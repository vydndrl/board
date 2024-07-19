package com.beyond.board.author.controller;

import com.beyond.board.author.dto.*;
import com.beyond.board.author.service.AuthorService;
import com.beyond.board.post.dto.AuthorUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/list")
    public String authorList(Model model) {
        List<AuthorResDto> authorList = authorService.authorList();
        model.addAttribute("authorList",authorList);
        return "author/author_list";
    }

    @GetMapping("/author/register")
    public String authorCreateScreen() {
        return "/author/author_register";
    }

    @PostMapping("/author/register")
    public String authorCreatePost(@ModelAttribute AuthorSaveReqDto dto){
            authorService.authorCreate(dto);
            return "redirect:/";
    }

    @GetMapping("/author/detail/{id}")
    public String authorDetail (@PathVariable Long id, Model model) {
        AuthorResDetDto author = authorService.authorDetail(id);
        model.addAttribute("author",author);
        return "author/author_detail";
    }

    @GetMapping("author/delete/{id}")
    public String authorDelete (@PathVariable Long id, Model model) {
        authorService.delete(id);
        return "redirect:/author/list";
    }

    @PostMapping("author/update/{id}")
    public String authorUpdate(@PathVariable Long id,
                               @ModelAttribute AuthorUpdateDto dto,
                               Model model) {
        authorService.update(id, dto);
        return "redirect:/author/detail/" + id;
    }
}
