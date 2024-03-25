"use client";

import axiosInstance from "@/axios";
import avaBlank from "@/public/avaBlank.png";
import Image from "next/image";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

interface Role {
  id: number;
  roleName: string;
  createdDate: string;
  updatedTime: string;
}

interface User {
  userId: number;
  username: string;
  password: string;
  fullName: string;
  dateOfBirth: string;
  gender: string;
  email: string;
  address: string;
  phone: string;
  identityCard: string;
  imageURL: string;
  role: Role;
}

export default function SidebarProfile() {
  const router = useRouter();
  const [user, setUser] = useState<User>();

  function handleTicketBooked() {
    router.push(`/user/dashboard/tickets/booked`);
  }
  function handleTicketCancel() {
    router.push(`/user/dashboard/tickets/canceled`);
  }

  function handleHistory() {
    router.push("/user/dashboard/tickets/history");
  }

  function handleUserInformation() {
    router.push("/user/dashboard/edit");
  }

  useEffect(() => {
    const userName = localStorage.getItem("account");
    async function getData() {
      await axiosInstance.get(`/api/user/${userName}`).then((response) => {
        setUser(response.data);
      });
    }
    getData();
  }, []);

  return (
    <div className="w-1/6 min-w-max bg-white p-4">
      <div className="flex flex-col items-center">
        <div className="w-24 h-24 mb-3 rounded-full">
          <img
            src={
              process.env.NEXT_PUBLIC_API_BASE_URL + "/images/" + user?.imageURL
            }
            alt=""
            className="rounded-full w-full h-full object-cover"
          />
        </div>
        <h2 className="text-center text-xl mb-5">{user?.fullName}</h2>
        <div className="flex items-center mb-6">
          <span className="text-yellow-400 mr-2">⭐</span>
          <span>Score: 36.500</span>
        </div>
        <div className="w-full">
          <span
            className="block py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out cursor-pointer"
            onClick={handleUserInformation}
          >
            Account Information
          </span>
          <span
            className="block py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out cursor-pointer"
            onClick={handleHistory}
          >
            History
          </span>
          <span
            className="block py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out cursor-pointer"
            onClick={() => handleTicketBooked()}
          >
            Booked ticket
          </span>
          <span
            className="block py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out cursor-pointer"
            onClick={() => handleTicketCancel()}
          >
            Canceled ticket
          </span>
        </div>
      </div>
    </div>
  );
}
