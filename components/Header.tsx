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
    <div className="z-[0] w-full h-full flex flex-row px-5 gap-5 justify-between">
      <div className="flex flex-col justify-center">
        <Image src={logo} alt="MovieTheaterLogo" />
      </div>
      <div className="h-full flex flex-row items-center justify-between gap-14">
        <span className="block">Showtime</span>
        <div className="flex flex-row">
          <button className="flex flex-row items-center justify-center gap-2 cursor-pointer" onClick={() => handleOpenPopup()}>
            <span>Movies</span>
            <Image src={dropdown} alt="DropDown Logo" />
          </button>
        </div>
        <div className="flex flex-row bg-white gap-2 p-1 px-2 items-center justify-center rounded-[10px] text-black">
          <input type="text" placeholder="Search" className="outline-none"/>
          <div className="">
            <Image src={search} alt="" />
          </div>
        </div>
      </div>
      <div className="flex flex-row items-center justify-center gap-10">
        <div className="flex flex-row gap-3 items-center justify-center">
          <Image src={user} alt="" />
          <span>Welcome, julientlam</span>
        </div>
        <div className="flex flex-row gap-3 items-center justify-center">
          <Image src={logout} alt="" />
          <span>Logout</span>
        </div>
      </div>
    </div>
  );
}
