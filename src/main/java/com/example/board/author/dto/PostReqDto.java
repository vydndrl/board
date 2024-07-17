package com.example.board.author.dto;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.Post;
import com.example.board.author.domain.roleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostReqDto {
    private Long id;
    private String title;
    private String contents;

    public Post toEntity() {
        return new Post(this.id, this.title, this.contents);
    }
}
