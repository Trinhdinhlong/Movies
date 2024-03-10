package com.mocktest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.Movie;
import com.mocktest.entities.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDto {
    private Long id;
    private String content;
    private String movieNameEnglish;
    private String movieNameVN;
    private String actor;
    private String director;
    private int duration;
    private String movieProductionCompany;
    private LocalDateTime startedDate;
    private LocalDateTime endDate;
    private String imageURL;
    private LocalDateTime createdTimDate;
    private Set<Type> typeMovies;
    public MovieDto(Movie entity) {
        if (entity != null) {
            BeanUtils.copyProperties(entity, this);
        }
    }
}
