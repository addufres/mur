package com.tdg.mur.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tdg.mur.dto.PostRequest;
import com.tdg.mur.dto.PostResponse;
import com.tdg.mur.service.PostService;

import java.util.List;
import javax.validation.Valid;


import static org.springframework.http.HttpStatus.OK;
import static com.tdg.mur.util.ApiPaths.*;

@RestController
@RequestMapping(POST_API_MAPPINGS)
@AllArgsConstructor
@Slf4j
public class PostController {

	private final PostService postService;
	
	@GetMapping(QUERY_ID)
	public PostResponse getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}
	
	@GetMapping(QUERY_ALL)
	public List<PostResponse> getAllPosts() {
		return postService.getAllPosts();
	}
	
	@GetMapping(QUERY_BY_THREAD)
	public List<PostResponse> getAllByThreadId(@PathVariable Long threadId) {
		return postService.getAllByThread(threadId);
	}

    @GetMapping(QUERY_BY_USERNAME)
    public List<PostResponse> getAllByUsername(@PathVariable String username) {
        return postService.getAllByUsername(username);
    }
    
    @PostMapping(CREATE)
    public ResponseEntity<String> createPost(@Valid @RequestBody PostRequest postRequest) {
        postService.create(postRequest);
        log.info("New post created: " + postRequest.toString());
        return new ResponseEntity<String>("Post created successfully.", OK);
    }
}
