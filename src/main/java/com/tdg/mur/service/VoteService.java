package com.tdg.mur.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdg.mur.dto.PostResponse;
import com.tdg.mur.dto.VoteDto;
import com.tdg.mur.exception.MurException;
import com.tdg.mur.exception.PostNotFoundException;
import com.tdg.mur.model.Post;
import com.tdg.mur.model.Vote;
import com.tdg.mur.repos.PostRepository;
import com.tdg.mur.repos.VoteRepository;
import static com.tdg.mur.model.VoteType.UPVOTE;
import static com.tdg.mur.util.Constants.POST_NOT_FOUND_FOR_ID;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {

	private final PostService postService;
	private final AuthService authService;
    private final VoteRepository voteRepo;
    private final PostRepository postRepo;
    
    @Transactional
    public synchronized PostResponse vote(VoteDto voteDto) {
		// Get the post
        Post post = postRepo.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        // Use the post to gather the votes and Users who voted 
        Optional<Vote> voteByPostAndUser = voteRepo.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        // Check if current logged in User has voted previously on post.
        if (voteByPostAndUser.isPresent()) {
            if (voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
                throw new MurException("You have already " + voteDto.getVoteType() + "'d for this post");
            }
        }
        // increase or decrease vote count accordingly
        int count = 0;
        if (UPVOTE.equals(voteDto.getVoteType())) {
            count = post.getVoteCount() + 1;
        } else {
            count = post.getVoteCount() - 1;
        }
        voteRepo.save(createVote(voteDto, post));
        post.setVoteCount(count);
        postRepo.save(post);
        return postService.createPostDto(post);
    }

	private Vote createVote(VoteDto voteDto, Post post) {
		return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
	}
	
    public Integer getVoteNumber(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND_FOR_ID + postId));
        return post.getVoteCount();
    }
    
}
