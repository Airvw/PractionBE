package com.PractionBE.dtos.reponse;

public class UserResponse {

    private Long id;

    private String email;
    private String name;
    private String nickName;

    public UserResponse(){}

    public UserResponse(Long id, String email, String name, String nickName) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }
}
