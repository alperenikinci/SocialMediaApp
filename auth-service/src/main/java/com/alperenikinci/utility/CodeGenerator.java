package com.alperenikinci.utility;

import java.util.UUID;


public class CodeGenerator {


    public  static String generateCode(){
        String code=UUID.randomUUID().toString();
        String [] data=code.split("-")  ;
        StringBuilder newCode=new StringBuilder();
        for (String string:data){
            newCode.append(string.charAt(0));
        }
        return newCode.toString();
    }


}
