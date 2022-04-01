package com.y1cho.tboard.controller;

import com.y1cho.tboard.entity.Board;
import com.y1cho.tboard.repository.BoardRepository;
import com.y1cho.tboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("boards", boardList);
        return "/board/newList";
    }

    @GetMapping("/entity/{boardId}")
    public String board(@PathVariable Long boardId, Model model){
        Board board = boardRepository.getById(boardId);
        model.addAttribute("board", board);
        return "/board/newEntity";
    }

    @GetMapping("/add")
    public String addForm(){
        return "/board/newAddForm";
    }

    @PostMapping("/add")
    public String addBoard(Board board){
        System.out.println("ADDED");
        boardRepository.save(board);
        return "redirect:/board/list";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model){
        Board board = boardRepository.getById(boardId);
        model.addAttribute("board", board);
        return "/board/newEditForm";
    }

    @PostMapping("/{boardId}/edit")
    public String editBoard(@PathVariable Long boardId, @ModelAttribute Board board){
        boardService.update(boardId, board);
        return "redirect:/board/entity/{boardId}";
    }

    @GetMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable Long boardId){
        //boardService.delete(boardId);
        boardRepository.deleteById(boardId);
        return "redirect:/board/list";
    }
}
