package com.example.board.author.dto;

import com.example.board.author.domain.roleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResDetDto {
    private Long id;
    private String title;
    private String contents;
    private String createdTime;
}
