package com.y1cho.tboard.service;

import com.y1cho.tboard.dto.BoardDto;
import com.y1cho.tboard.entity.Board;
import com.y1cho.tboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    // in this case, Data Access Object == BoardService
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void savePost(BoardDto boardDto){
        boardRepository.save(boardDto.toEntity());
    }

    @Transactional
    public List<BoardDto> getBoardList(){
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList){
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .createdTime(board.getCreatedTime())
                    .updatedTime(board.getUpdatedTime())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id){
        Board findBoard = boardRepository.findById(id).get();
        BoardDto boardDto = BoardDto.builder()
                .id(findBoard.getId())
                .title(findBoard.getTitle())
                .content(findBoard.getContent())
                .writer(findBoard.getWriter())
                .createdTime(findBoard.getCreatedTime())
                .updatedTime(findBoard.getUpdatedTime())
                .Hit_Count(findBoard.getHit_Count() + 1)
                .build();

        boardRepository.save(boardDto.toEntity());
        return boardDto;
    }

    @Transactional
    public void updatePost(Long id, BoardDto boardDto){
        BoardDto newDto = BoardDto.builder()
                .id(id)
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .createdTime(boardDto.getCreatedTime())
                .updatedTime(LocalDateTime.now())
                .Hit_Count(boardDto.getHit_Count())
                .build();
        boardRepository.save(newDto.toEntity());
    }

    public void delete(Long id){
        boardRepository.deleteById(id);
    }

}
