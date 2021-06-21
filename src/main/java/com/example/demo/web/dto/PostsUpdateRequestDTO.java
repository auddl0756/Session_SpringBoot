package com.example.demo.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostsUpdateRequestDTO {
    private String title;
    private String content;
}
