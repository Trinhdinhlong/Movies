"use client";

import axiosInstance from "@/axios";
import { useEffect, useState } from "react";

interface Ticket {
  createDate: string;
  movieNameVN: string;
}

export default function Home() {
  const [tickets, setTickets] = useState<Ticket[]>([]);
  const [noRecord, setNoRecord] = useState(false);
  useEffect(() => {
    const userName = localStorage.getItem("account");
    if (userName) {
      axiosInstance
        .get(`/api/ticket/history/${userName}`)
        .then((response) => setTickets(response.data))
        .catch(error => {
          console.log(error)
          if(error.errorCode === 6) {
            setNoRecord(true)
          }
        });
    }
  }, []);

  return (
    <div className="bg-[#EFF0F3] w-full h-full px-10 py-20 overflow-auto">
      <div className="mx-auto w-full">
        <div className="bg-white shadow-md rounded my-6">
          <table className="min-w-max w-full table-auto">
            <thead>
              <tr className="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
                <th className="py-3 px-6 text-left">#</th>
                <th className="py-3 px-6 text-left">Movie Name</th>
                <th className="py-3 px-6 text-center">Booking Date</th>
              </tr>
            </thead>
            <tbody className="text-gray-600 text-sm font-light">
              {tickets.map((el, index) => (
                <tr
                  key={index}
                  className="border-b border-gray-200 hover:bg-gray-100"
                >
                  <td className="py-3 px-6 text-left whitespace-nowrap">
                    {index+1}
                  </td>
                  <td className="py-3 px-6 text-left">{el.movieNameVN}</td>
                  <td className="py-3 px-6 text-center">{el.createDate}</td>
                </tr>
              ))}
            </tbody>
          </table>
          {noRecord && <span className="text-gray-200 block self-center font-medium">
            No records found!
          </span>}
        </div>
      </div>
    </div>
  );
}
