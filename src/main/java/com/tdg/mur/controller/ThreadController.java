package com.tdg.mur.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdg.mur.dto.PostResponse;
import com.tdg.mur.dto.ThreadDto;
import com.tdg.mur.service.ThreadService;

import static com.tdg.mur.util.ApiPaths.THREAD_API_MAPPING;
import static com.tdg.mur.util.ApiPaths.CREATE;
import static com.tdg.mur.util.ApiPaths.QUERY_ID;
import static com.tdg.mur.util.ApiPaths.QUERY_ALL;
import static com.tdg.mur.util.ApiPaths.ID_POSTS_ALL;

@RestController
@RequestMapping(THREAD_API_MAPPING)
@AllArgsConstructor
public class ThreadController {

	private final ThreadService threadService;
	
	@GetMapping(QUERY_ID)
	public ThreadDto getThread(@PathVariable Long threadId) {
		return threadService.getThread(threadId);
	}
	
	@GetMapping(QUERY_ALL)
	public List<ThreadDto> getAllThreads() {
		return threadService.getAll();
	}
	
	@GetMapping(ID_POSTS_ALL)
	public List<PostResponse> getAllPostsInThread(@PathVariable Long threadId) {
		return threadService.getAllPosts(threadId);
	}
	
	@PostMapping(CREATE)
	public ThreadDto create(@Valid @RequestBody ThreadDto threadDto) {
		return threadService.save(threadDto);
	}
}
