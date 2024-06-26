package com.sparta.andbackoffice.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ContestRequestDto {
	// 공모전 게시글의 카테고리는 1번으로 고정된 것을 상정함
	private String target;
	private String title;
	private String company;
	private String prize;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime endDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime startDate;

	private String homepage;
	private String contents;
	private String category;
	private List<String> bottomCategory;
	private List<MultipartFile> files;
}