"use client";

import { useEffect, useState } from "react";

export default function Register() {
    const [account, setAccount] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [fullName, setFullName] = useState('');
    const [dob, setDob] = useState('');
    const [gender, setGender] = useState('');
    const [identityCard, setIdentityCard] = useState('');
    const [email, setEmail] = useState('');
    const [address, setAddress] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
  return (
    <div className="w-full h-full flex flex-col items-center justify-center gap-3">
      <div className="flex flex-col gap-5 w-[35%] bg-white border-solid border-[1px] border-[#BEC8CF] rounded-[0.5rem] shadow-[0_4px_4px_rgba(0, 0, 0, 0.25)] px-10">
        <span className="flex justify-center mx-3 pt-4 text-center text-[2rem] text-black font-[700]">
          REGISTER ACCOUNT
        </span>
        <form className="w-full flex flex-col gap-2 text-black">
          <input
            type="text"
            name="userName"
            placeholder="Account"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setAccount(e.target.value)}
          />
          <input
            type="password"
            name="password"
            placeholder="Password"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setPassword(e.target.value)}
          />
          <input
            type="password"
            name="confirmPassword"
            placeholder="Confirm password"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
          <input
            type="text"
            name="fullName"
            placeholder="Full Name"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => set}
          />
          <input
            type="date"
            name="date"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
          />
          <div className="text-black flex flex-row gap-2">
            <div className="flex flex-row gap-1 items-center justify-between">
              <input
                type="radio"
                id="male"
                name="gender"
                checked
                className="mt-[3px]"
              />
              <label htmlFor="male">Nam</label>
            </div>
            <div className="flex flex-row gap-1 items-center justify-between">
              <input
                type="radio"
                id="female"
                name="gender"
                className="mt-[3px]"
              />
              <label htmlFor="female">Nữ</label>
            </div>
          </div>
          <input
            type="text"
            name="iCard"
            placeholder="Identity card"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
          />
          <input
            type="text"
            name="email"
            placeholder="Email"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
          />
          <input
            type="text"
            name="address"
            placeholder="Address"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
          />
          <input
            type="text"
            name="phone"
            placeholder="Phone number"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
          />
        </form>
        <button className="flex flex-row justify-center items-center p-[10px] bg-[#337AB7] rounded-[7px] mb-8">
          Register
        </button>
      </div>
      <span className="text-black">
        Have account already?{" "}
        <span className="underline cursor-pointer">Login</span>
      </span>
    </div>
  );
}
