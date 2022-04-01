package com.y1cho.tboard.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

/*    @Column(nullable = false)
    private Date createdTime;

    @Column(nullable = false)
    private Date updatedTime;

    @Column(nullable = false)
    private Integer hit_num;*/

    public Board(){
    }
}
