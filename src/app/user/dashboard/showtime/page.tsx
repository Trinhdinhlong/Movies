"use client";

import MovieShowTime from "components/MovieShowTime";
import { useEffect, useState } from "react";

interface Room {
  id: number;
  roomName: string;
  seatQuantity: number;
  createdDate: string;
  updatedTime: string;
}

interface Showtime {
  id: number;
  startTime: string;
  endTime: string;
  createdDate: string;
  updatedTime: string;
}

interface MovieShowtime {
  id: number;
  movieNameEnglish: string;
  movieNameVN: string;
  imageURL: string;
  showTimes: Showtime[];
  startDate: string;
  endDate: string;
  room: Room;
}

export default function Home() {
  const [listMovies, setListMovies] = useState<MovieShowtime[]>([]);
  useEffect(() => {
    fetch("/showtime.json")
      .then((response) => response.json())
      .then((data) => setListMovies(data));
  }, []);
  return (
    <div className="w-full bg-[#EFF0F3] text-black flex flex-col items-center h-full overflow-auto">
      <div className="w-[70%] flex flex-col items-center gap-10 mb-10">
        <span className="w-full text-center mt-10 font-[700] text-[1.5rem]">
          SHOWTIMES
        </span>
        <div className="flex flex-col bg-white gap-2 w-full py-10 px-10">
          {listMovies.map((movie) => (
            <MovieShowTime
              movieEn={movie.movieNameEnglish}
              movieVie={movie.movieNameVN}
              showtime={movie.showTimes}
              movieId={movie.id}
              room={movie.room}
            />
          ))}
          {/* http://localhost:8080/api/movie/1/room/1/showtime/1/seats */}
        </div>
      </div>
    </div>
  );
}
