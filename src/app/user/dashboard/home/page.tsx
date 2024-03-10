"use client";
import Image from "next/image";
import logo from "@/public/logo.svg";
import dropdown from "@/public/dropdown.svg";
import search from "@/public/Search.svg";
import { useEffect, useState } from "react";
import user from "@/public/User.svg"
import logout from "@/public/Logout.svg"
import list from "@/public/list.png"
import MovieBlock from "components/MovieBlock";
import axios from "axios";

interface TypeMovie {
  id: number;
  typeName: string;
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
  createdTimDate: string;
  typeMovies: TypeMovie[];
}

export default function Home() {
  const [open, setOpen] = useState(false);
  const [listMovie, setListMovie] = useState<Movie[]>([])
    useEffect(() => {
        axios.get("http://localhost:8080/api/movie/listmovie").then(response => {
            setListMovie(response.data)
        })
    },[])
  return (
    <div className="flex flex-col w-full relative">
      <div className="px-[30px] py-[10px] bg-[#205081] flex flex-row items-center justify-between w-full">
        <div>
          <Image src={logo} alt="MovieTheaterLogo" />
        </div>
        <div className="flex flex-row items-center gap-14">
          <span>Showtime</span>
          <div className="overflow-hidden">
            <div
              className="flex flex-row gap-3 cursor-pointer"
              onClick={() => setOpen(!open)}
            >
              <span>Movies</span>
              <Image src={dropdown} alt="DropDown Logo" />
            </div>
            {open && (
              <div className="z-[1] bg-white absolute left-[35%] top-[60px] flex flex-col gap-2 items-start rounded-[5px] p-2 w-[150px] max-h-[200px] overflow-auto text-black font-[500]">
                <span className="cursor-pointer p-2 pr-20 hover:bg-[#D4D4D4]">Action</span>
                <span className="cursor-pointer p-2 pr-20 hover:bg-[#D4D4D4]">Romantic</span>
                <span className="cursor-pointer p-2 pr-20 hover:bg-[#D4D4D4]">Demo1</span>
                <span className="cursor-pointer p-2 pr-20 hover:bg-[#D4D4D4]">Demo2</span>
                <span className="cursor-pointer p-2 pr-20 hover:bg-[#D4D4D4]">Demo2</span>
                <span className="cursor-pointer p-2 pr-20 hover:bg-[#D4D4D4]">Demo2</span>
                <span className="cursor-pointer p-2 pr-20 hover:bg-[#D4D4D4]">Demo2</span>
                <span className="cursor-pointer p-2 pr-20 hover:bg-[#D4D4D4]">Demo2</span>
                <span className="cursor-pointer p-2 pr-20 hover:bg-[#D4D4D4]">Demo2</span>
                <span className="cursor-pointer p-2 pr-20 hover:bg-[#D4D4D4]">Demo2</span>
              </div>
            )}
          </div>
          <div className="flex flex-row h-[2rem] justify-center items-center bg-white rounded-[10px]">
            <input type="text" placeholder="Search" className=" rounded-[10px] px-4 text-black h-full outline-none"/>
            <div className="border-solid border-l-[0.5px] border-l-[#EFF0F3] h-full flex items-center p-2">
              <Image src={search} alt=""/>
            </div>
          </div>
        </div>
        <div className="flex flex-row gap-10">
          <div className="cursor-pointer flex flex-row gap-2">
            <Image src={user} alt=""/>
            <span>Welcome, julientlam</span>
          </div>
          <div className="cursor-pointer flex flex-row gap-2">
            <Image src={logout} alt=""/>
            <span>Logout</span>
          </div>
        </div>
      </div>
      <div className="bg-[#B3A6C1] h-screen">
              <div className="w-full">
                <Image src={list} alt="" className="w-full"/>
                <div>
                  <span>Hoạt Hình</span>
                  <div className="flex flex-row gap-5">
                    {listMovie.map(movie => (<MovieBlock movieName={movie.movieNameEnglish}/>))}
                  </div>
                </div>
              </div>
      </div>
    </div>
  );
}
