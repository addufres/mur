package com.tdg.mur.service;

import static com.tdg.mur.util.Constants.THREAD_NOT_FOUND_WITH_ID;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tdg.mur.dto.PostResponse;
import com.tdg.mur.dto.ThreadDto;
import com.tdg.mur.exception.ThreadNotFoundException;
import com.tdg.mur.model.Thread;
import com.tdg.mur.repos.PostRepository;
import com.tdg.mur.repos.ThreadRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ThreadService {

	private final ThreadRepository threadRepo;
	private final PostRepository postRepo;
	private final PostService postService;
	private final AuthService authService;
	
	@Transactional(readOnly = true)
	public List<ThreadDto> getAll() {
		return threadRepo.findAll()
				.stream()
				.map(this::createThreadDto)
				.collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<PostResponse> getAllPosts(Long threadId) {
		Thread t = threadRepo.findById(threadId)
                .orElseThrow(() -> new ThreadNotFoundException(THREAD_NOT_FOUND_WITH_ID + threadId));
        return postRepo.findAllByThread(t)
                .stream()
                .map(postService::createPostDto)
                .collect(Collectors.toList());
	}
	
	public ThreadDto getThread(Long threadId) {
		Thread t = threadRepo.findById(threadId)
				.orElseThrow(() -> new ThreadNotFoundException(THREAD_NOT_FOUND_WITH_ID + threadId));
		log.info("The thread coming back is: " + t.getDescription());
		return createThreadDto(t);
	}
	
	@Transactional
	public ThreadDto save(ThreadDto threadDto) {
		Thread t = threadRepo.save(createThread(threadDto));
		threadDto.setId(t.getId());
		return threadDto;
	}
	
	private ThreadDto createThreadDto(Thread thread) {
		return ThreadDto.builder()
				.name(thread.getName())
				.description(thread.getDescription())
				.id(thread.getId())
				.postSum(thread.getPosts().size())
				.build();
	}
	
	private Thread createThread(ThreadDto threadDto) {
        return Thread.builder()
        		.name("/mu/" + threadDto.getName())
                .description(threadDto.getDescription())
                .user(authService.getCurrentUser())
                .created(Instant.now())
                .build();
	}
}
