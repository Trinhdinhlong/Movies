"use client";

import axiosInstance from "@/axios";
import MovieShowTime from "components/MovieShowTime";
import MovieShowTimeAdmin from "components/MovieShowTimeAdmin";
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

interface Params {
  slug: string;
}

interface SearchParams {
  [key: string]: string | undefined;
}

export default function Home({
  params,
  searchParams,
}: Readonly<{
  params: Params;
  searchParams: SearchParams;
}>) {
  const [listMovies, setListMovies] = useState<MovieShowtime[]>([]);
  const [noData, setNoData] = useState(false);
  const search = searchParams.search;
  useEffect(() => {
    if (search === undefined) {
      axiosInstance
        .get(`/api/movies/showtime`)
        .then((response) => setListMovies(response.data))
        .catch(err => {
          if(err.errorCode === 6) {
            setNoData(true);
          }
        });
    } else if ( search !== undefined ){
      axiosInstance.get(`/api/movies/showtime/${search}`).then(response => {
        setListMovies(response.data)
      }).catch(err => {
        if(err.errorCode === 6) {
          setNoData(true)
        }
      })
    }
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
              imageURL={movie.imageURL}
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
