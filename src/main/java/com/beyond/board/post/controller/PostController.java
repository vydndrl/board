package com.beyond.board.post.controller;

import com.beyond.board.author.dto.*;
import com.beyond.board.post.dto.*;
import com.beyond.board.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/list")
    public String postList(Model model) {
        model.addAttribute("postList", postService.postList());
        return "post/post_list";
    }

    @GetMapping("post/create")
    public String postCreateScreen() {
        return "/post/post_register";
    }


    @PostMapping("/post/create")
    public String postCreate(PostReqDto dto){
       postService.postCreate(dto);
       return "redirect:/post/list";
    }

    @GetMapping("/post/detail/{id}")
    public String postDetail (@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.postDetail(id));
        return "post/post_detail";
    }

    @GetMapping("post/delete/{id}")
    public String postDelete (@PathVariable Long id, Model model) {
        postService.delete(id);
        return "redirect:/post/list";
    }

    @PostMapping("post/update/{id}")
    public String postUpdate(@PathVariable Long id,
                               @ModelAttribute PostUpdateDto dto,
                               Model model) {
        postService.update(id, dto);
        return "redirect:/post/detail/" + id;
    }
}
