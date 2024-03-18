"use client";

import Image from "next/image";
import avaBlank from "@/public/avaBlank.png";
import { useState } from "react";
import axios from "axios";
import { useRouter } from "next/navigation";

export default function Home() {
  const router = useRouter();
  const [fileName, setFileName] = useState<File>();
  const [account, setAccount] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [fullName, setFullName] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [gender, setGender] = useState("male");
  const [identityCard, setIdentityCard] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");

  const handleFileChange = (e: any) => {
    setFileName(e.target.files[0]);
  };

  function checkFormFilled() {
    const allStringsFilled =
      account &&
      password &&
      confirmPassword &&
      fullName &&
      dateOfBirth &&
      gender &&
      identityCard &&
      email &&
      address &&
      phoneNumber;
    const fileSelected = fileName !== undefined;
    console.log(allStringsFilled && fileSelected);
    return allStringsFilled && fileSelected;
  }

  function handleEditProfile() {
    if (checkFormFilled()) {
      axios.post("", {}).then((response) => {
        console.log(response.data);
        router.push("/admin/dashboard/movies");
      });
    }
  }

  return (
    <div className="bg-[#EFF0F3] p-10 text-black h-full overflow-auto">
      <div className="bg-white flex flex-col items-center gap-3 pb-10">
        <span className="font-[700] border-b-[1px] w-full py-5 text-center text-[1.2rem]">
          Account Information
        </span>
        <div className="w-full flex items center justify-center pt-5">
          <Image src={avaBlank} alt="" />
        </div>
        <form
          className="w-[95%] bg-white px-[10px] flex flex-col gap-3"
          onSubmit={() => handleEditProfile()}
        >
          <label
            htmlFor="account"
            className="block text-sm font-medium text-gray-700"
          >
            Account:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="account"
            type="text"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            disabled
            value={account}
          />
          <label
            htmlFor="password"
            className="block text-sm font-medium text-gray-700"
          >
            Password:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="password"
            type="text"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            onChange={(e) => setPassword(e.target.value)}
          />
          <label
            htmlFor="confirm_pass"
            className="block text-sm font-medium text-gray-700"
          >
            Confirm password:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="confirm_pass"
            type="text"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
          <label
            htmlFor="full_name"
            className="block text-sm font-medium text-gray-700"
          >
            Full name:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="full_name"
            type="text"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            onChange={(e) => setFullName(e.target.value)}
          />
          <label
            htmlFor="dob"
            className="block text-sm font-medium text-gray-700"
          >
            Date of birth:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="dob"
            type="date"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            onChange={(e) => setDateOfBirth(e.target.value)}
          />
          <label
            htmlFor="gender"
            className="block text-sm font-medium text-gray-700 mt-2"
          >
            Gender:
            <span className="text-red-500">*</span>
          </label>
          <div className="text-black flex flex-row gap-2">
            <div className="flex flex-row gap-1 items-center justify-between">
              <input
                type="radio"
                value="male"
                id="male"
                name="gender"
                className="mt-[3px]"
                checked={gender === "male"}
                onChange={(e) => setGender(e.target.value)}
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
                checked={gender === "female"}
                onChange={(e) => setGender(e.target.value)}
              />
              <label htmlFor="female">Nữ</label>
            </div>
          </div>
          <label
            htmlFor="identity"
            className="block text-sm font-medium text-gray-700 mt-2"
          >
            Identity card:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="identity"
            type="text"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            disabled
            value={identityCard}
          />
          <label
            htmlFor="email"
            className="block text-sm font-medium text-gray-700"
          >
            Email:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="email"
            type="email"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] h-10 p-2"
            onChange={(e) => setEmail(e.target.value)}
          />
          <label
            htmlFor="address"
            className="block text-sm font-medium text-gray-700"
          >
            Address:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="address"
            type="text"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            onChange={(e) => setAddress(e.target.value)}
          />
          <label
            htmlFor="phone"
            className="block text-sm font-medium text-gray-700"
          >
            Phone:
            <span className="text-red-500">*</span>
          </label>
          <input
            id="phone"
            type="text"
            className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
            onChange={(e) => setPhoneNumber(e.target.value)}
          />
          <div>
            <label
              htmlFor="file-upload"
              className="block text-sm font-medium text-gray-700 mr-2 mb-3"
            >
              Image:
              <span className="text-red-500">*</span>
            </label>
            <div className="border-[1px] border-solid border-black rounded-[5px] p-1">
              <button
                type="button"
                onClick={() => document.getElementById("file-upload")?.click()}
                className="bg-gray-200 rounded p-1 text-sm mr-4 text-black" // Added mr-4 for margin to the right of the button
              >
                Choose File
              </button>
              <input
                id="file-upload"
                type="file"
                className="hidden"
                onChange={handleFileChange}
              />
              {fileName && (
                <span className="text-sm text-black">{fileName.name}</span>
              )}
            </div>
          </div>
          <div className="flex flex-row items-center gap-4 mt-5">
            <button
              type="submit"
              className="p-2 bg-[#337AB7] w-[5rem] rounded-[5px] text-white"
            >
              Save
            </button>
            <button
              type="reset"
              className="p-2 bg-[#337AB7] w-[5rem] rounded-[5px] text-white"
            >
              Close
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
