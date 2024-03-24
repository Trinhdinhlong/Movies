package com.mocktest.services;

import com.mocktest.bean.request.MovieRequest;
import com.mocktest.bean.response.MovieDetailResponse;
import com.mocktest.bean.response.MovieResponse;
import com.mocktest.bean.response.MovieShowTimeResponse;
import com.mocktest.bean.response.MovieWithCategoryResponse;
import com.mocktest.entities.Movie;
import com.mocktest.entities.ShowTime;
import com.mocktest.entities.Ticket;
import com.mocktest.entities.TypeMovie;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.ErrorCode;
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
    private final String REGEX = "^[a-zA-Z\\s]*$"; //Actor must contain only letters and spaces

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
    public MovieResponse UpdateMovie(MovieRequest request) {
        Movie movie = movieRepository.getById(request.getId());
        Set<TypeMovie> typeMovies = new HashSet<>();
        for (Long typeId : request.getTypeMovieId()) {
            TypeMovie type = typeRepository.findById(typeId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_TYPE_NOT_FOUND));
            if (type != null) {
                typeMovies.add(type);
            }
        }
        BeanUtils.copyProperties(request, movie);
        movie.setTypeMovies(typeMovies);
        if (request.getRoomId() != null) {
            Set<LocalTime> requestedTimes = new HashSet<>(request.getStartTime());
            List<ShowTime> listShowTime = showTimeRepository.getAllShowTimeById(movie.getId());
            for(ShowTime showTime : listShowTime){
                Ticket ticket = ticketService.getTicketHasActiveTrue(showTime.getId());
                if(ticket != null){
                    ticket.setTicketType(TicketStatus.Abort);
                    ticketService.save(ticket);
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
        MovieResponse movieResponse = new MovieResponse();
        BeanUtils.copyProperties(movieRepository.save(movie), movieResponse);
        List<ShowTime> saved = new ArrayList<>();
        for(ShowTime showTime : movie.getShowTimes()){
            if (showTime.getActive().equals("true")){
                saved.add(showTime);
            }
        }
        movieResponse.setShowTimes(saved);
        if(movieResponse == null){
            throw new BadRequestException("BAD_REQUEST");
        }
        return movieResponse;
    }
    public List<MovieShowTimeResponse> getAll(String movieName) {
        Movie movie = movieRepository.SearchMoive(movieName);
        List<ShowTime> showTimeListMovie = showTimeRepository.findAllAfterCurrentTimeByMovieId(LocalTime.now(),  movie.getId());
        List<Movie> list = movieRepository.findAll(LocalDate.now());
        List<Movie> movies = new ArrayList<>();
        for (Movie moviesaved :list){
            if(movie.getActive().equals("true")){
                movies.add(moviesaved);
            }
        }
        List<ShowTime> showTimes = showTimeRepository.findAllAfterCurrentTime(LocalTime.now());
        List<MovieShowTimeResponse> responses = new ArrayList<>();
        for(Movie movieSaved : movies){
            MovieShowTimeResponse response = new MovieShowTimeResponse();
            response.setId(movieSaved.getId());
            response.setMovieNameEnglish(movieSaved.getMovieNameEnglish());
            response.setMovieNameVN(movieSaved.getMovieNameVN());
            response.setImageURL(movieSaved.getImageURL());
            response.setStartDate(movieSaved.getStartedDate());
            response.setEndDate(movieSaved.getEndDate());
            response.setCreatedDate(movieSaved.getCreatedDate());
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
        List<MovieShowTimeResponse> showTimeResponses = new ArrayList<>();
        MovieShowTimeResponse showTimeResponse = new MovieShowTimeResponse();
        showTimeResponse.setId(movie.getId());
        showTimeResponse.setMovieNameEnglish(movie.getMovieNameEnglish());
        showTimeResponse.setMovieNameVN(movie.getMovieNameVN());
        showTimeResponse.setImageURL(movie.getImageURL());
        showTimeResponse.setStartDate(movie.getStartedDate());
        showTimeResponse.setEndDate(movie.getEndDate());
        showTimeResponse.setCreatedDate(movie.getCreatedDate());
        showTimeResponse.setShowTimes(showTimeListMovie);
        for(ShowTime showTime : showTimeResponse.getShowTimes()){
            showTimeResponse.setRoomId(showTime.getRoom().getId());
            break;
        }
        showTimeResponses.add(showTimeResponse);
        List<MovieShowTimeResponse> filteredResponses = new ArrayList<>();
        for (MovieShowTimeResponse response : responses) {
            if (response.getId() != showTimeResponse.getId()) {
                filteredResponses.add(response);
            }
        }
        showTimeResponses.addAll(filteredResponses);
        if(showTimeResponses.isEmpty()){
            throw new NotFoundException("No Data Found");
        }
        return showTimeResponses;
    }

    public List<MovieShowTimeResponse> getAll() {
        List<Movie> list = movieRepository.findAll(LocalDate.now());
        List<Movie> movies = new ArrayList<>();
        for (Movie movie :list){
            if(movie.getActive().equals("true")){
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
            throw new NotFoundException(ErrorCode.ERROR_DB_NOT_FOUND);
        }
        return responses;
    }
    public List<MovieResponse> getAllMovieByAdmin(){
        List<Movie> list = movieRepository.findAll(LocalDate.now());
        List<Movie> movieList = new ArrayList<>();
        for (Movie movie :list){
            if(movie.getActive().equals("true")){
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
            movieResponse.setCreatDate(movie.getCreatedDate());
            movieResponses.add(movieResponse);
        }
        movieResponses.sort(Comparator.comparing(MovieResponse::getCreatDate).reversed());
        return movieResponses;
    }
    public void deleteById(Long request){
        if (movieRepository.existsById(request)) {
            Movie movie = movieRepository.getById(request);
            movie.setActive("false");
            movieRepository.save(movie);
        }else {
            throw new NotFoundException(ErrorCode.ERROR_USER_NOT_FOUND);
        }
    }
    public MovieResponse create(MovieRequest request){
        if (request.getContent() == null
                && request.getMovieNameEnglish() == null
                && request.getMovieNameVN() == null
                && request.getActor() == null
                && request.getDirector() == null
        ) throw new BadRequestException(ErrorCode.ERROR_DATA_NOT_MATCH);

        if (!request.getActor().matches(REGEX)) {
            throw new BadRequestException(ErrorCode.ERROR_FORMAT_ACTOR);
        }
        if (!request.getDirector().matches(REGEX)) {
            throw new BadRequestException(ErrorCode.ERROR_FORMAT_DIRECTOR);
        }
        if (!request.getMovieProductionCompany().matches(REGEX)) {
            throw new BadRequestException(ErrorCode.ERROR_FORMAT_MOVIE_PRODUCTION_COMPANY);
        }
        if (request.getDuration() < 1 || request.getDuration() > 300) {
            throw new BadRequestException(ErrorCode.ERROR_DURATION_NOT_MATCH);
        }
        if (request.getEndDate().isBefore(LocalDate.now())) {
            throw new BadRequestException(ErrorCode.ERROR_DATE_NOT_MATCH);
        }

        Set<TypeMovie> typeMovies = new HashSet<>();
        for (Long typeId : request.getTypeMovieId()) {
            TypeMovie type = typeRepository.findById(typeId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_TYPE_NOT_FOUND));
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
                .orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_MOVIE_NOT_FOUND));
        return movie;
    }
    public List<MovieWithCategoryResponse> getAllByCategories() {
        List<Movie> list = movieRepository.findAll(LocalDate.now());
        List<Movie> movieList = new ArrayList<>();
        for (Movie movie :list){
            if(movie.getActive().equals("true")){
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
    public MovieResponse searchMovieByMovieName(String data){
        return new MovieResponse(movieRepository.SearchMoive(data));
    }
    public List<MovieTypeNameResponse> getAllMovieByTypeName(String typeName){
        List<Movie> movieList = movieRepository.getALlMovieByType(typeName);
        List<MovieTypeNameResponse> responses = new ArrayList<>();
        for(Movie movie : movieList){
            MovieTypeNameResponse response = new MovieTypeNameResponse();
            BeanUtils.copyProperties(movie, response);
            responses.add(response);
        }
        if(responses.isEmpty()){
            throw new NotFoundException("Not data found");
        }
        return responses;
    }
}
