package com.example.Boards.Services;

import com.example.Boards.Models.Board;
import com.example.Boards.Repositories.BoardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardRepo boardRepo;

    public String newBoard(Board board){
        boardRepo.save(board);
        return "The board has been added";
    }

    public List<Board> getAllBoard(){
        return boardRepo.findAll();
    }

    public Board getBoardById(Integer id){
        return boardRepo.findById(id).get();
    }

    public String saveBoard(Board board){
        boardRepo.save(board);
        return "Board has been saved";
    }

    public String deleteBoard(Integer id){
        boardRepo.deleteById(id);
        return "Board has been deleted";
    }
}
