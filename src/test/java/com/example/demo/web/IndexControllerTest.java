package com.example.demo.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

//실제 url 호출 시에 페이지 내용이 제대로 호출되는지 검사
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //실제로 서버 실행
public class IndexControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void 메인페이지_로딩(){
        String body = testRestTemplate.getForObject("/",String.class);
//        System.out.println(body);
        assertThat(body).contains("spring boot");   //페이지 내용 검사
    }
}