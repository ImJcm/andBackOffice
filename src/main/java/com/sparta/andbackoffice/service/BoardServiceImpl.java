package com.sparta.andbackoffice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.andbackoffice.dto.request.BoardRequestDto;
import com.sparta.andbackoffice.dto.response.ApiResponseDto;
import com.sparta.andbackoffice.dto.response.BoardResponseDto;
import com.sparta.andbackoffice.entity.Board;
import com.sparta.andbackoffice.entity.BoardCategory;
import com.sparta.andbackoffice.repository.BoardCategoryRepository;
import com.sparta.andbackoffice.repository.BoardRepository;
import com.sparta.andbackoffice.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j(topic = "BoardService")
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardRepository boardRepository;
	private final CategoryRepository categoryRepository;
	private final BoardCategoryRepository boardCategoryRepository;

	@Override
	public BoardResponseDto getBoard(Long boardId) {
		Board board = findBoard(boardId);
		return new BoardResponseDto(board);
	}

	@Override
	public List<BoardResponseDto> getAllBoards() {
		List<Board> boards = boardRepository.findAll();
		return boards.stream().map(BoardResponseDto::new).collect(Collectors.toList());
	}

	@Override
	public BoardResponseDto createBoard(BoardRequestDto requestDto) {
		log.info("Service - createBoard : 시작");

		findCategory(requestDto.getCategoryId());
		Board board = boardRepository.save(new Board(requestDto));

		log.info("Service - createBoard : 끝");
		return new BoardResponseDto(board);
	}


	@Override
	@Transactional
	public BoardResponseDto modifyBoard(Long id, BoardRequestDto requestDto) {
		log.info("Service - modifyBoard : 시작");

		Board board = findBoard(id);
		board.setTitle(requestDto.getTitle());
		board.setContents(requestDto.getContents());

		log.info("Service - modifyBoard : 끝");
		return new BoardResponseDto(board);
	}

	@Override
	@Transactional
	public ApiResponseDto deleteBoard(Long id) {
		log.info("Service - deleteBoard : 시작");

		Board board = findBoard(id);
		boardRepository.delete(board);

		log.info("Service - deleteBoard : 끝");
		return new ApiResponseDto("게시글 삭제 완료", HttpStatus.OK.value());
	}

	@Override
	public void findCategory(Long categoryId) {
		boardCategoryRepository.findById(categoryId).orElseThrow(
				() -> new IllegalArgumentException("존재하지 않는 카테고리입니다.")
		);
	}

	@Override
	public Board findBoard(Long boardId) {
		return boardRepository.findById(boardId).orElseThrow(
			() -> new IllegalArgumentException("존재하지 않는 글입니다.")
		);
	}

	@Override
	public void equalsCategory(Long categoryId, Board board) {
		BoardCategory boardCategory = boardCategoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("해당 게시글 카테고리가 존재하지 않습니다."));
		if (!(board.getCategory().equals(boardCategory))) {
			throw new IllegalArgumentException("카테고리가 일치하는지 다시 확인해주세요.");
		}
	}
}
