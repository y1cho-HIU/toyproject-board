package com.y1cho.tboard.controller;

import com.y1cho.tboard.dto.BoardDto;
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
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model){
        List<BoardDto> boardList = boardService.getBoardList();
        model.addAttribute("boards", boardList);
        return "/board/newList";
    }

    @GetMapping("/entity/{boardId}")
    public String board(@PathVariable Long boardId, Model model){
        BoardDto boardDto = boardService.getPost(boardId);
        model.addAttribute("board", boardDto);
        return "/board/newEntity";
    }

    @GetMapping("/add")
    public String addForm(){
        return "/board/newAddForm";
    }

    @PostMapping("/add")
    public String addBoard(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/board/list";
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@PathVariable Long boardId, Model model){
        BoardDto boardDto = boardService.getPost(boardId);
        model.addAttribute("board", boardDto);
        return "/board/newEditForm";
    }

    @PostMapping("/{boardId}/edit")
    public String editBoard(@PathVariable Long boardId, @ModelAttribute BoardDto boardDto){
        boardService.updatePost(boardId, boardDto);
        return "redirect:/board/entity/{boardId}";
    }

    @GetMapping("/{boardId}/delete")
    public String deleteBoard(@PathVariable Long boardId){
        boardService.delete(boardId);
        return "redirect:/board/list";
    }
}
