package com.example.demo.web.dto;

import com.example.demo.domain.posts.Posts;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostsRequestDTO {
    private String title;
    private String content;
    private String author;

    public Posts dtoToEntity(){
        Posts entity = Posts.builder()
                .title(this.getTitle())
                .content(this.getContent())
                .author(this.getAuthor())
                .build();

        return  entity;
    }
}
