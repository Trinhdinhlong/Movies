package com.mocktest.services;

import com.mocktest.bean.*;
import com.mocktest.entities.Movie;
import com.mocktest.entities.ShowTime;
import com.mocktest.entities.Ticket;
import com.mocktest.entities.TypeMovie;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Autowired
    private ShowTimeService showTimeService;
    @Autowired
    private TicketService ticketService;
    public Movie UpdateMovie(MovieRequest request) {
        Movie movie = movieRepository.getById(request.getId());
        Set<TypeMovie> typeMovies = new HashSet<>();
        for (Long typeId : request.getTypeMovieId()) {
            TypeMovie type = typeRepository.findById(typeId)
                    .orElseThrow(() -> new NotFoundException("Not found id type!"));
            if (type != null) {
                typeMovies.add(type);
            }
        }
        movie.setMovieNameVN(request.getMovieNameVN());
        movie.setMovieNameEnglish(request.getMovieNameEnglish());
        movie.setContent(request.getContent());
        movie.setActor(request.getActor());
        movie.setDirector(request.getDirector());
        movie.setDuration(request.getDuration());
        movie.setMovieProductionCompany(request.getMovieProductionCompany());
        movie.setStartedDate(request.getStartedDate());
        movie.setEndDate(request.getEndDate());
        movie.setVersion(request.getVersion());
        movie.setImageURL(request.getImageURL());
        movie.setTypeMovies(typeMovies);
        if (request.getRoomId() != null) {
            Set<LocalTime> requestedTimes = new HashSet<>(request.getStartTime());
            List<ShowTime> listShowTime = showTimeRepository.getAllShowTimeById(movie.getId());
            for(ShowTime showTime : listShowTime){
                Ticket ticket = ticketService.getTicketHasActiveTrue(showTime.getId());
                if(ticket != null){
                    throw new BadRequestException("BAD_REQUEST " + showTime.getStartTime());
                }
            }
            listShowTime.forEach(showTime -> {
                if (!requestedTimes.contains(showTime.getStartTime())) {
                    showTimeService.DeleteById(showTime.getId());
                }
            });
            for (LocalTime time : request.getStartTime()) {
                boolean timeExists = false;
                for (ShowTime showTime : listShowTime) {
                    if (time.equals(showTime.getStartTime())) {
                        timeExists = true;
                        break;
                    }
                }
                if (!timeExists) {
                    ShowTime showTimeSaved = new ShowTime();
                    showTimeSaved.setRoom(roomRepository.getById(request.getRoomId()));
                    showTimeSaved.setStartTime(time);
                    showTimeSaved.setEndTime(time.plusMinutes(movie.getDuration()));
                    showTimeSaved.setMovie(movie);
                    showTimeRepository.save(showTimeSaved);
                }
            }
        }
        return movieRepository.save(movie);
    }

    public List<MovieShowTimeResponse> getAll() {
        List<Movie> list = movieRepository.findAll(LocalDate.now());
        List<Movie> movies = new ArrayList<>();
        for (Movie movie :list){
            if(movie.getActive().equals("1")){
                movies.add(movie);
            }
        }
        List<ShowTime> showTimes = showTimeRepository.findAllAfterCurrentTime(LocalTime.now());
        List<MovieShowTimeResponse> responses = new ArrayList<>();
        for(Movie movie : movies){
            MovieShowTimeResponse response = new MovieShowTimeResponse();
            response.setId(movie.getId());
            response.setMovieNameEnglish(movie.getMovieNameEnglish());
            response.setMovieNameVN(movie.getMovieNameVN());
            response.setImageURL(movie.getImageURL());
            response.setStartDate(movie.getStartedDate());
            response.setEndDate(movie.getEndDate());
            response.setCreatedDate(movie.getCreatedDate());
            List<ShowTime> saved = new ArrayList<>();
            Long roomId = 0L;
            for(ShowTime showTime : showTimes){
                if (response.getId() == showTime.getMovie().getId()){
                    saved.add(showTime);
                    roomId = showTime.getRoom().getId();
                }
            }
            saved.sort(Comparator.comparing(ShowTime::getStartTime));
            response.setRoomId(roomId);
            if(response.getRoomId() != 0) {
                response.setShowTimes(saved);
                responses.add(response);
            }
        }
        responses.sort(Comparator.comparing(MovieShowTimeResponse::getCreatedDate).reversed());
        if(responses.isEmpty()){
            throw new NotFoundException("No Data Found");
        }
        return responses;
    }
    public List<MovieResponse> getAllMovieByAdmin(){
        List<Movie> list = movieRepository.findAll(LocalDate.now());
        List<Movie> movieList = new ArrayList<>();
        for (Movie movie :list){
            if(movie.getActive().equals("1")){
                movieList.add(movie);
            }
        }
        List<MovieResponse> movieResponses = new ArrayList<>();
        for(Movie movie : movieList){
            MovieResponse movieResponse = new MovieResponse();
            movieResponse.setId(movie.getId());
            movieResponse.setContent(movieResponse.getContent());
            movieResponse.setMovieNameEnglish(movie.getMovieNameEnglish());
            movieResponse.setMovieNameVN(movie.getMovieNameVN());
            movieResponse.setActor(movie.getActor());
            movieResponse.setDirector(movieResponse.getDirector());
            movieResponse.setStartedDate(movie.getStartedDate());
            movieResponse.setMovieProductionCompany(movie.getMovieProductionCompany());
            movieResponse.setDuration(movie.getDuration());
            movieResponse.setVersion(movie.getVersion());
            movieResponse.setTypeMovies(movie.getTypeMovies());
            movieResponse.setShowTimes(movie.getShowTimes());
            movieResponse.setImageURL(movie.getImageURL());
            movieResponses.add(movieResponse);
        }
        return movieResponses;
    }
    public void deleteById(Long request){
        if (movieRepository.existsById(request)) {
            Movie movie = movieRepository.getById(request);
            movie.setActive("false");
            movieRepository.save(movie);
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
                .version(movie.getVersion())
                .typeMovies(movie.getTypeMovies())
                .showTimes(movie.getShowTimes())
                .build();
    }
    public List<TypeMovie> getMovieTypes() {
        List<TypeMovie> types = typeRepository.findAll();
        return types;
    }
    public Movie getById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No movie data!"));
        return movie;
    }
    public List<MovieWithCategoryResponse> getAllByCategories() {
        List<Movie> list = movieRepository.findAll(LocalDate.now());
        List<Movie> movieList = new ArrayList<>();
        for (Movie movie :list){
            System.out.println(movie.getActive());
            if(movie.getActive().equals("1")){
                movieList.add(movie);
            }
        }
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
       return categorizedResponses.entrySet().stream().map(entry ->
                        MovieWithCategoryResponse.builder()
                                .categoryName(entry.getKey())
                                .movies(entry.getValue())
                                .build()).collect(Collectors.toList());
    }
}
