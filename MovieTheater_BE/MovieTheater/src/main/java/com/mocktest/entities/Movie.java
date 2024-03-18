package com.mocktest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
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
    @Size(min = 1, max = 1000, message = "Content must be between 1 and 255 characters")
    private String content;
    @Column(name = "movie_name_english")
    @NotNull(message = "The movie name english is not blank!")
    @Pattern(regexp = "^[^\\p{L}\\d]*$", message = "Movie name English must not contain characters")
    private String movieNameEnglish;
    @Column(name = "movie_name_vn")
    @NotNull(message = "The movie name vn is not blank!")
    @Pattern(regexp = "^[^\\p{L}\\d]*$", message = "Movie name VN must not contain characters")
    private String movieNameVN;
    @Column(name = "actor")
    @NotNull(message = "The actor is not blank!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Actor must contain only letters and spaces")
    private String actor;
    @Column(name = "director")
    @NotNull(message = "The director is not blank!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Director must contain only letters and spaces")
    private String director;
    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Max(value = 300, message = "Duration cannot exceed 300 minutes")
    @Column(name = "duration")
    private int duration;
    @Column(name = "movie_production_company")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Movie production company must contain only letters and spaces")
    private String movieProductionCompany;
    @Column(name = "started_date")
    @FutureOrPresent(message = "Start time must be in the present or future")
    private LocalDate startedDate;
    @Column(name = "end_date")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;
    @Column(name = "image_url")
    private String imageURL;
    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
    @Column(name = "version")
    @Pattern(regexp = "^(2D|3D)$", message = "Version must be either '2D' or '3D'")
    private String version;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_type",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Set<TypeMovie> typeMovies = new HashSet<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShowTime> showTimes;
    @AssertTrue(message = "End date must be after start date")
    private boolean isValidEndDate() {
        return endDate.isAfter(startedDate);
    }
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
                '}';
    }
}
