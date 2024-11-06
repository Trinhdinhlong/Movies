"use client";

import axiosInstance from "@/axios";
import Loading from "components/LoadingScreen";
import Image from "next/image";
import loginIcon from "../../../public/loginIcon.svg";
import { useRouter } from "next/navigation";
import { useState } from "react";

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
  const [loading, setLoading] = useState(false);
  const [wrongData, setWrongData] = useState(false);
  const [invalid, setInvalid] = useState(false);
  const [success, setSuccess] = useState(false);
  const [invalidConfirmPassword, setInvalidConfirmPassoword] = useState(false);
  const [invalidUsername, setInvalidUsername] = useState(false);
  const [invalidPassword, setInvalidPassword] = useState("");
  const [invalidPhone, setInvalidPhone] = useState(false);
  const [invalidEmail, setInvalidEmail] = useState(false);
  const [invalidDob, setInvalidDob] = useState(false);

  const checkValidPasswordConfirm = () => {
    return password === confirmPassword;
  };

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

  function extractDob() {
    const arr = dob.split("-");
    return arr;
  }

  function handleRegister(e: any) {
    e.preventDefault();
    setLoading(true);
    if (checkAllFieldsFilled() && checkValidPasswordConfirm()) {
      const dobArray = extractDob();
      axiosInstance
        .post("/api/register", {
          username: account,
          password: password,
          fullName: fullName,
          dateOfBirth:
            dobArray[0] +
            "-" +
            dobArray[1].padStart(2, "0") +
            "-" +
            dobArray[2].padStart(2, "0"),
          gender: gender.toUpperCase(),
          email: email,
          address: address,
          phone: phoneNumber,
          identityCard: identityCard,
        })
        .then((response) => {
          setSuccess(true);
          setTimeout(() => {
            router.push("/login");
          }, 1500);
        })
        .catch((error) => {
          console.log(error)
          setTimeout(() => {
            setLoading(false);
            if (error.response.data.errorCode === 16) {
              setInvalidPassword(error.response.data.message)
              setInvalidConfirmPassoword(false);
            };
          }, 1000);
        });
    } else {
      setTimeout(() => {
        setLoading(false);
        if (!checkAllFieldsFilled()) {
          setInvalid(true);
        } else {
          setInvalidConfirmPassoword(true);
          setInvalidPassword("");
        }
      }, 1000);
    }
  }

  if (success) {
    return <Loading />;
  }

  function handleRedirectLogin() {
    router.push("/login");
  }

  return (
    <div className="w-full h-full flex flex-col items-center justify-center gap-3 bg-[#EFF0F3]">
      <div className="flex flex-col w-[35%] bg-white border-solid border-[1px] border-[#BEC8CF] rounded-[0.5rem] pb-4 shadow-[0_4px_4px_rgba(0, 0, 0, 0.25)] px-10">
        <span className="flex justify-center mx-3 pt-4 text-center text-[2rem] text-black font-[700] mb-5">
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
                checked={gender.toLowerCase() === "male"}
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
                checked={gender.toLowerCase() === "female"}
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
          <button
            className="flex flex-row justify-center items-center p-[10px] mb-4 bg-[#337AB7] rounded-[7px] text-white mt-3"
            disabled={loading}
          >
            <div className="flex flex-row items-center gap-2">
              {loading ? (
                <div className="block h-4 w-4 animate-spin rounded-full border-2 border-t-transparent border-solid border-white"></div>
              ) : (
                <Image src={loginIcon} alt="" />
              )}
              <span>{loading ? "Waiting..." : "Register"}</span>
            </div>
          </button>
        </form>
        {invalid && (
          <span className="text-red-500 block self-center font-medium">
            Please fill in all the information above!
          </span>
        )}
        {wrongData && (
          <span className="text-red-500 block self-center font-medium">
            Please revalidate the information above!
          </span>
        )}
        {invalidConfirmPassword && (
          <span className="text-red-500 block self-center font-medium">
            The password and password confirmation must be the same!
          </span>
        )}
        {invalidPassword !== "" && (
            <span className="text-red-500 block self-center font-bold">
              {invalidPassword.toUpperCase()}
            </span>
          )}
      </div>
      <span className="text-black font-medium">
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
