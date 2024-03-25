"use client";

import Image from "next/image";
import { useRouter } from "next/navigation";
import { useState } from "react";
import axiosInstace from "@/axios"
import loginIcon from "../../../public/loginIcon.svg";
import Loading from "components/LoadingScreen";

export default function Register() {
  const router = useRouter();
  const [account, setAccount] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [wrongData, setWrongData] = useState(false);
  const [invalid, setInvalid] = useState(false);
  const [success, setSuccess] = useState(false);

  const checkAllFieldsFilled = () => {
    const fields = [account, password];
    const allFilled = fields.every((field) => field.trim() !== "");
    return allFilled;
  };

  function handleLogin(e: any) {
    e.preventDefault();
    setInvalid(false);
    setWrongData(false);
    setLoading(true);
    if (checkAllFieldsFilled()) {
      setInvalid(false);
      
        axiosInstace.post("/api/login", {
          username: account,
          password: password,
        })
        .then((response) => {
          console.log(response.data)
          setSuccess(true);
          setTimeout(() => {
            localStorage.setItem("isLogin", "true");
            localStorage.setItem("account", response.data[0]);
            localStorage.setItem("role", response.data[1])
            console.log(response.data[1])
            if(response.data[1] === 'Admin') {
              router.push("/admin/dashboard/employees")
            } else {
              router.push("/user/dashboard/home")
            }
          }, 1500);
        })
        .catch((error) => {
          setTimeout(() => {
            setLoading(false);
            setWrongData(true);
          }, 1000);
        });
    } else {
      setTimeout(() => {
        setLoading(false);
        setInvalid(true);
      }, 1000);
    }
  }

  function handleRedirectRegister() {
    router.push("/register");
  }

  if (success) {
    return <Loading />;
  }

  return (
    <div className="w-full h-full flex flex-col items-center justify-center gap-3 bg-[#EFF0F3]">
      <div className="flex flex-col gap-5 w-[35%] bg-white border-solid border-[1px] pb-5 border-[#BEC8CF] rounded-[0.5rem] shadow-[0_4px_4px_rgba(0, 0, 0, 0.25)] px-10">
        <span className="flex justify-center mx-3 pt-4 text-center text-[2rem] text-black font-[700]">
          LOGIN
        </span>
        <form
          className="w-full flex flex-col gap-2 text-black"
          onSubmit={(e) => handleLogin(e)}
        >
          <input
            type="text"
            name="userName"
            value={account}
            placeholder="Account"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setAccount(e.target.value)}
          />
          <input
            type="password"
            name="password"
            value={password}
            placeholder="Password"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setPassword(e.target.value)}
          />
          <button
            className="flex flex-row justify-center items-center p-[10px] bg-[#337AB7] rounded-[7px] text-white mt-3"
            disabled={loading}
          >
            <div className="flex flex-row items-center gap-2">
              {loading ? (
                <div className="block h-4 w-4 animate-spin rounded-full border-2 border-t-transparent border-solid border-white"></div>
              ) : (
                <Image src={loginIcon} alt="" />
              )}
              <span>{loading ? "Waiting..." : "Login"}</span>
            </div>
          </button>
        </form>
        {wrongData && (
          <span className="text-red-500 font-medium block self-center">
            The account or password is invalid. Please try again!
          </span>
        )}
        {invalid && (
          <span className="text-red-500 block self-center font-medium">
            Please fill in all the information above!
          </span>
        )}
      </div>
      <span className="text-black">
        Have account already?{" "}
        <span
          className="underline cursor-pointer"
          onClick={() => handleRedirectRegister()}
        >
          Register
        </span>
      </span>
    </div>
  );
}
