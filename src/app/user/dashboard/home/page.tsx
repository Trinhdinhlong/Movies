"use client";
import Image from "next/image";
import { useEffect, useState } from "react";
import list from "@/public/list.png";
import MovieBlock from "components/MovieBlock";
import axios from "axios";

interface TypeMovie {
  id: number;
  typeName: string;
  createdDate: string;
  updatedTime: string;
}

interface Movie {
  id: number;
  content: string;
  movieNameEnglish: string;
  movieNameVN: string;
  actor: string;
  director: string;
  duration: number;
  movieProductionCompany: string;
  startedDate: string;
  endDate: string;
  imageURL: string;
  typeMovies: TypeMovie[];
}

export default function Home() {
  const [open, setOpen] = useState(false);
  const [listMovie, setListMovie] = useState<Movie[]>([]);
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/movie-management/movie")
      .then((response) => {
        setListMovie(response.data);
      });
  }, []);
  return (
    <div className="flex flex-col w-full relative">
      <div className="h-screen bg-[#B8ADC1]">
        <div className="w-full flex flex-col">
          <Image src={list} alt="" className="w-full" />
          <div>
            <div className="mt-5 ml-5">
              <span className="font-[700] block mb-2">Hoạt Hình</span>
              <div className="flex flex-row gap-5 flex-wrap">
                {listMovie.map((movie) => (
                  <MovieBlock
                    key={movie.id}
                    movieName={movie.movieNameEnglish}
                    imageURL={movie.imageURL}
                  />
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
