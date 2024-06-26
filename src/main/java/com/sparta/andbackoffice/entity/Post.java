package com.sparta.andbackoffice.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.sparta.andbackoffice.dto.request.PostRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "posts")
@DynamicInsert
@NoArgsConstructor
public class Post extends TimeStamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "contents", nullable = false)
	private String contents;

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<ReportPost> reportPostList = new ArrayList<>();

	@Column(name = "communityPostViews")
	@ColumnDefault("0")
	// 조회수 디폴트 값을 0으로 주긴 했는데 좋아요 카운트 했던 것처럼 증가, 감소 메서드를 만들어야 하는 건지 잘 모르겠어요...
	private Long postviews;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setPostviews(Long postviews) {
		this.postviews = postviews;
	}

	public Post(PostRequestDto requestDto, User user) {
		this.title = requestDto.getTitle();
		this.contents = requestDto.getContents();
		this.user = user;
	}
}
