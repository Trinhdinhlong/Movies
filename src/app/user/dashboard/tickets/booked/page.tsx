"use client";

import axios from "axios";
import { useEffect, useState } from "react";

interface Ticket {
  movieNameVN: string;
  totalAmount: number;
  startDate: string;
  ticketType: string;
}

interface Params {
  slug: string;
}

interface SearchParams {
  [key: string]: string;
}

export default function Home({
  params,
  searchParams,
}: {
  params: Params;
  searchParams: SearchParams;
}) {
  const [tickets, setTickets] = useState<Ticket[]>([]);
  const ticket = searchParams.ticket;
  useEffect(() => {
    axios
      .get("https://9817-14-232-224-226.ngrok-free.app/api/ticket/booked/1", {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      })
      .then((response) => setTickets(response.data));
  }, []);

  return (
    <div className="bg-[#EFF0F3] w-full h-full px-10 py-20">
      <div className="w-full bg-white mx-auto p-4">
        <div className="flex justify-between mb-4">
          <div>
            <label
              htmlFor="entries"
              className="text-sm font-medium text-gray-700 mr-2"
            >
              Show
            </label>
            <select
              id="entries"
              name="entries"
              className="border border-gray-300 rounded-md p-1 pl-6 shadow-sm focus:ring-indigo-500 focus:border-indigo-500 w-[5rem]"
            >
              <option value="10">10</option>
              <option value="20">20</option>
              {/* Add more options as needed */}
            </select>
            <span className="text-sm font-medium text-gray-700 ml-2">
              entries
            </span>
          </div>
          <div>
            <input
              type="text"
              placeholder="Search:"
              className="border border-gray-300 rounded-md p-1 shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
            />
          </div>
        </div>

        <div className="bg-white overflow-hidden shadow rounded-lg">
          <div className="px-4 py-5 sm:px-6 border-b border-gray-200">
            <h3 className="text-lg leading-6 font-medium text-gray-900">
              Booked Tickets
            </h3>
          </div>
          <div className="p-4">
            <table className="min-w-full divide-y divide-gray-200">
              <thead>
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    #
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Movie Name
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Booking Date
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Total Amount
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Status
                  </th>
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {tickets.map((el, index) => (
                  <tr>
                    <td className="px-6 py-3 text-left text-xs text-gray-500 uppercase tracking-wider">
                      {index}
                    </td>
                    <td className="px-6 py-3 text-left text-xs text-gray-500 uppercase tracking-wider">
                      {el.movieNameVN}
                    </td>
                    <td className="px-6 py-3 text-left text-xs text-gray-500 uppercase tracking-wider">
                      {el.startDate}
                    </td>
                    <td className="px-6 py-3 text-left text-xs text-gray-500 uppercase tracking-wider">
                      {el.totalAmount}
                    </td>
                    <td className="px-6 py-3 text-left text-xs text-green uppercase tracking-wider">
                      {el.ticketType}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>

        <div className="flex justify-between items-center mt-4">
          <span className="text-sm font-medium text-gray-700">
            Showing 1 to 10 of 100 entries
          </span>
          <div className="inline-flex">
            <button className="text-sm bg-gray-200 hover:bg-gray-300 text-gray-800 font-semibold py-2 px-4 rounded-l">
              Prev
            </button>
            <button className="text-sm bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4">
              1
            </button>
            <button className="text-sm bg-gray-200 hover:bg-gray-300 text-gray-800 font-semibold py-2 px-4 rounded-r">
              Next
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
