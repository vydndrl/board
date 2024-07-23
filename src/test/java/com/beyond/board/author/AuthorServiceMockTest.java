package com.beyond.board.author;


import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.dto.AuthorResDetDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.repository.MyAuthorRepository;
import com.beyond.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AuthorServiceMockTest {

    @Autowired
    private AuthorService authorService;

//    @Autowired
//    private MyAuthorRepository authorRepository;
//    가짜 객체를 만드는 작업을 목킹이라 한다.

    @MockBean
    private MyAuthorRepository authorRepository;

    @Test
    public void findAuthorDetailTest() {
        AuthorSaveReqDto author = new AuthorSaveReqDto("test1", "test1@naver.com", "12341234", Role.ADMIN);
        Author author1 = authorService.authorCreate(author);
//        Author mockAuthor = Author.builder().id(1L).name("test").email("test@naver.com").build();
        AuthorResDetDto authorResDetDto = authorService.authorDetail(author1.getId());
//        Author savedAuthor = authorRepository.findById(author1.getId())
//                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        Mockito.when(authorRepository.findById(author1.getId())).thenReturn(Optional.of(author1));
        Author savedAuthor = authorRepository.findById(author1.getId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        Assertions.assertEquals(authorResDetDto.getEmail(), savedAuthor.getEmail());
    }
}
