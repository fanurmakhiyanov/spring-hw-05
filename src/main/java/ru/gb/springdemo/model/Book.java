package ru.gb.springdemo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Book implements Serializable{

    @Id
    private long id;
    private String name;
}