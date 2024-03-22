"use client";

import axios from "axios";
import { useEffect, useState } from "react";

interface Ticket {
  createDate: string;
  movieNameVN: string;
}

export default function Home() {
  const [tickets, setTickets] = useState<Ticket[]>([]);
  useEffect(() => {
    axios
      .get("https://9817-14-232-224-226.ngrok-free.app/api/ticket/history/1", {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      })
      .then((response) => setTickets(response.data));
  }, []);

  return (
    <div className="bg-[#EFF0F3] w-full h-full px-10 py-20">
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
                <tr key={index} className="border-b border-gray-200 hover:bg-gray-100">
                  <td className="py-3 px-6 text-left whitespace-nowrap">
                    {index}
                  </td>
                  <td className="py-3 px-6 text-left">{el.movieNameVN}</td>
                  <td className="py-3 px-6 text-center">{el.createDate}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
