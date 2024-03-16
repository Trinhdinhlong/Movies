package com.mocktest.dto;

import com.fasterxml.jackson.annotation.*;
import com.mocktest.entities.Movie;
import com.mocktest.entities.TypeMovie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MovieDto {
    private Long id;
    private String content;
    private String movieNameEnglish;
    private String movieNameVN;
    private String actor;
    private String director;
    private int duration;
    private String movieProductionCompany;
    private LocalDate startedDate;
    private LocalDate endDate;
    private String imageURL;

    @JsonIgnore
    private Set<TypeMovie> typeMovies;
    private List<Long> typeMovieId;

    public MovieDto(Movie entity) {
        if (entity != null) {
            BeanUtils.copyProperties(entity, this);
    }
    }
    @Override
    public String toString() {
        return "MovieDto{" +
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
                '}';
    }
}
