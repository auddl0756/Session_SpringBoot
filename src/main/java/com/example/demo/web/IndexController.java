package com.example.demo.web;

import com.example.demo.config.auth.LoginUser;
import com.example.demo.config.auth.dto.SessionUser;
import com.example.demo.service.PostsService;
import com.example.demo.web.dto.PostsResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Log4j2
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts",postsService.findAll());
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        log.info("/경로로 요청이 들어왔습니다.");

        if(user!=null) {
            model.addAttribute("userName1",user.getName());
            System.out.println(user.getEmail()+" "+user.getName());
        } else{
//            model.addAttribute("userName1","?");
//            System.out.println("session user is null");
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

//    @GetMapping("/posts/list")
//    public String postsList(Model model){
//        model.addAttribute("posts",postsService.findAll());
//        return "index";
//    }

    @GetMapping("/posts/show/{id}")
    public String postsShow(@PathVariable Long id,Model model){
        model.addAttribute("post",postsService.findById(id));
        return "posts-show";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDTO dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
