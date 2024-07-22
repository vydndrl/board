package com.beyond.board.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PostListResDto {
    private Long id;
    private String title;
//    Author 객체 그 자체를 return 하게 되면 Author 안에 Post 가 재창조되어,
//    순환 참조 이슈 발생
//    private Author author;
    private String author_email;
}
