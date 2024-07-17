package com.example.board.author.service;

import com.example.board.author.domain.Author;
import com.example.board.author.domain.Post;
import com.example.board.author.dto.*;
import com.example.board.author.repository.MyPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final MyPostRepository myPostRepository;

    @Autowired
    public PostService(MyPostRepository postRepository) {
        this.myPostRepository = postRepository;
    }

    public List<PostResDto> postList() {
        List<PostResDto> postResDtos = new ArrayList<>();
        List<Post> postList = myPostRepository.findAll();
        for (Post post : postList) {
            postResDtos.add(post.listFromEntity());
        }
        return postResDtos;
    }

    @Transactional
    public void postCreate(PostReqDto dto) {
        if (dto.getTitle().length() < 8) {
            throw new IllegalArgumentException("제목이 너무 짧습니다.");
        }
        Post post = dto.toEntity();
        myPostRepository.save(post);
    }

    public PostResDetDto postDetail(Long id) {
        Optional<Post> optPost = myPostRepository.findById(id);
        Post post = optPost.orElseThrow(()->new EntityNotFoundException("없는 회원입니다."));
        return post.detFromEntity();
    }
}
