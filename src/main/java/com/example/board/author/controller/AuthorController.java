package com.example.board.author.controller;

import com.example.board.author.domain.Author;
import com.example.board.author.dto.*;
import com.example.board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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

    @PostMapping("/author/create")
    public Object memberCreatePost(@RequestBody AuthorReqDto dto){
        try {
            authorService.authorCreate(dto);
            CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "Success", dto);
            ResponseEntity<CommonResDto> result = new ResponseEntity<>(commonResDto, HttpStatus.OK);
            return result;
        }catch (IllegalArgumentException e){
            CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            ResponseEntity<CommonErrorDto> result = new ResponseEntity<>(commonErrorDto, HttpStatus.BAD_REQUEST);
            return result;
        }
    }

    @GetMapping("/author/detail/{id}")
    public Object authorDetail (@PathVariable Long id) {
        try {
            AuthorResDetDto authorResDetDto = authorService.authorDetail(id);
            CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "member is found", authorResDetDto);
            return new ResponseEntity<>(commonResDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
            ResponseEntity<CommonErrorDto> result = new ResponseEntity<>(commonErrorDto, HttpStatus.NOT_FOUND);
            return result;
        }
    }
}
