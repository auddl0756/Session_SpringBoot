package com.example.demo.config.auth.dto;

import com.example.demo.domain.user.Role;
import com.example.demo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
@AllArgsConstructor
@Builder
@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String,Object> attributes){

        return ofGoogle(userNameAttributeName,attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,Map<String,Object> attributes){
        log.info("ofGoogle() 들어옴");
        return OAuthAttributes.builder()
                                .name((String)attributes.get("name"))
                                .email((String)attributes.get("email"))
                                .picture((String)attributes.get("picture"))
                                .attributes(attributes)
                                .nameAttributeKey(userNameAttributeName)
                                .build();
    }

    public User toEntity(){
        return User.builder()
                    .name(name)
                    .email(email)
                    .picture(picture)
                    .role(Role.USER)
                    .build();
    }

}
