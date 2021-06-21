package com.example.demo.web;

import com.example.demo.domain.posts.Posts;
import com.example.demo.domain.posts.PostsRepository;
import com.example.demo.service.PostsService;
import com.example.demo.web.dto.PostsRequestDTO;
import com.example.demo.web.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void 컨트롤러로_Posts_등록() throws Exception{
        String title ="title";
        String content ="content";
        String author ="author";

        PostsRequestDTO requestDTO
                = PostsRequestDTO.builder()
                                .title(title)
                                .content(content)
                                .author(author)
                                .build();

        String url="http://localhost:"+port+"/api/v1/posts";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url,requestDTO,Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void 컨트롤러로_수정() throws Exception{
        String title ="title";
        String content ="content";
        String author ="author";

        PostsRequestDTO requestDTO
                = PostsRequestDTO.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        postsRepository.save(requestDTO.dtoToEntity()); //일단 저장하고

        Long updateId = postsRepository.findAll().get(0).getId();
        String updatedTitle = "updatedTitle";
        String updatedContent = "updatedContent";

        String url="http://localhost:"+port+"/api/v1/posts/"+updateId;

        PostsUpdateRequestDTO updateRequestDTO
                    =PostsUpdateRequestDTO.builder()
                                            .title(updatedTitle)
                                            .content(updatedContent)
                                            .build();

//        ResponseEntity<Long> responseEntity =

        testRestTemplate.put(url,updateRequestDTO);

        Posts post_updated = postsRepository.findById(updateId).orElseThrow(
                ()-> new IllegalArgumentException("id가 "+updateId+"인 글이 없음.")
        );

        assertThat(post_updated.getTitle()).isEqualTo(updatedTitle);
        assertThat(post_updated.getContent()).isEqualTo(updatedContent);
    }

}