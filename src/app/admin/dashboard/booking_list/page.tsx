"use client"

import axiosInstance from "@/axios";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

interface TicketDetails {
  ticketId: number;
  userId: number;
  fullName: string;
  identityCard: string;
  phoneNumber: string;
  movieNameVN: string;
  startTime: string;
  seatColumn: string;
  seatRow: number;
  ticketType: string;
}

export default function Home() {
  const [bookings, setBookings] = useState<TicketDetails[]>([]);
  const [search, setSearch] = useState("")
  const router = useRouter()
  useEffect(() => {
    axiosInstance
      .get(`/api/ticket/booking`)
      .then((response) => {
        setBookings(response.data);
      });
  }, []);

  function handleConfirmTicket(type: string, ticketId: number) {
    router.push(`/admin/dashboard/booking_list/confirmation?type=${type}&ticketId=${ticketId}`)
  }

  const listFilteredBooking = bookings.filter(el => el.fullName.toLowerCase().includes(search.toLowerCase()))

  return (
    <div className="bg-[#EFF0F3] w-full h-full flex flex-col items-center overflow-auto">
      <div className="w-[96%] bg-white mt-8 mb-10">
        <div className="flex justify-between items-center p-4 bg-white">
          <h1 className="text-xl font-semibold">Booking List</h1>
          <div className="flex space-x-2">
            <input
              className="px-4 py-2 border rounded-md"
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              placeholder="Search"
            />
            <button className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none">
              Search
            </button>
          </div>
        </div>
        <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
          <table className="w-full text-sm text-left text-gray-500">
            <thead className="text-xs text-gray-700 uppercase bg-gray-50">
              <tr>
                <th scope="col" className="py-3 px-6">
                  #
                </th>
                <th scope="col" className="py-3 px-6">
                  Booking ID
                </th>
                <th scope="col" className="py-3 px-6">
                  Member ID
                </th>
                <th scope="col" className="py-3 px-6">
                  Full name
                </th>
                <th scope="col" className="py-3 px-6">
                  Identity card
                </th>
                <th scope="col" className="py-3 px-6">
                  Phone number
                </th>
                <th scope="col" className="py-3 px-6">
                  Movie
                </th>
                <th scope="col" className="py-3 px-6">
                  Time
                </th>
                <th scope="col" className="py-3 px-6">
                  Seat
                </th>
                <th scope="col" className="py-3 px-6">
                  Action
                </th>
              </tr>
            </thead>
            <tbody>
              {listFilteredBooking.map((el, index) => (
                <tr className="bg-white border-b">
                  <td className="py-4 px-6">{index+1}</td>
                  <td className="py-4 px-6">{el.ticketId}</td>
                  <td className="py-4 px-6">{el.userId}</td>
                  <td className="py-4 px-6">{el.fullName}</td>
                  <td className="py-4 px-6">{el.identityCard}</td>
                  <td className="py-4 px-6">{el.phoneNumber}</td>
                  <td className="py-4 px-6">{el.movieNameVN}</td>
                  <td className="py-4 px-6">{el.startTime}</td>
                  <td className="py-4 px-6">{el.seatColumn + el.seatRow}</td>
                  <td className="py-4 px-6">
                    <button className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600 focus:outline-none"
                    disabled={el.ticketType === "Abort"}
                    onClick={() => handleConfirmTicket(el.ticketType, el.ticketId)}>
                      {el.ticketType}
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
