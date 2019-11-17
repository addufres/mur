package com.tdg.mur.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

	private Long postId;
	private String threadName;
	@NotBlank(message = "Post name is required.")
	private String postName;
	private String url;
	private String description;
}
