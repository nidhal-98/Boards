package com.example.Boards.Repositories;

import com.example.Boards.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Integer> {

    List<Card> findByBoardId(Integer boardID);

    Card findByIdAndBoardId(Integer cardID, Integer boardID);

}
