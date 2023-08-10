package com.example.Boards.Requestes;

import com.example.Boards.Models.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    Integer id;
    String title;
    Map<Integer, String> columns;

    public Board getBoardResponse(){
        Board board = new Board();

        board.setId(this.getId());
        board.setTitle(this.getTitle());
        board.setColumns(this.getColumns());

        return board;
    }
}
