package com.example.board.author.controller;

import com.example.board.author.domain.Author;
import com.example.board.author.dto.AuthorResDto;
import com.example.board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/list")
    public ResponseEntity<List<AuthorResDto>> authorList() {
        List<AuthorResDto> authors = authorService.authorList();
        return new ResponseEntity<>(authors, HttpStatus.CREATED);
    }


}
