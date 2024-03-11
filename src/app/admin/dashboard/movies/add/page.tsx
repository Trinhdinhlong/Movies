"use client";
import Image from "next/image";
import logo from "@/public/logo.svg";
import dropdown from "@/public/dropdown.svg";
import search from "@/public/Search.svg";
import { useState } from "react";
import user from "@/public/User.svg";
import logout from "@/public/Logout.svg";
import minimize from "@/public/minimize.svg";

export default function Home() {
  const [open, setOpen] = useState(false);
  const [account, setAccount] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [fullName, setFullName] = useState("male");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [gender, setGender] = useState("");
  const [identityCard, setIdentityCard] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [image, setImage] = useState("");
  return (
    <div className="bg-[#EFF0F3]">
      <div className="flex flex-col w-full relative bg-[#EFF0F3]">
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
                <div className="border-[1px] border-solid border-black z-[3] bg-white absolute left-[35%] top-[60px] flex flex-col gap-2 items-start rounded-[5px] p-2 w-[150px] max-h-[200px] overflow-auto text-black font-[500]">
                  <span className="cursor-pointer p-2">Action</span>
                  <span className="cursor-pointer p-2">Romantic</span>
                  <span className="cursor-pointer p-2">Demo1</span>
                  <span className="cursor-pointer p-2">Demo2</span>
                  <span className="cursor-pointer p-2">Demo2</span>
                  <span className="cursor-pointer p-2">Demo2</span>
                  <span className="cursor-pointer p-2">Demo2</span>
                  <span className="cursor-pointer p-2">Demo2</span>
                  <span className="cursor-pointer p-2">Demo2</span>
                  <span className="cursor-pointer p-2">Demo2</span>
                </div>
              )}
            </div>
            <div className="flex flex-row h-[2rem] justify-center items-center bg-white rounded-[10px]">
              <input
                type="text"
                placeholder="Search"
                className=" rounded-[10px] px-4 text-black h-full outline-none"
              />
              <div className="border-solid border-l-[0.5px] border-l-[#EFF0F3] h-full flex items-center p-2">
                <Image src={search} alt="" />
              </div>
            </div>
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
      <div className="flex flex-row text-black w-full">
        <div className="z-[1] flex flex-col bg-white w-[16%] h-screen sticky left-0 top-0 overflow-x-hidden">
          <div className="flex flex-row justify-end px-5 py-3">
            <Image src={minimize} alt="" />
          </div>
          <span className="px-5 py-3">Employees</span>
          <span className="px-5 py-3 border-t-[1px] border-solid border-t-black">
            Movies
          </span>
          <span className="px-5 py-3 border-t-[1px] border-solid border-t-black">
            Cinema Room
          </span>
          <span className="px-5 py-3 border-t-[1px] border-solid border-t-black">
            Booking List
          </span>
          <span className="px-5 py-3 border-t-[1px] border-solid border-t-black border-b-[1px] border-b-black">
            Promotion
          </span>
        </div>
        <div className="flex flex-col items-center justify-center p-5 w-full overflow-auto">
          <form className="space-y-4">
            <input
              type="text"
              name="movieNameEng"
              className="block w-full px-4 py-2 border rounded-md"
              placeholder="Movie name (ENG)"
            />
            <input
              type="text"
              name="movieNameVN"
              className="block w-full px-4 py-2 border rounded-md"
              placeholder="Tên phim (VN)"
            />
            <input
              type="date"
              name="fromDate"
              className="block w-full px-4 py-2 border rounded-md"
            />
            <input
              type="date"
              name="toDate"
              className="block w-full px-4 py-2 border rounded-md"
            />
            <input
              type="text"
              name="actor"
              className="block w-full px-4 py-2 border rounded-md"
              placeholder="Actor"
            />
            <input
              type="text"
              name="movieProductionCompany"
              className="block w-full px-4 py-2 border rounded-md"
              placeholder="Movie Production Company"
            />
            <input
              type="text"
              name="director"
              className="block w-full px-4 py-2 border rounded-md"
              placeholder="Director"
            />
            <input
              type="text"
              name="duration"
              className="block w-full px-4 py-2 border rounded-md"
              placeholder="Duration"
            />
            {/* ... more inputs ... */}
            <button
              type="submit"
              className="px-6 py-2 text-white bg-blue-500 rounded-md hover:bg-blue-600"
            >
              Save
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
