package com.beyond.board.post.controller;

import com.beyond.board.author.dto.*;
import com.beyond.board.post.service.PostService;
import com.beyond.board.post.dto.PostReqDto;
import com.beyond.board.post.dto.PostResDetDto;
import com.beyond.board.post.dto.PostResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/list")
    public List<PostResDto> postList() {
        return postService.postList();
    }

    @PostMapping("/post/create")
    public String postCreate(@RequestBody PostReqDto dto){
       postService.postCreate(dto);
       return "ok";
    }

    @GetMapping("/post/detail/{id}")
    public PostResDetDto postDetail (@PathVariable Long id) {
        return postService.postDetail(id);
    }
}
