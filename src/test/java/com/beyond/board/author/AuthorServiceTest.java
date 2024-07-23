package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.repository.MyAuthorRepository;
import com.beyond.board.author.service.AuthorService;
import com.beyond.board.post.dto.AuthorUpdateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
@Transactional
//@Rollback
public class AuthorServiceTest {

    @Autowired
    AuthorService authorService;

    @Autowired
    MyAuthorRepository authorRepository;

    // 저장 및 detail 조회
    @Test
    void saveAndFind(){
        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("test10", "test10@naver.com", "1234123411", Role.ADMIN);
        Author author = authorService.authorCreate(authorDto);
        Author authorDetail  = authorRepository.findById(author.getId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));
        Assertions.assertEquals(authorDetail.getEmail(), authorDto.getEmail());
    }

    // update 검증
//    객체 create
    @Test
    public void updateTest() {
        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("test6", "test6@naver.com", "1234123411", Role.ADMIN);
        Author author = authorService.authorCreate(authorDto);

        //    수정 작업(name, password)
        authorService.update(author.getId(), new AuthorUpdateDto("hong", "4321432112"));

        //    수정 후 재조회 : name, password가 맞는지 각각 검증
        Author savedAuthor = authorRepository.findById(author.getId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));;
        Assertions.assertEquals("hong", savedAuthor.getName());
        Assertions.assertEquals("4321432112", savedAuthor.getPassword());
    }


    // findAll 검증
    @Test
    public void findAllTest() {

        int size = authorService.authorList().size();

//        3개 author 객체 저장
        AuthorSaveReqDto authorDto1 = new AuthorSaveReqDto("test7", "test7@naver.com", "1234123411", Role.ADMIN);
        AuthorSaveReqDto authorDto2 = new AuthorSaveReqDto("test8", "test8@naver.com", "1234123411", Role.ADMIN);
        AuthorSaveReqDto authorDto3 = new AuthorSaveReqDto("test9", "test9@naver.com", "1234123411", Role.ADMIN);

        authorService.authorCreate(authorDto1);
        authorService.authorCreate(authorDto2);
        authorService.authorCreate(authorDto3);

//        authorList를 조회한 후 저장한 갯수와 저장 된 갯수 비교
        Assertions.assertEquals(size+3, authorService.authorList().size());

    }
}