import Image from "next/image";
import logo from "@/public/logo.svg";
import dropdown from "@/public/dropdown.svg";
import search from "@/public/Search.svg";
import user from "@/public/User.svg";
import logout from "@/public/Logout.svg";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function Header({ handleOpen }: any) {
  const router = useRouter()
  const [changed, setChanged] = useState(false)

  function logIn() {
    router.push("/login")
  }
  
  function handleOpenPopup() {
    handleOpen();
  }

  function handleUserProfile() {
    router.push("/user/dashboard/edit")
  }

  function handleLogout() {
    localStorage.removeItem("isLogin")
    localStorage.removeItem("account")
    location.reload()
    router.push("/user/dashboard/home")
  }

  function handleShowtime() {
    router.push("/user/dashboard/showtime")
  }

  function handleHome() {
    router.push("/user/dashboard/home")
  }

  const [authenticated, setAuthenticated] = useState(false)
  const [userId, setUserID] = useState("")

  useEffect(() => {
    if(localStorage.getItem("isLogin") || localStorage.getItem("isLogin") === "true") {
      setAuthenticated(true)
      setUserID(localStorage.getItem("account") || "")
    }
  }, [authenticated])

  return (
    <div className="z-[0] w-full h-full flex flex-row px-5 gap-5 justify-between font-[500]">
      <div className="flex flex-col justify-center cursor-pointer"
      onClick={handleHome}>
        <Image src={logo} alt="MovieTheaterLogo" />
      </div>
      <div className="h-full flex flex-row items-center justify-between gap-14">
        <span className="block cursor-pointer" onClick={handleShowtime}>Showtime</span>
        <div className="flex flex-row">
          <button className="flex flex-row items-center justify-center gap-2 cursor-pointer" onClick={() => handleOpenPopup()}>
            <span>Movies</span>
            <Image src={dropdown} alt="DropDown Logo" />
          </button>
        </div>
        <div className="flex flex-row bg-white gap-2 p-1 px-2 items-center justify-center rounded-[10px] text-black">
          <input type="text" placeholder="Search" className="outline-none px-2"/>
          <div className="">
            <Image src={search} alt="" />
          </div>
        </div>
      </div>
      <div className="flex flex-row items-center justify-center gap-10">
        {authenticated && <div className="flex flex-row gap-3 items-center justify-center cursor-pointer"
        onClick={handleUserProfile}>
          <Image src={user} alt="" />
          <span>Welcome, {userId}</span>
        </div>}
        <div className="flex flex-row gap-3 items-center justify-center cursor-pointer"
        onClick={authenticated ? handleLogout : logIn}>
          <Image src={logout} alt="" />
          <span>{authenticated ? "Logout" : "Sign in"}</span>
        </div>
      </div>
    </div>
  );
}
