package com.mocktest.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDetailRequest {
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
    private List<Long> typeMovieId;
    private List<Long> showTimeId;
    private Long roomId;
}
