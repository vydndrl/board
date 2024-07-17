package com.example.board.author.dto;

import com.example.board.author.domain.roleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResDto {
    private String name;

    private String email;

    private roleType role;
}
