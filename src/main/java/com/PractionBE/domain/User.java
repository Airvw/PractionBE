package com.PractionBE.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String nickName;

    public User(){}

    public User(String email, String name, String password, String nickName){
        this.email = email;
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

    public void updateUser(String password, String nickName) {
        this.password = password;
        this.nickName = nickName;
    }
}
