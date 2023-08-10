package com.example.Boards.Services;

import com.example.Boards.Models.Board;
import com.example.Boards.Models.Card;
import com.example.Boards.Repositories.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    CardRepo cardRepo;

    @Autowired
    BoardService boardService;

    public Card newCard(Integer boardID, Card card){
        Board board = boardService.getBoardById(boardID);
        card.setBoard(board);
        return cardRepo.save(card);
    }

    public List<Card> getAllCardsInBoard(Integer boardID){
        return cardRepo.findByBoardId(boardID);
    }

    public Card getSpecificCard(Integer cardID, Integer boardID){
        return cardRepo.findByIdAndBoardId(cardID, boardID);
    }

    public String saveCard(Card card){
        cardRepo.save(card);
        return "Card has been saved";
    }

    public String deleteCard(Integer cardID, Integer boardID){
        Card card = cardRepo.findByIdAndBoardId(cardID, boardID);
        cardRepo.delete(card);
        return "Card has been deleted";
    }
}
