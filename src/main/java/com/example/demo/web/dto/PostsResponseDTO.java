package com.example.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PostsResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime modifiedDate;
}
