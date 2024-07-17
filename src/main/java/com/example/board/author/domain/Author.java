package com.example.board.author.domain;

import com.example.board.author.dto.AuthorResDetDto;
import com.example.board.author.dto.AuthorResDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private roleType role;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;

    public Author(String name, String email, String password, roleType role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AuthorResDto listFromEntity() {
        return new AuthorResDto(this.name, this.email, this.role);
    }

    public AuthorResDetDto detFromEntity() {
        LocalDateTime createdTime = this.getCreatedTime();
        String value = createdTime.getYear() + "년 " + createdTime.getMonthValue() + "월 " +
                createdTime.getDayOfMonth() + "일 ";
        return new AuthorResDetDto
                (this.id, this.name, this.email, this.password, this.role, value);
    }
}

