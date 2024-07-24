package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorSaveReqDto {
    private String name;
    private String email;
    private String password;
//    사용자가 String 요청해도 Role 클래스 자동 형 변환
    private Role role;

    private PasswordEncoder passwordEncoder;

    public Author toEntity(String encodedPassword) {
        Author author = Author.builder()
                .password(encodedPassword)
                .name(this.name)
                .email(this.email)
                .posts(new ArrayList<>())
                .role(this.role).build();
        return author;
    }
}
