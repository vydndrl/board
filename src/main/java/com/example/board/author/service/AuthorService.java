package com.example.board.author.service;

import com.example.board.author.domain.Author;
import com.example.board.author.dto.AuthorResDetDto;
import com.example.board.author.dto.AuthorResDto;
import com.example.board.author.repository.MyAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

    private final MyAuthorRepository myAuthorRepository;

    @Autowired
    public AuthorService(MyAuthorRepository authorRepository) {
        this.myAuthorRepository = authorRepository;
    }

    public List<AuthorResDto> authorList() {
        List<AuthorResDto> authorResDtos = new ArrayList<>();
        List<Author> authorList = myAuthorRepository.findAll();
        for (Author author : authorList) {
            authorResDtos.add(author.listFromEntity());
        }
        return authorResDtos;
    }

    public AuthorResDetDto authorDetail(Long id) {
        Optional<Author> optAuthor = myAuthorRepository.findById(id);
        Author author = optAuthor.orElseThrow(()->new EntityNotFoundException("없는 회원입니다."));
        return author.detFromEntity();
    }
}
