package com.example.Boards.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@Table(name = "Cards")
public class Card extends BaseEntity{
    //ID
    //title

    String description;

    Integer section;
    @ManyToOne
    @JoinColumn(name = "board_id")
    Board board;
}
