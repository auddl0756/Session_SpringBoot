package com.example.demo.domain.user;

import com.example.demo.domain.posts.BaseEntity;
import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
@Entity
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable=false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public User update(String name,String picture){
        this.name=name;
        this.picture=picture;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
