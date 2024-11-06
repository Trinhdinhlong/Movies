"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import avaBlank from "@/public/defaultAva.jpg";
import Image from "next/image";
import axiosInstance from "@/axios";

interface Params {
  slug: string;
}

interface SearchParams {
  [key: string]: string;
}

interface Role {
  id: number;
  roleName: string;
  createdDate: string;
  updatedTime: string;
}

interface Employee {
  userId: number;
  username: string;
  password: string;
  fullName: string;
  dateOfBirth: string;
  gender: "MALE" | "FEMALE"; // Assuming these are the only two options
  email: string;
  address: string;
  phone: string;
  identityCard: string;
  imageURL: string;
  role: Role;
}

export default function Home({
  params,
  searchParams,
}: {
  params: Params;
  searchParams: SearchParams;
}) {
  const router = useRouter();
  const username = searchParams.username;
  const [user, setUser] = useState<Employee>();
  const [id, setId] = useState(0);
  const [account, setAccount] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [fullName, setFullName] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [gender, setGender] = useState("MALE");
  const [identityCard, setIdentityCard] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [image, setImage] = useState<File>();
  const [notReload, setNotReload] = useState(false);

  useEffect(() => {
    async function getData() {
      await axiosInstance.get(`/api/user/${username}`).then((response) => {
        setUser(response.data);
      });
    }
    if (notReload) {
      getData();
      setNotReload(false);
    }
    getData();
  }, [notReload]);

  useEffect(() => {
    setId(user?.userId || 0);
    setAccount(user?.username || "");
    setPassword(user?.password || "");
    setConfirmPassword("");
    setFullName(user?.fullName || "");
    setDateOfBirth(user?.dateOfBirth || "");
    setGender(user?.gender || "");
    setIdentityCard(user?.identityCard || "");
    setEmail(user?.email || "");
    setAddress(user?.address || "");
    setPhoneNumber(user?.phone || "");
  }, [user]);

  const handleFileChange = (e: any) => {
    setImage(e.target.files[0]);
    if (e.target.files[0]) {
      const form = new FormData();
      form.append("imageFile", e.target.files[0]);
      axiosInstance.post("/images", form);
    }
  };

  function handleUpdate(e: any) {
    e.preventDefault();
    axiosInstance
      .put("/api/profile", {
        userId: id,
        username: account,
        password: password,
        fullName: fullName,
        dateOfBirth: dateOfBirth,
        gender: gender,
        email: email,
        address: address,
        phone: phoneNumber,
        identityCard: identityCard,
        imageURL: image?.name,
      })
      .then((response) => {
        router.push("/admin/dashboard/employees");
      });
  }

  function handleBack() {
    router.push("/admin/dashboard/employees");
  }

  return (
    <div className="bg-[#EFF0F3] h-full overflow-auto">
      <div className="flex flex-row text-black w-full">
        <div className="flex flex-col items-center justify-center p-5 w-full overflow-auto">
          <form
            className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4 w-full flex flex-col"
            onSubmit={handleUpdate}
          >
            <div className="mb-4">
              <h1 className="text-lg leading-tight font-bold mb-2 text-center mb-5">
                UPDATE EMPLOYEE
              </h1>
            </div>

            <div className="w-[20rem] h-[20rem] overflow-hidden rounded-[10px] mb-5 self-center">
              <img
                src={
                  process.env.NEXT_PUBLIC_API_BASE_URL +
                  "/images/" +
                  user?.imageURL
                }
                alt=""
                className="w-full h-full object-cover rounded-full border-[1px] border-black"
              />
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
                value={account}
                disabled
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
                value={password}
                placeholder="Password"
                onChange={(e) => setPassword(e.target.value)}
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
                value={fullName}
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
                value={dateOfBirth}
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
                  value="MALE"
                  checked={gender === "MALE"}
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
                  checked={gender === "FEMALE"}
                  value="FEMALE"
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
                disabled
                value={identityCard}
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
                value={email}
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
                value={address}
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
                value={phoneNumber}
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
                  onChange={(e) => handleFileChange(e)}
                />
              </label>
            </div>
            {image && (
              <div className="flex justify-center items-center w-full">
                <p className="text-gray-600">{image.name}</p>
              </div>
            )}
            {/* Repeat for other fields like 'Confirm password', 'Full name', etc. */}

            <div className="flex items-center justify-between">
              <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                Save
              </button>
              <button
                className="bg-transparent hover:bg-gray-500 text-blue-700 font-semibold hover:text-white py-2 px-4 border border-blue-500 hover:border-transparent rounded"
                type="button"
                onClick={handleBack}
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
