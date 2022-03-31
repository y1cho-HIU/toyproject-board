package com.y1cho.tboard.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    public Board(){
    }
}
