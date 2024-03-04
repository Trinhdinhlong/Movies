package com.mocktest.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "movies", schema = "dbo")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private String id;

    @Column(name = "content")
    private String content;

    @Column(name = "movie_name_english")
    private String movieNameEnglish;

    @Column(name = "movie_name_vn")
    private String movieNameVN;

    @Column(name = "actor")
    private String actor;

    @Column(name = "director")
    private String director;

    @Column(name = "duration")
    private int duration;

    @Column(name = "movie_production_company")
    private String movieProductionCompany;

    @Column(name = "started_date")
    private LocalDateTime startedDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "image_url")
    private String imageURL;


}
