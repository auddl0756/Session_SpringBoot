package com.example.demo.config.auth.dto;

import com.example.demo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Log4j2
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        log.info("SessionUser 생성자");
        this.name=user.getName();
        this.email=user.getEmail();
        this.picture=user.getPicture();
    }
}
