"use client";

import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function Register() {
  const router = useRouter();
  const [account, setAccount] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [fullName, setFullName] = useState("");
  const [dob, setDob] = useState("");
  const [gender, setGender] = useState("male");
  const [identityCard, setIdentityCard] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");

  const checkAllFieldsFilled = () => {
    const fields = [
      account,
      password,
      confirmPassword,
      fullName,
      dob,
      gender,
      identityCard,
      email,
      address,
      phoneNumber,
    ];
    const allFilled = fields.every((field) => field.trim() !== "");
    return allFilled;
  };

  function handleRegister(e: any) {
    e.preventDefault();
    // if (checkAllFieldsFilled()) {
    //   axios.post("https://localhost:8080/api/user", {
    //     username: account,
    //     password: password,
    //     fullName: fullName,
    //     dateOfBirth: dob,
    //     gender: gender,
    //     email: email,
    //     address: address,
    //     phone: phoneNumber,
    //     role: 2,
    //   }).then(response => console.log(response.data));
    // }
    axios.post("http://localhost:8080/api/long",
      {
        address: "1243",
        dateOfBirth: [2004, 1, 1],
        email: "12483@gmail.com",
        fullName: "Nguyen4",
        gender: "MALE",
        password: "1243",
        phone: "0936036188",
        username: "user22"
      }).then(response => console.log(response.data))
  }

  function handleRedirectLogin() {
    router.push("/login");
  }

  return (
    <div className="w-full h-full flex flex-col items-center justify-center gap-3 bg-[#EFF0F3]">
      <div className="flex flex-col gap-5 w-[35%] bg-white border-solid border-[1px] border-[#BEC8CF] rounded-[0.5rem] shadow-[0_4px_4px_rgba(0, 0, 0, 0.25)] px-10">
        <span className="flex justify-center mx-3 pt-4 text-center text-[2rem] text-black font-[700]">
          REGISTER ACCOUNT
        </span>
        <form
          className="w-full flex flex-col gap-2 text-black"
          onSubmit={(e) => handleRegister(e)}
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
          <input
            type="password"
            name="confirmPassword"
            value={confirmPassword}
            placeholder="Confirm password"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
          <input
            type="text"
            name="fullName"
            value={fullName}
            placeholder="Full Name"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setFullName(e.target.value)}
            required
          />
          <input
            type="date"
            name="date"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setDob(e.target.value)}
          />
          <div className="text-black flex flex-row gap-2">
            <div className="flex flex-row gap-1 items-center justify-between">
              <input
                type="radio"
                value="male"
                id="male"
                name="gender"
                className="mt-[3px]"
                onChange={(e) => setGender(e.target.value)}
                checked={gender === "male"}
              />
              <label htmlFor="male">Nam</label>
            </div>
            <div className="flex flex-row gap-1 items-center justify-between">
              <input
                type="radio"
                value="female"
                id="female"
                name="gender"
                className="mt-[3px]"
                onChange={(e) => setGender(e.target.value)}
                checked={gender === "female"}
              />
              <label htmlFor="female">Nữ</label>
            </div>
          </div>
          <input
            type="text"
            name="iCard"
            placeholder="Identity card"
            value={identityCard}
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setIdentityCard(e.target.value)}
            required
          />
          <input
            type="text"
            name="email"
            placeholder="Email"
            value={email}
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="text"
            name="address"
            placeholder="Address"
            value={address}
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setAddress(e.target.value)}
            required
          />
          <input
            type="text"
            name="phone"
            placeholder="Phone number"
            value={phoneNumber}
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[7px] px-[10px] py-[7px]"
            onChange={(e) => setPhoneNumber(e.target.value)}
            required
          />
          <button className="flex flex-row justify-center items-center p-[10px] bg-[#337AB7] rounded-[7px] mb-8 text-white mt-3">
            Register
          </button>
        </form>
      </div>
      <span className="text-black">
        Have account already?{" "}
        <span
          className="underline cursor-pointer"
          onClick={() => handleRedirectLogin()}
        >
          Login
        </span>
      </span>
    </div>
  );
}
