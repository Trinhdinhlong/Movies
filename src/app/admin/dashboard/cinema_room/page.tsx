"use client";

import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

interface RoomDetails {
  id: number;
  nameRoom: string;
  seatQuantity: number;
}

export default function Home() {
  const [rooms, setRooms] = useState<RoomDetails[]>([]);
  const router = useRouter()

  useEffect(() => {
    axios
      .get(`https://9817-14-232-224-226.ngrok-free.app/api/room`, {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      })
      .then((response) => {
        setRooms(response.data);
      });
  });

  function handleSeatDetails(id: any) {
    router.push(`/admin/dashboard/cinema_room/seats?id=${id}`)
  }

  return (
    <div className="bg-[#EFF0F3] w-full h-screen flex flex-col items-center">
      <div className="w-[96%] bg-white mt-8">
        <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
          <div className="flex justify-between items-center p-4 bg-white">
            <button className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none">
              Add new
            </button>
            <div className="flex space-x-2">
              <input
                className="px-4 py-2 border rounded-md"
                placeholder="Search"
              />
              <button className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none">
                Search
              </button>
            </div>
          </div>
          <table className="w-full text-sm text-left text-gray-500">
            <thead className="text-xs text-gray-700 uppercase bg-gray-50">
              <tr>
                <th scope="col" className="py-3 px-6">
                  #
                </th>
                <th scope="col" className="py-3 px-6">
                  Cinema room ID
                </th>
                <th scope="col" className="py-3 px-6">
                  Cinema room
                </th>
                <th scope="col" className="py-3 px-6">
                  Seat quantity
                </th>
                <th scope="col" className="py-3 px-6">
                  Seat detail
                </th>
              </tr>
            </thead>
            <tbody>
              {rooms.map((el, index) => (
                <tr className="bg-white border-b">
                  <td className="py-4 px-6">{index+1}</td>
                  <td className="py-4 px-6">{el.id}</td>
                  <td className="py-4 px-6">{el.nameRoom}</td>
                  <td className="py-4 px-6">{el.seatQuantity}</td>
                  <td className="py-4 px-6">
                    <button className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none"
                    onClick={() => handleSeatDetails(el.id)}>
                      Seat detail
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className="flex justify-between items-center p-4 bg-white">
          <span className="text-sm text-gray-700">
            Showing 1 to 6 of 6 entries
          </span>
          <div className="flex space-x-1">
            <button className="px-3 py-1 border rounded-md hover:bg-gray-200 focus:outline-none">
              Previous
            </button>
            <button className="px-3 py-1 border rounded-md hover:bg-gray-200 focus:outline-none">
              1
            </button>
            <button className="px-3 py-1 border rounded-md hover:bg-gray-200 focus:outline-none">
              Next
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
