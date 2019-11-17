package com.tdg.mur.service;

import static com.github.marlonlom.utilities.timeago.TimeAgo.using;
import static com.tdg.mur.model.VoteType.UPVOTE;
import static com.tdg.mur.util.Constants.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.Instant;
import javax.validation.Valid;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdg.mur.dto.PostRequest;
import com.tdg.mur.dto.PostResponse;
import com.tdg.mur.exception.PostNotFoundException;
import com.tdg.mur.exception.ThreadNotFoundException;
import com.tdg.mur.model.Post;
import com.tdg.mur.model.Thread;
import com.tdg.mur.model.User;
import com.tdg.mur.model.Vote;
import com.tdg.mur.model.VoteType;
import com.tdg.mur.repos.CommentRepository;
import com.tdg.mur.repos.PostRepository;
import com.tdg.mur.repos.ThreadRepository;
import com.tdg.mur.repos.UserRepository;
import com.tdg.mur.repos.VoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostService {
	
	private final AuthService authService;
    private final PostRepository postRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final ThreadRepository threadRepository;
    private final CommentRepository commentRepository;

    PostResponse createPostDto(Post post) {
    	PostResponse pr = PostResponse.builder()
    			.id(post.getPostId())
    			.postName(post.getPostName())
    			.description(post.getDescription())
    			.url(post.getUrl())
    			.userName(post.getUser().getUsername())
    			.threadName(post.getThread().getName())
    			.voteSum(post.getVoteCount())
    			.commentSum(commentRepository.findByPost(post).size())
                .duration(using(post.getCreatedDate().toEpochMilli()))
                .build();
    	if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser = voteRepository
            		.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
            if (voteForPostByUser.isPresent()) {
                VoteType voteType = voteForPostByUser.get().getVoteType();
                if (voteType.equals(UPVOTE))
                    pr.setUpVote(true);
                else
                    pr.setDownVote(true);
            }
        }
        return pr;
    }

    private Post mapToPostModel(PostRequest postRequest) {
        Thread t = threadRepository.findByName(postRequest.getThreadName())
                .orElseThrow(() -> new ThreadNotFoundException(THREAD_NOT_FOUND_WITH_NAME
                        + postRequest.getThreadName()));
        return Post.builder()
                .postName(postRequest.getPostName())
                .description(postRequest.getDescription())
                .url(postRequest.getUrl())
                .createdDate(Instant.now())
                .voteCount(0)
                .thread(t)
                .user(authService.getCurrentUser())
                .build();
    }

    @Transactional
    public void create(@Valid PostRequest postRequest) {
    	postRepository.save(mapToPostModel(postRequest));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_FOR_ID + id));
        return createPostDto(post);
    }
    
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll()
                .stream()
                .map(this::createPostDto)
                .collect(Collectors.toList());
    }
    
    public List<PostResponse> getAllByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(this::createPostDto)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<PostResponse> getAllByThread(Long threadId){
    	Thread thread = threadRepository.findById(threadId)
                .orElseThrow(() -> new ThreadNotFoundException(THREAD_NOT_FOUND_WITH_ID + threadId));
    	List<Post> posts = postRepository.findAllByThread(thread);
    	return posts.stream()
    			.map(this::createPostDto)
    			.collect(Collectors.toList());
    	
    }
    
}
