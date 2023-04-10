package com.PractionBE.dtos.request;

public class UserRequest {

    private String email;

    private String name;

    private String password;

    private String nickName;

    public UserRequest(){}

    public UserRequest(String password, String nickName){
        this.password = password;
        this.nickName = nickName;
    }

    public UserRequest(String email, String name, String password, String nickName){
        this.email = email;
        this.name = name;
        this.password = password;
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }
}
