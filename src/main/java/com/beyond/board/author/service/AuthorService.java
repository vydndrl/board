package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorResDetDto;
import com.beyond.board.author.dto.AuthorResDto;
import com.beyond.board.author.repository.MyAuthorRepository;
import com.beyond.board.post.domain.Post;
import com.beyond.board.post.dto.AuthorUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthorService(MyAuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.myAuthorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
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
        Author author = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
//        cascade persist 테스트, remove 테스트는 회원 삭제로 대체
        author.getPosts().add(Post.builder()
                .title("가입인사")
                .author(author)
                .contents("안녕하세요 " + dto.getName() + " 입니다.")
                .build());
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

    @Transactional
    public void delete(Long id) {
        myAuthorRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, AuthorUpdateDto dto) {
        Author author = myAuthorRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("member is not found"));
        author.updateAuthor(dto);
//        jpa가 특정 엔티티의 변경을 자동으로 인지하고 변경사항을 DB에 반영하는 것이 dirtychecking(변경감지)
//        더티체킹 시 transactional 어노테이션 필요.
//        myAuthorRepository.save(author);
    }
}
