package com.sparta.andbackoffice.dto.response;

import com.sparta.andbackoffice.entity.Contest;
import com.sparta.andbackoffice.entity.ContestStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ContestResponseDto {
	private String target;
	private String title;
	private String company;
	private LocalDateTime endDate;
	private LocalDateTime startDate;
	private String status;
	private String homepage;
	private String contents;
	private String prize;
	private Long contestViews;

	public ContestResponseDto(Contest contest) {
		this.target = contest.getTarget();
		this.title = contest.getTitle();
		this.company = contest.getCompany();
		this.endDate = contest.getEndDate();
		this.startDate = contest.getStartDate();
		this.status = contest.getStatus().getDisplayText();
		this.homepage = contest.getHomepage();
		this.contents = contest.getContents();
		this.contestViews = contest.getContestViews();
		this.prize = contest.getPrize();
	}
}