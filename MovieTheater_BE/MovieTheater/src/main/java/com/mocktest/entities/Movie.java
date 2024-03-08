package com.mocktest.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    private Long id;

    @Column(name = "content")
    @NotNull(message = "The content is not blank!")
    private String content;

    @Column(name = "movie_name_english")
    @NotNull(message = "The movie name english is not blank!")
    private String movieNameEnglish;

    @Column(name = "movie_name_vn")
    @NotNull(message = "The movie name vn is not blank!")
    private String movieNameVN;

    @Column(name = "actor")
    @NotNull(message = "The actor is not blank!")
    private String actor;

    @Column(name = "director")
    @NotNull(message = "The director is not blank!")
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

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @ManyToMany
    @JoinTable(
            name = "movie_type",
            joinColumns = @JoinColumn(name = "movie_id_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<Type> typeMovies;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ShowTime> showTimes;
}
