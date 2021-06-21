package com.example.demo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@AllArgsConstructor
public class HelloResponseDTO {
    private String name;
    private int amount;
}
