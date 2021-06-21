package com.example.demo.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostsListsResponseDTO {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;
}
