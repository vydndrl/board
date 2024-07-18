package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResDetDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private int postCounts;
    private LocalDateTime createdTime;

    public AuthorResDetDto fromEntity(Author author) {
        return AuthorResDetDto.builder()
                .id(author.getId())
                .name(author.getName())
                .password(author.getPassword())
                .role(author.getRole())
                .postCounts(author.getPosts().size())
                .email(author.getEmail())
                .createdTime(author.getCreatedTime())
                .build();
    }
}
