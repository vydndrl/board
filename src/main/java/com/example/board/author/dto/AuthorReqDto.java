package com.example.board.author.dto;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.roleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthorReqDto {
    private String name;
    private String email;
    private String password;
    private roleType role;

    public Author toEntity() {
        return new Author(this.name, this.email, this.password, this.role);
    }
}
