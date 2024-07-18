package com.beyond.board.author.controller;

import com.beyond.board.author.dto.*;
import com.beyond.board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/list")
    public List<AuthorResDto> authorList() {
        return authorService.authorList();
    }

    @PostMapping("/author/create")
    public String memberCreatePost(@RequestBody AuthorSaveReqDto dto){
            authorService.authorCreate(dto);
            return "ok";
    }

    @GetMapping("/author/detail/{id}")
    public AuthorResDetDto authorDetail (@PathVariable Long id) {
        return authorService.authorDetail(id);
    }
}
