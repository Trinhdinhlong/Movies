package com.mocktest.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {
    private Long id;
    private String movieNameEnglish;
    private String movieNameVN;
    private LocalDate createDate;
    private String movieProductionCompany;
    private int duration;
    private String version;
}
