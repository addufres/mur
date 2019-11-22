package com.tdg.mur.controller;

import static com.tdg.mur.util.ApiPaths.COMMENT_API_MAPPING;
import static com.tdg.mur.util.ApiPaths.CREATE;
import static com.tdg.mur.util.ApiPaths.QUERY_COMMENTS_ID;
import static com.tdg.mur.util.ApiPaths.QUERY_COMMENTS_USER;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdg.mur.dto.CommentDto;
import com.tdg.mur.service.CommentService;
import static org.springframework.http.HttpStatus.OK;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(COMMENT_API_MAPPING)
@AllArgsConstructor
public class CommentController {

	private final CommentService commentService;
	
	@GetMapping(QUERY_COMMENTS_ID)
	public List<CommentDto> getAllCommentsForPost(@PathVariable Long postId) {
		return commentService.getCommentByPost(postId);
	}
	
	@GetMapping(QUERY_COMMENTS_USER)
	public List<CommentDto> getAllCommentsByUser(@PathVariable String username) {
		return commentService.getCommentsByUser(username);
	}
	
	@PostMapping(CREATE)
	public ResponseEntity<String> postComment(@RequestBody CommentDto commentDto) {
		commentService.createComment(commentDto);
		return new ResponseEntity<>("Comment posted successfully.", OK);
	}
}
