package com.example.demo.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    public void Post저장후_출력(){
        String title="title";
        String content="content";
        String author ="author";

        Posts post = Posts.builder()
                        .title(title)
                        .content(content)
                        .author(author)
                        .build();

        postsRepository.save(post);

        Posts post_get = postsRepository.findAll().get(0);

        assertThat(post_get.getTitle()).isEqualTo(title);
        assertThat(post_get.getContent()).isEqualTo(content);

    }
}