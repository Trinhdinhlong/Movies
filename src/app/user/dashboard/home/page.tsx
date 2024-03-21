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
  const [listMovieType, setMovieType] = useState<TypeMovie[]>([])
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/movies")
      .then((response) => {
        setMovieType(response.data);
      });
  }, []);
  console.log(listMovieType)
  return (
    <div className="flex flex-col w-full relative h-full overflow-auto">
      <div className="h-screen bg-[#B8ADC1]">
        <div className="w-full flex flex-col">
          <Image src={list} alt="" className="w-full" />
          <div>
            <div className="mt-5 ml-5">
              <span className="font-[700] block mb-2">Hoạt Hình</span>
              <div className="flex flex-row gap-5 flex-wrap">
                {listMovieType.map((movie) => (
                  <div><span>{movie.categoryName}</span>
                    {movie.movies.map(el => <MovieBlock imageURL={el.imageURL} movie />)}</div>
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
