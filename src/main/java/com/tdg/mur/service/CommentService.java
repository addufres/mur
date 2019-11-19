package com.tdg.mur.service;

import static com.github.marlonlom.utilities.timeago.TimeAgo.using;
import static com.tdg.mur.util.Constants.POST_NOT_FOUND_FOR_ID;
import static com.tdg.mur.util.Constants.POST_URL;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdg.mur.dto.CommentDto;
import com.tdg.mur.exception.PostNotFoundException;
import com.tdg.mur.model.Comment;
import com.tdg.mur.model.NotificationEmail;
import com.tdg.mur.model.Post;
import com.tdg.mur.model.User;
import com.tdg.mur.repos.CommentRepository;
import com.tdg.mur.repos.PostRepository;
import com.tdg.mur.repos.UserRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CommentService {

    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final CommentRepository commentRepo;
    private final MailBuilder mailBuilder;
    private final MailService mailService;
    private final AuthService authService;
    
    private CommentDto createCommentDto(Comment comment) {
    	return CommentDto.builder()
    			.postId(comment.getPost().getPostId())
    			.text(comment.getText())
    			.duration(using(comment.getCreatedDate().toEpochMilli()))
    			.user(comment.getUser().getUsername())
    			.build();
    }
    
    private Comment createComment(CommentDto commentDto, Post post) {
    	return Comment.builder()
    			.text(commentDto.getText())
    			.post(post)
    			.user(authService.getCurrentUser())
    			.createdDate(Instant.now())
    			.build();
    }
    
    private void sendCommentNotification(String message, User user) {
    	mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on Your Post!", user.getEmail(), message));
    }
    
    @Transactional
    public void createComment(CommentDto commentDto) {
    	Post p = postRepo.findById(commentDto.getPostId())
    			.orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_FOR_ID));
    	commentRepo.save(createComment(commentDto, p));
    	String message = mailBuilder.build(p.getUser().getUsername() + " wrote a comment on your post. " 
    	+ POST_URL + p.getPostId());
    	sendCommentNotification(message, p.getUser());
    }
    
    public List<CommentDto> getCommentsByUser(String name) {
    	User u = userRepo.findByUsername(name)
    			.orElseThrow(() -> new UsernameNotFoundException(name));
    	return commentRepo.findAllByUser(u)
    			.stream()
    			.map(this::createCommentDto)
    			.collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public  List<CommentDto> getCommentByPost(Long id) {
    	Post p = postRepo.findById(id)
    			.orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_FOR_ID + id));
    	return commentRepo.findByPost(p)
    			.stream()
    			.map(this::createCommentDto)
    			.collect(Collectors.toList());
    }
}
