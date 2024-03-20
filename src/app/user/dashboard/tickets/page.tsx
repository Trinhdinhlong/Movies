"use client"

import axios from "axios";
import { useEffect, useState } from "react";

interface Ticket {
  movieNameVN: string;
  totalAmount: number;
  startTime: string;
  ticketType: string;
}

export default function Home() {
  const [tickets, setTickets] = useState<Ticket[]>([]);
  useEffect(() => {
    axios.get("http://localhost:8080/api/ticket/booked")
    console.log()
  }, []);

  return (
    <div className="bg-[#EFF0F3] w-full h-full px-10 py-20">
      <div className="bg-white w-full pb-7">
        <span className="py-[15px] font-bold text-center block w-full">
          Booked Ticket
        </span>
        <div className="flex flex-row px-[10px] justify-between">
          <div className="flex flex-row gap-2 ml-5 flex flex-row items-center">
            <span>Show</span>
            <select className="flex flex-row justify-center w-[5rem] text-center">
              <option value={10}>10</option>
            </select>
            <span>entries</span>
          </div>
          <div className="flex flex-row items-center gap-3">
            <span>Search:</span>
            <input
              type="search"
              className="border-[1px] border-[#BEC8CF] outline-none rounded-[5px] h-[1.8rem] pl-3 "
            />
          </div>
        </div>
        <div className="w-full px-[10px] mt-5">
          <table className="w-full text-left">
            <thead>
              <tr className="border-b-[1px] border-solid border-[#BEC8CF]">
                <th className="w-[10%] py-[10px]">#</th>
                <th className="py-[10px]">MOVIE NAME</th>
                <th className="py-[10px]">BOOKING DATE</th>
                <th className="py-[10px]">TOTAL AMOUNT</th>
                <th className="py-[10px]">STATUS</th>
              </tr>
            </thead>
            <tbody>
              {tickets.map((ticket, index) => (
                <tr key={ticket.movieNameVN} className="border-b-[1px] border-solid border-[#BEC8CF]">
                  <td className="py-[10px]">{index}</td>
                  <td className="py-[10px]">{ticket.movieNameVN}</td>
                  <td className="py-[10px]">{ticket.startTime}</td>
                  <td className="py-[10px]">{ticket.totalAmount}</td>
                  <td className="py-[10px] text-[#2036F8]">
                    {ticket.ticketType}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className="px-[10px] ml-5 flex flex-row justify-end mt-7">
          <div className="flex flex-row border-[1px] border-solid border-[#BEC8CF] rounded-[5px]">
            <span className="py-[8px] px-[12px]">Preview</span>
            <span className="py-[8px] px-[12px] bg-[#3C99EA] text-white">1</span>
            <span className="py-[8px] px-[12px]">Next</span>
          </div>
        </div>
      </div>
    </div>
  );
}
