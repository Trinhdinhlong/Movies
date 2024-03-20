package com.mocktest.services;

import com.mocktest.bean.MovieDetailResponse;
import com.mocktest.bean.MovieResponse;
import com.mocktest.bean.MovieShowTimeResponse;
import com.mocktest.bean.MovieRequest;
import com.mocktest.entities.Movie;
import com.mocktest.entities.ShowTime;
import com.mocktest.entities.TypeMovie;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.MovieRepository;
import com.mocktest.repository.TypeRepository;
import com.mocktest.until.ParseTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ShowTimeService showTimeService;

    public Page<Movie> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        if (!moviePage.hasContent()) {
            throw new NotFoundException("No data to display!");
        }
        return moviePage;
    }
    public List<MovieShowTimeResponse> getAll() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream().map(data -> new MovieShowTimeResponse(data))
                .collect(Collectors.toList());
    }

    public List<MovieShowTimeResponse> getAll(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        LocalTime time = ParseTime.convertToTime(dateTime);

        List<Movie> movieList = movieRepository.findAll();
        LocalDate dateNow = dateTime.toLocalDate();
        movieList  = movieList.stream()
                .filter( movie -> {
                    return ((movie.getStartedDate().isBefore(dateNow)) && (movie.getEndDate().isAfter(dateNow)));
                })
                .map(data -> {
                    List<ShowTime> showTimes = data.getShowTimes();
                    if(!CollectionUtils.isEmpty(showTimes)) {
                        data.setShowTimes(showTimes.stream().filter(showTime -> {
                            return !(showTime.getStartTime()).isBefore(time);
                        }).collect(Collectors.toList()));
                    }
                    return data;
                }).collect(Collectors.toList());

        return movieList.stream().filter(movie -> movie.getShowTimes().size() > 0)
                .map(data -> new MovieShowTimeResponse(data))
                .collect(Collectors.toList());
    }
    public List<MovieResponse> getAllMovieByAdmin(){
        List<Movie> movieList = movieRepository.findAll();
        List<MovieResponse> movieResponses = new ArrayList<>();
        for(Movie movie : movieList){
            MovieResponse movieResponse = new MovieResponse();
            movieResponse.setId(movie.getId());
            movieResponse.setMovieNameEnglish(movie.getMovieNameEnglish());
            movieResponse.setMovieNameVN(movie.getMovieNameVN());
            movieResponse.setStartedDate(movie.getStartedDate());
            movieResponse.setMovieProductionCompany(movie.getMovieProductionCompany());
            movieResponse.setDuration(movie.getDuration());
            movieResponse.setVersion(movie.getVersion());
            movieResponses.add(movieResponse);
        }
        return movieResponses;
    }
    public void deleteById(Long request){
        if (movieRepository.existsById(request)) {
            movieRepository.deleteById(request);
        }else {
            throw new NotFoundException("data not found in entity User: " + request);
        }
    }
    public MovieResponse create(MovieRequest request){
        Set<TypeMovie> typeMovies = new HashSet<>();
        for (Long typeId : request.getTypeMovieId()) {
            TypeMovie type = typeRepository.findById(typeId)
                    .orElseThrow(() -> new NotFoundException("Not found id type!"));
            if (type != null) {
                typeMovies.add(type);
            }
        }

        Movie movie = new Movie();
        movie.setTypeMovies(typeMovies);
        BeanUtils.copyProperties(request, movie);
        movieRepository.save(movie);
        movie.setShowTimes(showTimeService.createShowTime(request, movie));
        return MovieResponse.builder()
                .id(movie.getId())
                .content(movie.getContent())
                .movieNameEnglish(movie.getMovieNameEnglish())
                .movieNameVN(movie.getMovieNameVN())
                .actor(movie.getActor())
                .director(movie.getDirector())
                .duration(movie.getDuration())
                .movieProductionCompany(movie.getMovieProductionCompany())
                .startedDate(movie.getStartedDate())
                .endDate(movie.getEndDate())
                .imageURL(movie.getImageURL())
                .typeMovies(movie.getTypeMovies())
                .showTimes(movie.getShowTimes())
                .build();
    }
    public MovieRequest updateById(MovieRequest request){
        Optional<Movie> userOptional = movieRepository.findById(request.getId());
        MovieRequest response = new MovieRequest();
        BeanUtils.copyProperties(userOptional, response);
        return response;
    }


    public List<Type> getMovieTypes() {
        List<Type> types = typeRepository.findAll();
        return types;

    public Movie getById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieOptional, movie);
        return movie;
    }
    public Map<String, List<MovieDetailResponse>> getAllByCategories() {
        List<Movie> movieList = movieRepository.findAll();
        LocalDate currentDate = LocalDate.now();
        List<Movie> filteredMovies = movieList.stream()
                .filter(movie -> movie.getEndDate().isAfter(currentDate) || movie.getEndDate().isEqual(currentDate))
                .collect(Collectors.toList());
        Map<TypeMovie, List<Movie>> categorizedMovies = filteredMovies.stream()
                .flatMap(movie -> movie.getTypeMovies().stream().map(typeMovie -> new AbstractMap.SimpleEntry<>(typeMovie, movie)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
        categorizedMovies.forEach((typeMovie, movies) -> {
            movies.sort(Comparator.comparing(Movie::getStartedDate).reversed());
        });
        Map<String, List<MovieDetailResponse>> categorizedResponses = new HashMap<>();
        categorizedMovies.forEach((typeMovie, movies) -> {
            List<MovieDetailResponse> movieResponses = movies.stream().map(movie -> {
                MovieDetailResponse movieResponse = new MovieDetailResponse();
                movieResponse.setId(movie.getId());
                movieResponse.setMovieNameEnglish(movie.getMovieNameEnglish());
                movieResponse.setMovieNameVN(movie.getMovieNameVN());
                movieResponse.setActor(movie.getActor());
                movieResponse.setDirector(movie.getDirector());
                movieResponse.setDuration(movie.getDuration());
                movieResponse.setMovieProductionCompany(movie.getMovieProductionCompany());
                movieResponse.setStartedDate(movie.getStartedDate());
                movieResponse.setEndDate(movie.getEndDate());
                movieResponse.setImageURL(movie.getImageURL());
                movieResponse.setVersion(movie.getVersion());
                return movieResponse;
            }).collect(Collectors.toList());
            categorizedResponses.put(typeMovie.getTypeName(), movieResponses);
        });
        return categorizedResponses;

    }
}
