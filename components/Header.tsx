"use client";

import Image from "next/image";
import logo from "@/public/logo.svg";
import dropdown from "@/public/dropdown.svg";
import searchIcon from "@/public/Search.svg";
import user from "@/public/User.svg";
import logout from "@/public/Logout.svg";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import axiosInstance from "@/axios";

interface MovieType {
  id: number;
  typeName: string;
  createdDate: string;
  updatedTime: string;
}

export default function Header() {
  const router = useRouter();
  const [search, setSearch] = useState("");
  const [open, setOpen] = useState(false);
  const [authenticated, setAuthenticated] = useState(false);
  const [userId, setUserID] = useState("");
  const [searched, setSearched] = useState(false);
  const [movieTypes, setMovieTypes] = useState<MovieType[]>([]);
  const [login, setLogin] = useState(false);

  function handleHomeSearch(e: any) {
    e.preventDefault();
    router.push(
      `/${
        localStorage.getItem("role") !== null
          ? localStorage.getItem("role")?.toLowerCase()
          : "user"
      }/dashboard/showtime?search=${search}`
    );
    setTimeout(() => {
      location.reload();
    }, 1000)
  }

  function handleOpen() {
    setOpen(!open);
  }

  function logIn() {
    router.push("/login");
  }

  function handleUserProfile() {
    if (
      localStorage.getItem("role") === "User" ||
      localStorage.getItem("Role") === null
    ) {
      router.push("/user/dashboard/edit");
    } else {
      router.push("/admin/dashboard/employees");
    }
  }

  function handleLogout() {
    localStorage.removeItem("isLogin");
    localStorage.removeItem("account");
    localStorage.removeItem("role");
    router.push("/user/dashboard/home");
    location.reload();
  }

  function handleShowtime() {
    if (localStorage.getItem("role") === "Admin")
      router.push("/admin/dashboard/showtime");
    else router.push("/user/dashboard/showtime");
  }

  function handleHome() {
    if (localStorage.getItem("role") === "Admin")
      router.push("/admin/dashboard/showtime");
    else router.push("/user/dashboard/home");
  }

  useEffect(() => {
    if (
      localStorage.getItem("isLogin") ||
      localStorage.getItem("isLogin") === "true"
    ) {
      setLogin(true);
      setAuthenticated(true);
      setUserID(localStorage.getItem("account") || "");
    }
  }, [authenticated]);

  useEffect(() => {
    axiosInstance
      .get("/api/type")
      .then((response) => setMovieTypes(response.data));
  }, []);

  return (
    <div className="z-[2] w-full h-full flex flex-row px-5 gap-5 justify-between font-[500] relative overflow-visible">
      <div
        className="flex flex-col justify-center cursor-pointer"
        onClick={handleHome}
      >
        <Image src={logo} alt="MovieTheaterLogo" />
      </div>
      <div className="h-full flex flex-row items-center justify-between gap-14">
        <span className="block cursor-pointer" onClick={handleShowtime}>
          Showtime
        </span>
        <div className="flex flex-row">
          <button
            className="flex flex-row items-center justify-center gap-2 cursor-pointer"
            onClick={() => handleOpen()}
          >
            <span>Movies</span>
            <Image src={dropdown} alt="DropDown Logo" />
          </button>
        </div>
        <div className="flex flex-row bg-white gap-2 p-1 px-2 items-center justify-center rounded-[10px] text-black">
          <form className="flex flex-row items-center"
          onSubmit={(e) => handleHomeSearch(e)}>
            <input
              type="text"
              placeholder="Search"
              className="outline-none px-2"
              onChange={(e) => setSearch(e.target.value)}
            />
            <button>
              <Image src={searchIcon} alt="" />
            </button>
          </form>
        </div>
      </div>
      <div className="flex flex-row items-center justify-center gap-10">
        {authenticated && (
          <div
            className="flex flex-row gap-3 items-center justify-center cursor-pointer w-[10rem]"
            onClick={handleUserProfile}
          >
            <Image src={user} alt="" />
            <span>Welcome, {userId}</span>
          </div>
        )}
        <div
          className="flex flex-row gap-3 items-center justify-center cursor-pointer"
          onClick={authenticated ? handleLogout : logIn}
        >
          <Image src={logout} alt="" />
          <span>{authenticated ? "Logout" : "Sign in"}</span>
        </div>
      </div>
      {open && (
        <div
          id="dropdown"
          className={`z-10 bg-white absolute top-[70%] ${
            login ? "left-[30%]" : "left-[37%]"
          } divide-y divide-gray-100 rounded-lg shadow w-44 bg-white`}
        >
          <ul
            className="py-2 text-sm text-black"
            aria-labelledby="dropdownDefaultButton"
          >
            {movieTypes.map((el, index) => (
              <li key={index}>
                <a
                  href={`/user/dashboard/home?type=${el.typeName}`}
                  className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                >
                  {el.typeName}
                </a>
              </li>
            ))}
          </ul>
        </div>
      )}
    </div>
  );
}
