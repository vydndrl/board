package com.beyond.board.post.domain;

import com.beyond.board.author.domain.Author;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.dto.PostResDetDto;
import com.beyond.board.post.dto.PostResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 3000)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Builder
    public Post(Long id ,String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public PostResDto listFromEntity() {
        PostResDto postResDto = PostResDto.builder()
                .id(this.id)
                .title(this.title)
//                .author(this.author)
                .author_email(this.author.getEmail())
                .build();
        return postResDto;
    }

    public PostResDetDto detFromEntity() {
        return PostResDetDto.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .author_email(this.author.getEmail())
                .createdTime(this.getCreatedTime())
                .updatedTime(this.getUpdateTime())
                .build();
    }
}
