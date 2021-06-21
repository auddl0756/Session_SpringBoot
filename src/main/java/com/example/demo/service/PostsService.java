package com.example.demo.service;

import com.example.demo.domain.posts.Posts;
import com.example.demo.domain.posts.PostsRepository;
import com.example.demo.web.dto.PostsRequestDTO;
import com.example.demo.web.dto.PostsResponseDTO;
import com.example.demo.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsRequestDTO requestDTO){
//        return postsRepository.save(dtoToEntity(requestDTO)).getId();
        return postsRepository.save(requestDTO.dtoToEntity()).getId();
    }

    @Transactional
    public Long update(Long id,PostsUpdateRequestDTO requestDTO) {
        Posts post = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );

        post.update(requestDTO.getTitle(), requestDTO.getContent());
        return id;
    }

//    public Posts findById(Long id){
//        Optional<Posts> optPost = postsRepository.findById(id);
//        if(optPost.isPresent()==false){
//            return null;
//        }
//        return optPost.get();
//    }


    //이거 왜 필요한건지?
    public PostsResponseDTO findById(Long id){
        Posts post = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id ="+id+"인 게시물 없음"));
        PostsResponseDTO dto
                = PostsResponseDTO.builder()
                                    .id(id)
                                    .title(post.getTitle())
                                    .content(post.getContent())
                                    .author(post.getAuthor())
                                    .build();

        return dto;
    }

    public List<Posts> findAll(){
        return postsRepository.findAll();
    }

    @Transactional
    public void delete(Long id){
        postsRepository.deleteById(id);
    }

}
