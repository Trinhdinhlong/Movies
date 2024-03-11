"use client";

import Image from "next/image";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import axios from "axios";
import loginIcon from "../../../public/loginIcon.svg"

export default function Register() {
  const router = useRouter();
  const [account, setAccount] = useState("");
  const [password, setPassword] = useState("");

  const checkAllFieldsFilled = () => {
    const fields = [
      account,
      password
    ];
    const allFilled = fields.every((field) => field.trim() !== "");
    return allFilled;
  };

  function handleLogin(e: any) {
    e.preventDefault();
    if (checkAllFieldsFilled()) {
      axios.post("http://localhost:8080/api/login", {
        "username":account,
        "password": password
      }).then(response => {
        router.push("user/dashboard/home")
      })
    }
  }

  function handleRedirectRegister() {
    router.push("/register");
  }

  return (
    <div className="w-full h-full flex flex-col items-center justify-center gap-3 bg-[#EFF0F3]">
      <div className="flex flex-col gap-5 w-[35%] bg-white border-solid border-[1px] border-[#BEC8CF] rounded-[0.5rem] shadow-[0_4px_4px_rgba(0, 0, 0, 0.25)] px-10">
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
            required
          />
          <input
            type="password"
            name="password"
            value={password}
            placeholder="Password"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button className="flex flex-row justify-center items-center p-[10px] bg-[#337AB7] rounded-[7px] mb-8 text-white mt-3">
            <div className="flex flex-row gap-2">
                <Image src={loginIcon} alt=""/>
                <span>Login</span>
            </div>
          </button>
        </form>
      </div>
      <span className="text-black">
        Have account already?{" "}
        <span className="underline cursor-pointer"
        onClick={() => handleRedirectRegister()}>Register</span>
      </span>
    </div>
  );
}
