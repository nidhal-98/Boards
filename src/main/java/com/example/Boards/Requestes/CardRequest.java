package com.example.Boards.Requestes;

import com.example.Boards.Models.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {
    Integer id;
    String title;
    String description;
    Integer section;

    public Card getCardResponse(){
        Card card = new Card();

        card.setId(this.getId());
        card.setTitle(this.getTitle());
        card.setSection(this.getSection());

        return card;
    }
}
