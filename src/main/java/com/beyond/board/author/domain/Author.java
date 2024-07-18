package com.beyond.board.author.domain;

import com.beyond.board.author.dto.AuthorResDetDto;
import com.beyond.board.author.dto.AuthorResDto;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
//builder 어노테이션을 통해 매개변수의 순서, 매개변수의 갯수 등을 유연하게 세팅하여
//생성자로써 활용
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @Builder
    public Author(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public AuthorResDto listFromEntity() {
        AuthorResDto authorResDto = AuthorResDto.builder()
                .name(this.name)
                .email(this.email)
                .id(this.id)
                .build();
        return authorResDto;
    }

    public AuthorResDetDto detFromEntity() {
        return new AuthorResDetDto
                (this.id, this.name, this.email, this.password, this.role, (this.getPosts().size()), this.getCreatedTime());
    }
}

