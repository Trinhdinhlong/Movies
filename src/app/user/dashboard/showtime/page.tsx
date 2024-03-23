"use client";

import axios from "axios";
import MovieShowTime from "components/MovieShowTime";
import { useEffect, useState } from "react";

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
  roomId: number;
}

export default function Home() {
  const [listMovies, setListMovies] = useState<MovieShowtime[]>([]);
  useEffect(() => {
    function formatDate(date: Date) {
      const day = String(date.getDate()).padStart(2, "0");
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const year = date.getFullYear();
      const hours = String(date.getHours()).padStart(2, "0");
      const minutes = String(date.getMinutes()).padStart(2, "0");

      return `${day}-${month}-${year} ${hours}:${minutes}`;
    }

    let currentDate = new Date();
    let formattedDate = formatDate(currentDate);
    axios
      .get(
        `https://9817-14-232-224-226.ngrok-free.app/api/movies/showtime?date=${formattedDate}`,
        {
          headers: {
            "ngrok-skip-browser-warning": "skip-browser-warning",
          },
        }
      )
      .then((response) => setListMovies(response.data));
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
              key={movie.id}
              movieEn={movie.movieNameEnglish}
              movieVie={movie.movieNameVN}
              showtime={movie.showTimes}
              movieId={movie.id}
              room={movie.roomId}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
