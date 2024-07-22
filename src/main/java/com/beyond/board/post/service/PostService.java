package com.beyond.board.post.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.service.AuthorService;
import com.beyond.board.post.domain.Post;
import com.beyond.board.post.dto.*;
import com.beyond.board.post.repository.MyPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public Page<PostListResDto> postList(Pageable pageable) {
//        List<Post> posts = myPostRepository.findAllLeftJoin();
//        List<PostResDto> postResDtos = new ArrayList<>();
//        for (Post p : posts) {
//            postResDtos.add(p.listFromEntity());
//        }
//        Page<Post> posts = myPostRepository.findAll(pageable)
        Page<Post> posts = myPostRepository.findByAppointment(pageable, "N");
        Page<PostListResDto> postListResDtos = posts.map(a->a.listFromEntity());
        return postListResDtos;
    }

    public Page<PostListResDto> postListPage(Pageable pageable) {
        Page<Post> posts = myPostRepository.findAll(pageable);
        Page<PostListResDto> postListResDtos = posts.map(a->a.listFromEntity());
        return postListResDtos;
    }

//    authorservice에서 author객체를 찾아 post의 toEntity에 넘기고,
//    jpa가 author 객체에서 author_id를 찾아 db에는 author_id가 넘어감
    @Transactional
    public Post postCreate(PostSaveReqDto dto) {
        Author author = authorService.authorFindByEmail(dto.getEmail());
        String appointment = null;
        LocalDateTime appointmentTime = null;
        if (dto.getAppointment().equals("Y") && !dto.getAppointmentTime().isEmpty()) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            appointmentTime = LocalDateTime.parse(dto.getAppointmentTime(), dateTimeFormatter);
            LocalDateTime now = LocalDateTime.now();
            if (appointmentTime.isBefore(now)) {
                throw new IllegalArgumentException("시간 입력이 잘못 되었습니다.");
            }
        }
        System.out.println("dto = " + dto);
        Post post = myPostRepository.save(dto.toEntity(author, appointmentTime));
        return post;
    }

    public PostDetResDto postDetail(Long id) {
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
