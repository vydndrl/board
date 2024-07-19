package com.beyond.board.post.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.service.AuthorService;
import com.beyond.board.post.domain.Post;
import com.beyond.board.post.dto.*;
import com.beyond.board.post.repository.MyPostRepository;
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
    private final AuthorService authorService;

//    다 못씀
//    service 또는 repository 를 autowired 할지는 상황에 따라 다르니,
//    service 레벨에서 코드가 고도화 되어있고 코드의 중복이 심할경우, serivce 레이어를 autowired
    @Autowired
    public PostService(MyPostRepository postRepository, AuthorService authorService) {
        this.myPostRepository = postRepository;
        this.authorService = authorService;
    }

    public List<PostResDto> postList() {
        List<Post> posts = myPostRepository.findAllLeftJoin();
        List<PostResDto> postResDtos = new ArrayList<>();
        for (Post p : posts) {
            postResDtos.add(p.listFromEntity());
        }
        return postResDtos;
    }

//    authorservice에서 author객체를 찾아 post의 toEntity에 넘기고,
//    jpa가 author 객체에서 author_id를 찾아 db에는 author_id가 넘어감
    @Transactional
    public Post postCreate(PostReqDto dto) {
        Author author = authorService.authorFindByEmail(dto.getEmail());
        Post post = myPostRepository.save(dto.toEntity(author));
        return post;
    }

    public PostResDetDto postDetail(Long id) {
        Post post = myPostRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시물이 없습니다."));
        return post.detFromEntity();
    }

    @Transactional
    public void delete(Long id) {
        myPostRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, PostUpdateDto dto) {
        Post post = myPostRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("post is not found"));
        post.updatePost(dto);
        myPostRepository.save(post);
    }
}
