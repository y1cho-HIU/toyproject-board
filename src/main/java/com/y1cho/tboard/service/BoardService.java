package com.y1cho.tboard.service;

import com.y1cho.tboard.entity.Board;
import com.y1cho.tboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void update(Long id, Board updateParam){
        Board findBoard = boardRepository.getById(id);
        findBoard.setTitle(updateParam.getTitle());
        findBoard.setContent(updateParam.getContent());
        boardRepository.save(findBoard);
    }

}
