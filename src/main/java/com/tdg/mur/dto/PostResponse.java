package com.tdg.mur.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PostResponse {

	private Long id;
	private String postName;
	private String url;
	private String description;
	private Integer voteSum;
	private String userName;
	private boolean upVote;
	private boolean downVote;
	private String threadName;
	private Integer commentSum;
	private String duration;
	
}
