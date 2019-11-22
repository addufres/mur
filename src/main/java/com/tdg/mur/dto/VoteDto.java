package com.tdg.mur.dto;

import com.tdg.mur.model.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
	
	private VoteType voteType; // 0 is UPVOTE 1 is DOWNVOTE
	private Long postId;
}
