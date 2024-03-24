"use client";
import Image from "next/image";
import { useEffect, useState } from "react";
import list from "@/public/list.png";
import MovieBlock from "components/MovieBlock";
import axios from "axios";

interface TypeMovie {
  categoryName: string;
  movies: Movie[];
}

interface Movie {
  id: number;
  movieNameEnglish: string;
  movieNameVN: string;
  actor: string;
  director: string;
  duration: number;
  movieProductionCompany: string;
  startedDate: string;
  endDate: string;
  imageURL: string;
}

export default function Home() {
  const [listMovie, setListMovie] = useState<Movie[]>([]);
  const [authenticated, setAuthenticated] = useState(false)
  const [listMovieType, setMovieType] = useState<TypeMovie[]>([]);

  useEffect(() => {
    if(localStorage.getItem("isLogin") || localStorage.getItem("isLogin") === "true") {
      setAuthenticated(true)
    }
  }, [authenticated])

  useEffect(() => {
    axios
      .get("https://9817-14-232-224-226.ngrok-free.app/api/movies", {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      })
      .then((response) => {
        setMovieType(response.data);
      })
      .catch((error) => console.error(error));
  }, []);

  return (
    <div className="flex flex-col w-full relative h-full overflow-auto">
      <div className="bg-[#B8ADC1]">
        <div className="w-full flex flex-col mb-20">
          <Image src={list} alt="" className="w-full" />
          <div>
            <div className="mt-5 ml-5">
              <div className="flex flex-col gap-16">
                {listMovieType.map((movie) => (
                  <div className="flex flex-col">
                    <span className="font-[700] block mb-2 text-white">
                      {movie.categoryName}
                    </span>
                    <div className="flex flex-row gap-16">
                      {movie.movies.map((el) => (
                        <MovieBlock imageURL={el.imageURL} movieName={el.movieNameEnglish} />
                      ))}
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
