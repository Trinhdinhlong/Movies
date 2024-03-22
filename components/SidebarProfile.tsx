"use client";

import avaBlank from "@/public/avaBlank.png";
import Image from "next/image";
import { useRouter } from "next/navigation";

export default function SidebarProfile() {
  const router = useRouter();

  function handleTicketBooked() {
    router.push(`/user/dashboard/tickets/booked`);
  }
  function handleTicketCancel() {
    router.push(`/user/dashboard/tickets/canceled`);
  }

  function handleHistory() {
    router.push("/user/dashboard/tickets/history")
  }

  function handleUserInformation() {
    router.push("/user/dashboard/edit")
  }

  return (
    <div className="w-1/6 min-w-max bg-white p-4">
      <div className="flex flex-col items-center">
        <div className="w-24 h-24 mb-3">
          <Image src={avaBlank} alt=""/>
        </div>
        <h2 className="text-center text-xl mb-5">julientlam</h2>
        <div className="flex items-center mb-6">
          <span className="text-yellow-400 mr-2">⭐</span>
          <span>Score: 36.500</span>
        </div>
        <div className="w-full">
          <span className="block py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out cursor-pointer"
          onClick={handleUserInformation}>
            Account Information
          </span>
          <span className="block py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out cursor-pointer"
          onClick={handleHistory}>
            History
          </span>
          <span className="block py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out cursor-pointer"
          onClick={() => handleTicketBooked()}>
            Booked ticket
          </span>
          <span className="block py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out cursor-pointer"
          onClick={() => handleTicketCancel()}>
            Canceled ticket
          </span>
        </div>
      </div>
    </div>
  );
}
