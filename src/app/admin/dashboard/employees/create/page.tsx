"use client";
import Image from "next/image";
import { useState } from "react";
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
          <form className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 w-full">
            <div className="mb-4">
              <h1 className="text-lg leading-tight font-bold mb-2">
                Add Employee
              </h1>
            </div>

            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-bold mb-2"
                htmlFor="account"
              >
                Account *
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="account"
                type="text"
                placeholder="Account"
                onChange={(e) => setAccount(e.target.value)}
              />
            </div>
            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-bold mb-2"
                htmlFor="password"
              >
                Password *
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="password"
                type="password"
                placeholder="Password"
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>

            {/* Confirm Password Field */}
            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-bold mb-2"
                htmlFor="confirm-password"
              >
                Confirm Password *
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="confirm-password"
                type="password"
                placeholder="Confirm password"
                onChange={(e) => setConfirmPassword(e.target.value)}
              />
            </div>

            {/* Full Name Field */}
            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-bold mb-2"
                htmlFor="full-name"
              >
                Full Name *
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="full-name"
                type="text"
                placeholder="Full name"
                onChange={(e) => setFullName(e.target.value)}
              />
            </div>

            {/* Date of Birth Field */}
            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-bold mb-2"
                htmlFor="date-of-birth"
              >
                Date of Birth *
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="date-of-birth"
                type="date"
                placeholder="Date of birth"
                onChange={(e) => setDateOfBirth(e.target.value)}
              />
            </div>

            {/* Gender Field */}
            <div className="mb-4">
              <span className="block text-gray-700 text-sm font-bold mb-2">
                Gender *
              </span>
              <div className="flex items-center">
                <input
                  id="gender-male"
                  type="radio"
                  name="gender"
                  className="mr-2"
                  value="male"
                  onChange={(e) => setGender(e.target.value)}
                />
                <label htmlFor="gender-male" className="mr-4">
                  Male
                </label>
                <input
                  id="gender-female"
                  type="radio"
                  name="gender"
                  className="mr-2"
                  value="female"
                  onChange={(e) => setGender(e.target.value)}
                />
                <label htmlFor="gender-female">Female</label>
              </div>
            </div>

            {/* Identity Card Field */}
            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-bold mb-2"
                htmlFor="identity-card"
              >
                Identity Card *
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="identity-card"
                type="text"
                placeholder="Identity card number"
                onChange={(e) => setIdentityCard(e.target.value)}
              />
            </div>

            {/* Email Field */}
            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-bold mb-2"
                htmlFor="email"
              >
                Email *
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="email"
                type="email"
                placeholder="Email"
                onChange={(e) => setEmail(e.target.value)}
              />
            </div>

            {/* Address Field */}
            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-bold mb-2"
                htmlFor="address"
              >
                Address *
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="address"
                type="text"
                placeholder="Address"
                onChange={(e) => setAddress(e.target.value)}
              />
            </div>

            {/* Phone Number Field */}
            <div className="mb-4">
              <label
                className="block text-gray-700 text-sm font-bold mb-2"
                htmlFor="phone-number"
              >
                Phone Number *
              </label>
              <input
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                id="phone-number"
                type="tel"
                placeholder="Phone number"
                onChange={(e) => setPhoneNumber(e.target.value)}
              />
            </div>
            <div className="flex justify-center items-center w-full">
              <label className="flex flex-col justify-center items-center px-4 py-6 bg-white text-blue rounded-lg shadow-lg tracking-wide uppercase border border-blue cursor-pointer hover:bg-blue-500 hover:text-white">
                <svg
                  className="w-8 h-8"
                  fill="currentColor"
                  xmlns="http://www.w3.org/2000/svg"
                  viewBox="0 0 20 20"
                >
                  <path d="M16.7,5.3,10,11.9,3.3,5.3a1,1,0,0,0-1.4,1.4l7,7a1,1,0,0,0,1.4,0l7-7A1,1,0,0,0,16.7,5.3Z" />
                </svg>
                <span className="mt-2 text-base leading-normal">
                  Select a file
                </span>
                <input
                  type="file"
                  className="hidden"
                  onChange={(e) => setImage(e.target.value)}
                />
              </label>
            </div>
            {image && (
              <div className="flex justify-center items-center w-full">
                <p className="text-gray-600">{image}</p>
              </div>
            )}
            <div className="flex justify-center items-center w-full">
              <button className="mt-2 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700 focus:outline-none focus:shadow-outline">
                Upload
              </button>
            </div>
            {/* Repeat for other fields like 'Confirm password', 'Full name', etc. */}

            <div className="flex items-center justify-between">
              <button
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                type="button"
              >
                Save
              </button>
              <button
                className="bg-transparent hover:bg-gray-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded"
                type="button"
              >
                Back
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
