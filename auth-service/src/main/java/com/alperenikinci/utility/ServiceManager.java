package com.alperenikinci.utility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @param <T>  -> Bu sınıfı miras almak isteyen bir servis hangi entity e
 *           hizmet ediyor ise onu Type olarak verecek.
 * @param <ID> -> Bu sınıfı miras alan servisin kullanmakta olduğu id tipini
 *            belirlemesi gerekir, Long,String,Integer...
 */

public class ServiceManager<T, ID> implements IService<T,ID>{
    /**
     * Tüm Servisler için ortak bir kullanım sunacak ise, burada repository
     * üzerinden işlem yapabiliyor olmalı.
     * @param t
     * @return
     */

    private final JpaRepository<T,ID> repository;
    public ServiceManager(JpaRepository<T,ID> repository){
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        return repository.saveAll(t);
    }

    @Override
    public T update(T t) {
        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
}
