package com.example.demo.web;

import com.example.demo.domain.posts.Posts;
import com.example.demo.domain.posts.PostsRepository;
import com.example.demo.service.PostsService;
import com.example.demo.web.dto.PostsRequestDTO;
import com.example.demo.web.dto.PostsResponseDTO;
import com.example.demo.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    //create
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsRequestDTO requestDTO){
        return postsService.save(requestDTO);
    }

    //update
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDTO requestDTO){
        return postsService.update(id,requestDTO);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public void delete(@PathVariable Long id){
        postsService.delete(id);
    }

    //조회
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDTO test_see(@PathVariable Long id){
        return postsService.findById(id);
    }

}
