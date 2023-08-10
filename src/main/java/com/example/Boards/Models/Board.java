package com.example.Boards.Models;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Data
@Table(name = "Boards")
public class Board extends BaseEntity{
    //ID
    //title

    @Convert(converter = HashMapConverter.class)
    Map<Integer, String> columns = Map.of(
            1, "To do",
            2, "In progress",
            3, "Done"
    );

    @OneToMany(mappedBy = "board")
    List<Card> cards;
}
