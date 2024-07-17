package com.example.board.author.domain;

import com.example.board.author.dto.AuthorResDetDto;
import com.example.board.author.dto.PostResDetDto;
import com.example.board.author.dto.PostResDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    public Post(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public PostResDto listFromEntity() {
        return new PostResDto(this.id, this.title);
    }

    public PostResDetDto detFromEntity() {
        LocalDateTime createdTime = this.getCreatedTime();
        String value = createdTime.getYear() + "년 " + createdTime.getMonthValue() + "월 " +
                createdTime.getDayOfMonth() + "일 ";
        return new PostResDetDto(this.id, this.title, this.contents, value);
    }
}
