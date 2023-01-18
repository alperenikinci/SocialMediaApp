package com.alperenikinci.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateUserModel implements Serializable {
   private Long authId;
   private String email;
   private String username;
}
