package com.alperenikinci.repository.entity;

import com.alperenikinci.repository.enums.Roles;
import com.alperenikinci.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tbl_auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "username", unique = true)
    String username;
    String password;
    String email;
    String activationCode;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    Roles role = Roles.USER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    Status status = Status.PENDING;


}
