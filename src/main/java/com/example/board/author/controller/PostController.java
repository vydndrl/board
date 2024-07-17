package com.example.board.author.controller;

import com.example.board.author.dto.*;
import com.example.board.author.service.AuthorService;
import com.example.board.author.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/list")
    public ResponseEntity<List<PostResDto>> postList() {
        List<PostResDto> posts = postService.postList();
        return new ResponseEntity<>(posts, HttpStatus.CREATED);
    }

    @PostMapping("/post/create")
    public Object memberCreatePost(@RequestBody PostReqDto dto){
        try {
            postService.postCreate(dto);
            CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "Success", dto);
            ResponseEntity<CommonResDto> result = new ResponseEntity<>(commonResDto, HttpStatus.OK);
            return result;
        }catch (IllegalArgumentException e){
            CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            ResponseEntity<CommonErrorDto> result = new ResponseEntity<>(commonErrorDto, HttpStatus.BAD_REQUEST);
            return result;
        }
    }

    @GetMapping("/post/detail/{id}")
    public Object postDetail (@PathVariable Long id) {
        try {
            PostResDetDto postResDetDto = postService.postDetail(id);
            CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "member is found", postResDetDto);
            return new ResponseEntity<>(commonResDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
            ResponseEntity<CommonErrorDto> result = new ResponseEntity<>(commonErrorDto, HttpStatus.NOT_FOUND);
            return result;
        }
    }
}
