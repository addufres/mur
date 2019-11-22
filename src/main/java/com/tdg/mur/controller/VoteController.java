package com.tdg.mur.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tdg.mur.dto.PostResponse;
import com.tdg.mur.dto.VoteDto;
import com.tdg.mur.service.VoteService;
import static com.tdg.mur.util.ApiPaths.VOTES;
import static com.tdg.mur.util.ApiPaths.VOTE;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(VOTES)
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping(VOTE)
    public PostResponse vote(@Valid @RequestBody VoteDto voteDto) {
        return voteService.vote(voteDto);
    }
	
}
