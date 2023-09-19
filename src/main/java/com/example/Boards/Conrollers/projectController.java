//package com.example.Boards.Conrollers;
//
//import com.example.Boards.Models.Board;
//import com.example.Boards.Models.Card;
//import com.example.Boards.Repositories.CardRepo;
//import com.example.Boards.Responses.BoardResponse;
//import com.example.Boards.Responses.CardResponse;
//import com.example.Boards.Services.BoardService;
//import com.example.Boards.Services.CardService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping(path = "/api/boards")
//public class projectController {
//
//    @Autowired
//    BoardService boardService;
//
//    @Autowired
//    CardService cardService;
//
//    @PostMapping
//    BoardResponse newBoard(@RequestBody Board board){
//        boardService.newBoard(board);
//        BoardResponse boardResponse = new BoardResponse();
//        boardResponse.setId(board.getId());
//        boardResponse.setTitle(board.getTitle());
//        boardResponse.setColumns(board.getColumns());
//        return boardResponse;
//    }
//
//    @GetMapping
//    List<BoardResponse> getAllBoards(){
//        List<Board> boardList = boardService.getAllBoard();
//        List<BoardResponse> boardResponseList = new ArrayList<>();
//
//        for (Board board : boardList){
//            BoardResponse boardResponse = new BoardResponse();
//
//            boardResponse.setId(board.getId());
//            boardResponse.setTitle(board.getTitle());
//            boardResponse.setColumns(board.getColumns());
//            boardResponseList.add(boardResponse);
//        }
//        return boardResponseList;
//    }
//
//    @PutMapping("/{id}")
//    String updateBoard(@PathVariable("id") Integer id, @RequestBody Board updatedBoard){
//        Board board;
//        board = boardService.getBoardById(id);
//        board.setTitle(updatedBoard.getTitle());
//
//        boardService.saveBoard(board);
//        return "Updated Successfully";
//    }
//
//    @DeleteMapping("/{id}")
//    String deleteBoard(@PathVariable("id") Integer id){
//        boardService.deleteBoard(id);
//        return "Board with ID " + id + " has been deleted successfully";
//    }
//
////    Cards
//
//    @PostMapping("/{board_id}/cards")
//    CardResponse newCard(@PathVariable("board_id") Integer boardID, @RequestBody Card card){
//        cardService.newCard(boardID, card);
//
//        CardResponse cardResponse = new CardResponse();
//        cardResponse.setId(card.getId());
//        cardResponse.setTitle(card.getTitle());
//        cardResponse.setDescription(card.getDescription());
//        cardResponse.setSection(card.getSection());
//
//        return cardResponse;
//    }
//
//    @GetMapping("/{board_id}/cards")
//    List<CardResponse> getAllCards(@PathVariable("board_id") Integer boardID){
//        List<Card> cardList = cardService.getAllCardsInBoard(boardID);
//        List<CardResponse> cardResponseList = new ArrayList<>();
//
//        for (Card card : cardList){
//            CardResponse cardResponse = new CardResponse();
//            cardResponse.setId(card.getId());
//            cardResponse.setTitle(card.getTitle());
//            cardResponse.setDescription(card.getDescription());
//            cardResponse.setSection(card.getSection());
//
//            cardResponseList.add(cardResponse);
//        }
//
//        return cardResponseList;
//    }
//
//    @GetMapping("{board_id}/cards/{card_id}")
//    CardResponse getSpecificCard(@PathVariable("board_id") Integer boardID, @PathVariable("card_id") Integer cardID){
//        Card card = cardService.getSpecificCard(cardID, boardID);
//        CardResponse cardResponse = new CardResponse();
//
//        cardResponse.setId(card.getId());
//        cardResponse.setTitle(card.getTitle());
//        cardResponse.setDescription(card.getDescription());
//        cardResponse.setSection(card.getSection());
//
//        return cardResponse;
//    }
//
//    @PutMapping("{board_id}/cards/{card_id}")
//    CardResponse updateSpecificCard(@PathVariable("board_id") Integer boardID, @PathVariable("card_id") Integer cardID, @RequestBody Card upadteCard){
//        Card card;
//        card = cardService.getSpecificCard(cardID, boardID);
//        card.setTitle(upadteCard.getTitle());
//        card.setDescription(upadteCard.getDescription());
//        card.setSection(upadteCard.getSection());
//        cardService.saveCard(card);
//
//        CardResponse cardResponse = new CardResponse();
//        cardResponse.setId(card.getId());
//        cardResponse.setTitle(upadteCard.getTitle());
//        cardResponse.setDescription(upadteCard.getDescription());
//        cardResponse.setSection(upadteCard.getSection());
//
//        return cardResponse;
//    }
//
//    @DeleteMapping("{board_id}/cards/{card_id}")
//    String deleteCard(@PathVariable("board_id") Integer boardID, @PathVariable("card_id") Integer cardID){
//        cardService.deleteCard(cardID, boardID);
//        return "Card with ID " + cardID + " has been deleted successfully from board " + boardID;
//    }
//
//}
