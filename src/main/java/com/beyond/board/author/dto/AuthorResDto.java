package com.beyond.board.author.dto;

import com.beyond.board.author.domain.roleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResDto {
    private Long id;

    private String name;

    private String email;

}
