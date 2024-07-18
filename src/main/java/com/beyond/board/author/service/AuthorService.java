package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorResDetDto;
import com.beyond.board.author.dto.AuthorResDto;
import com.beyond.board.author.repository.MyAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
// 조회 작업 시에 readOnly 설정하면 성능 향상
// 다만, 저장 작업시에는 Transactional 필요
@Transactional(readOnly = true)
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

    @Transactional
    public Author authorCreate(AuthorSaveReqDto dto) {
        if (myAuthorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 email 입니다.");
        }
        Author author = dto.toEntity();
        return myAuthorRepository.save(author);
    }

    public AuthorResDetDto authorDetail(Long id) {
        Author author = myAuthorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
        AuthorResDetDto authorResDetDto = new AuthorResDetDto();
        return authorResDetDto.fromEntity(author);
    }

    public Author authorFindByEmail(String email) {
        Author author = myAuthorRepository.findByEmail(email)
                .orElseThrow(()->new EntityNotFoundException("해당 Email의 사용자는 없습니다."));
        return author;
    }
}
