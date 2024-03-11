"use client";
import Image from "next/image";
import logo from "@/public/logo.svg";
import dropdown from "@/public/dropdown.svg";
import search from "@/public/Search.svg";
import user from "@/public/User.svg";
import logout from "@/public/Logout.svg";

export default function Header({ handleOpen }: any) {
  function handleOpenPopup() {
    handleOpen();
  }

  return (
    <div className="w-full h-full top-[0]">
      <div className="z-[0] px-[30px] py-[10px] bg-[#205081] flex flex-row items-center justify-between w-full h-full">
        <div>
          <Image src={logo} alt="MovieTheaterLogo" />
        </div>
        <div className="flex flex-row items-center gap-14">
          <span>Showtime</span>
          <div className="overflow-hidden">
            <button
              className="flex flex-row gap-3 cursor-pointer items-center"
              onClick={() => handleOpenPopup()}
            >
              <span>Movies</span>
              <Image src={dropdown} alt="DropDown Logo" />
            </button>
          </div>
          <form className="flex flex-row h-[2rem] justify-center items-center bg-white rounded-[10px]">
            <input
              type="text"
              placeholder="Search"
              className=" rounded-[10px] px-4 text-black h-full outline-none"
            />
            <div className="border-solid border-l-[0.5px] border-l-[#EFF0F3] h-full flex items-center p-2">
              <Image src={search} alt="" />
            </div>
          </form>
        </div>
        <div className="flex flex-row gap-10">
          <div className="cursor-pointer flex flex-row gap-2">
            <Image src={user} alt="" />
            <span>Welcome, julientlam</span>
          </div>
          <div className="cursor-pointer flex flex-row gap-2">
            <Image src={logout} alt="" />
            <span>Logout</span>
          </div>
        </div>
      </div>
    </div>
  );
}
