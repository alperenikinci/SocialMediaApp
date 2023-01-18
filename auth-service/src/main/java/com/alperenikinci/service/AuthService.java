package com.alperenikinci.service;


import com.alperenikinci.dto.request.ActivateRequestDto;
import com.alperenikinci.dto.request.LoginRequestDto;
import com.alperenikinci.dto.request.RegisterRequestDto;
import com.alperenikinci.dto.request.UpdateByEmailOrUsernameRequestDto;
import com.alperenikinci.dto.response.ActivateResponseDto;

import com.alperenikinci.dto.response.LoginResponseDto;
import com.alperenikinci.dto.response.RegisterResponseDto;
import com.alperenikinci.dto.response.RoleResponseDto;
import com.alperenikinci.exception.AuthManagerException;
import com.alperenikinci.exception.ErrorType;
import com.alperenikinci.manager.IUserManager;
import com.alperenikinci.mapper.AuthMapper;
import com.alperenikinci.rabbitmq.producer.RegisterUserProducer;
import com.alperenikinci.repository.IAuthRepository;
import com.alperenikinci.repository.entity.Auth;
import com.alperenikinci.repository.enums.Roles;
import com.alperenikinci.repository.enums.Status;
import com.alperenikinci.utility.CodeGenerator;
import com.alperenikinci.utility.JwtTokenManager;
import com.alperenikinci.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class AuthService  extends ServiceManager<Auth,Long > {


    private  final IAuthRepository authRepository;
    private final IUserManager userManager;
    private final CacheManager cacheManager;
    private final JwtTokenManager jwtTokenManager;
    private final RegisterUserProducer registerUserProducer;

    public AuthService(IAuthRepository authRepository, IUserManager userManager, CacheManager cacheManager, JwtTokenManager jwtTokenManager, RegisterUserProducer registerUserProducer) {
        super(authRepository);
        this.authRepository=authRepository;
        this.userManager = userManager;
        this.cacheManager = cacheManager;
        this.jwtTokenManager = jwtTokenManager;
        this.registerUserProducer = registerUserProducer;
    }

    public Boolean updateByEmailOrUsername (UpdateByEmailOrUsernameRequestDto dto) {
        Optional <Auth> auth = authRepository.findById(dto.getId());
        if(auth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }

        auth.get().setUsername(dto.getUsername());
        auth.get().setEmail(dto.getEmail());
        save(auth.get());
        return true;
    }

    public Boolean deleteAuthById (Long authId){
        Optional<Auth> auth = findById(authId);
        if (auth.isEmpty()) {
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        auth.get().setStatus(Status.DELETED);
        save(auth.get());
        userManager.deleteByAuthId(authId);
        return true;
    }





    @Transactional
    public RegisterResponseDto register(RegisterRequestDto dto){

    /*    if (authRepository.findOptionalByUsername(dto.getUsername()).isPresent()){
            throw  new AuthManagerException(ErrorType.USERNAME_DUPLICATE);
        }*/
        Auth auth= AuthMapper.INSTANCE.toAuth(dto);
        try {
            auth.setActivationCode(CodeGenerator.generateCode());
            save(auth);
            userManager.createUser(AuthMapper.INSTANCE.toNewCreateUserDto(auth));
            return AuthMapper.INSTANCE.toRegisterResponseDto(auth);
        }catch (Exception e){
            System.out.println(e.toString());
            //  throw  new DataIntegrityViolationException("Kullanýcý adý vardýr");
            throw  new AuthManagerException(ErrorType.USERNAME_DUPLICATE);
        }
    }
    public RegisterResponseDto registerWithRabbitMQ(RegisterRequestDto dto) {

        if (authRepository.findOptionalByUsername(dto.getUsername()).isPresent()) {
            throw new AuthManagerException(ErrorType.USERNAME_DUPLICATE);
        }
        Auth auth = AuthMapper.INSTANCE.toAuth(dto);
        try {
            auth.setActivationCode(CodeGenerator.generateCode());
            save(auth);
            registerUserProducer.sendNewUser(AuthMapper.INSTANCE.toNewCreateUserModel(auth));
            return AuthMapper.INSTANCE.toRegisterResponseDto(auth);
        } catch (Exception e) {
            //    delete(auth);
            System.out.println(e.toString());
            //  throw  new DataIntegrityViolationException("Kullan?c? ad? vard?r");
            throw new AuthManagerException(ErrorType.USER_NOT_CREATED);
        }
    }

        public ActivateResponseDto activateStatus(ActivateRequestDto dto) {
            Optional<Auth> auth=findById(dto.getId()) ;
            ActivateResponseDto responseDto=null;
            if (auth.isEmpty()){
                throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
            }
            if (auth.get().getActivationCode().equals(dto.getActivationCode())){
                auth.get().setStatus(Status.ACTIVE);
                save(auth.get());
                // userprofile controller a bir id gonderebilirim
                userManager.activateStatus(dto.getId());
                cacheManager.getCache("findallactiveprofile").clear();
                responseDto=AuthMapper.INSTANCE.toActivateResponseDto(auth.get());
            }
            return responseDto;
        }

    public LoginResponseDto login(LoginRequestDto dto) {
        Optional<Auth> auth=authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty()){
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        }
        if(!auth.get().getStatus().equals(Status.ACTIVE)){
            throw new AuthManagerException(ErrorType.LOGIN_STATUS_ERROR);
        }
        LoginResponseDto loginResponseDto=AuthMapper.INSTANCE.toLoginResponseDto(auth.get());
        loginResponseDto.setToken(jwtTokenManager.createToken(auth.get().getId()));
        return  loginResponseDto;
    }

    public List<ActivateResponseDto> getAllActivateStatus() {
        List<Auth> authList = authRepository.getAllActivateStatus();
//        return authList.stream().map(a-> AuthMapper.INSTANCE.toActivateResponseDto(a)).collect(Collectors.toList());
        return AuthMapper.INSTANCE.toActivateResponseDtos(authList);
    }

    public List<ActivateResponseDto> findAllByStatus(Status status) {
        List<Auth> authList = authRepository.findAllByStatus(status);
        return AuthMapper.INSTANCE.toActivateResponseDtos(authList);
    }

    public List<ActivateResponseDto>  findAllByStatusString(String status) {

        Status status1=null;

        try {
            status1= Status.valueOf(status.toUpperCase());
            List<Auth> authList = authRepository.findAllByStatus(status1);
            return AuthMapper.INSTANCE.toActivateResponseDtos(authList);

        }catch (Exception e){
            throw new AuthManagerException(ErrorType.STATUS_NOT_FOUND);
        }

    }

    public List<RoleResponseDto> getByRole(String role) {
        Roles roles=null;

        try{
            roles=Roles.valueOf(role.toUpperCase());
            return AuthMapper.INSTANCE.toRoleResponseDtos(authRepository.findAllOptionalByRole(roles));
        } catch (Exception e){
            throw new AuthManagerException(ErrorType.ROLE_NOT_FOUND);
        }
    }

    //uzun yöntem.
/*    public List<RoleResponseDto> getByRole(String role) {
        Roles roles=null;
        List<RoleResponseDto> roleList = new ArrayList<>();
        try{
            roles=Roles.valueOf(role.toUpperCase());
            roleList = authRepository.findAllOptionalByRole(roles).stream().map(x-> AuthMapper.INSTANCE.toRoleResponseDto(x)).collect(Collectors.toList());
            return roleList;
        } catch (Exception e){
            throw new AuthManagerException(ErrorType.ROLE_NOT_FOUND);
        }
    }*/




}