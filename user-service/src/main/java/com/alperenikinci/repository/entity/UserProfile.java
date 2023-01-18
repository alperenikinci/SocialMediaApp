package com.alperenikinci.repository.entity;

import com.alperenikinci.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.ObjectInputFilter;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class UserProfile implements Serializable {

    @Id
    private  String id;
    private  Long authId;

    @Indexed(unique = true)
    private   String username;
    private   String name;
    private    String email;
    private    String phone;
    private    String avatar;
    private    String address;
    private    String about;

    @Builder.Default
    private Status status=Status.PENDING;

    long createdDate;

    long updatedDate;




}