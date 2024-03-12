package com.mocktest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    private LocalDate startedDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "image_url")
    private String imageURL;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @ManyToMany
    @JoinTable(
            name = "movie_type",
            joinColumns = @JoinColumn(name = "movie_id_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    @JsonIgnore
    private Set<Type> typeMovies;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowTime> showTimes;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", movieNameEnglish='" + movieNameEnglish + '\'' +
                ", movieNameVN='" + movieNameVN + '\'' +
                ", actor='" + actor + '\'' +
                ", director='" + director + '\'' +
                ", duration=" + duration +
                ", movieProductionCompany='" + movieProductionCompany + '\'' +
                ", startedDate=" + startedDate +
                ", endDate=" + endDate +
                ", imageURL='" + imageURL + '\'' +
                ", createdDate=" + createdDate +
                ", updatedTime=" + updatedTime +
                ", typeMovies=" + typeMovies +
                '}';
    }
}
