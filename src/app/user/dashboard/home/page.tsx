"use client";
import { useEffect, useState } from "react";
import MovieBlock from "components/MovieBlock";
import axiosInstance from "@/axios";
import Carousel from "components/Carousel";
import { useRouter } from "next/navigation";

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
  const type = searchParams.type;
  const router = useRouter()
  const [authenticated, setAuthenticated] = useState(false);
  const [noData, setNoData] = useState(false);
  const [movieType, setMovieType] = useState<TypeMovie[]>([]);
  const slides = [
    "/piano.jpg",
    "/greenbook.jpeg",
    "/Crazy-Rich-Asians-top.jpg",
    "/dune.jpg",
  ];

  useEffect(() => {
    if (localStorage.getItem("isLogin") !== null) {
      setAuthenticated(true);
    }
  }, [authenticated]);

  // useEffect(() => {
  //   if( localStorage.getItem("role") === "Admin") {
  //     router.push("/admin/dashboard/showtime")
  //   }
  // }, [])

  useEffect(() => {
    if (type === undefined) {
      axiosInstance
        .get("/api/movies")
        .then((response) => {
          setMovieType(response.data);
        })
        .catch((error) => console.error(error));
    } else {
      axiosInstance.get(`/api/movie/${type}`).then((response) => {
        console.log(response.data);
        setMovieType(response.data);
      });
    }
  }, []);

  return (
    <div className="flex flex-col w-full relative">
      <div className="bg-[#B8ADC1] h-full overflow-auto">
        <div className="w-full flex flex-col mb-20">
          <div className="flex h-[30rem] justify-center bg-no-repeat bg-center bg-cover flex-row-full bg-[url(/backgroundcarousel.jpg)] backdrop-brightness-50 object-cover">
            <div className="h-[72%] w-[50%] flex flex-row gap-3 overflow-hidden absolute left-[26%] rounded-[5%] border-[1px] border-red-500 top-[5px]">
              <Carousel
                autoSlide={true}
                autoSlideInterval={2500}
                slides={slides}
              >
                {slides.map((el) => (
                  <div className="w-full flex shrink-0" key={el}>
                    <img src={el} alt="" className="w-full h-auto " />
                  </div>
                ))}
              </Carousel>
            </div>
          </div>
          <div>
            <div className="mt-5 ml-5">
              <div className="flex flex-col gap-16">
                {movieType.map((movie, index) => (
                  <div className="flex flex-col" key={index}>
                    <span className="font-[700] block mb-2 text-white text-[1.5rem] mb-5">
                      {movie.categoryName}
                    </span>
                    <div className="flex flex-row gap-16">
                      {movie.movies.map((el) => (
                        <MovieBlock
                          key={el.id}
                          imageURL={el.imageURL}
                          movieName={el.movieNameEnglish}
                        />
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
