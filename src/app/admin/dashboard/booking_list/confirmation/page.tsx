"use client";

import Image from "next/image";
import defaultAva from "@/public/defaultAva.jpg";
import { useEffect, useState } from "react";
import MovieConfirmation from "components/TableMovieConfirm";
import UserConfirmation from "components/UserConfirmation";
import axios from "axios";
import { useRouter } from "next/navigation";
import MovieConfirmationSingleTicket from "components/MovieConfirmationSingleTicket";

interface Params {
  slug: string;
}

interface Room {
  id: number;
  nameRoom: string;
  seatQuantity: number;
}

interface SearchParams {
  [key: string]: string;
}

interface TicketInfo {
  ticketId: number;
  movieNameVN: string;
  movieNameEN: string;
  room_name: string;
  startDate: string;
  start_time: string;
  seatColumn: string;
  seatRow: number;
  price: number;
  username: string;
  fullName: string;
  identityCard: string;
  phone: string;
}

export default function Home({
  params,
  searchParams,
}: {
  params: Params;
  searchParams: SearchParams;
}) {
    const [ticketInfo, setTicketInfo] = useState<TicketInfo>();
    const type = searchParams.type;
    const ticketId = searchParams.ticketId;
    const [claimed, setClaimed] = useState(false)
  const router = useRouter();


  useEffect(() => {
    axios
      .get(`https://9817-14-232-224-226.ngrok-free.app/api/ticket/admin/${ticketId}`, {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      })
      .then((response) => {
        setTicketInfo(response.data);
      });
  }, []);

    async function handleConfirmBooking() {
      await axios.put(
        `https://9817-14-232-224-226.ngrok-free.app/api/ticket/${ticketId}`,
        {
          headers: {
            "ngrok-skip-browser-warning": "skip-browser-warning",
          },
        }
      ).then(response => setClaimed(true));
    }

    function handleBack() {
      router.push("/admin/dashboard/booking_list")
    }

  return (
    <div className="bg-[#EFF0F3] w-full overflow-auto flex flex-col items-center gap-10 py-10 pb-20">
      <span className="font-bold text-[1.5rem]">CONFIRM BOOKING TICKET</span>
      <div className="w-[80%] flex flex-col items-center">
        <div className="bg-white w-full px-[3rem] py-[2rem]">
          <div className="flex flex-row gap-7 w-full">
            <div className="w-[12rem]">
              <Image src={defaultAva} alt="" />
            </div>
            <div className="grow flex flex-col gap-5">
              <span className="text-[1.3rem] text-[#337AB7] font-[400] block">
                {ticketInfo?.movieNameEN.toUpperCase()}
              </span>
              <MovieConfirmationSingleTicket
                roomName={ticketInfo?.room_name}
                showTime={ticketInfo?.start_time}
                date={ticketInfo?.startDate}
                colName={ticketInfo?.seatColumn || ""}
                rowName={ticketInfo?.seatRow || 0}
                price={ticketInfo?.price}
              />
              <span className="text-[1.3rem] text-[#337AB7] font-[400] block">
                Check your booking ticket confirmation
              </span>
              <UserConfirmation />
            </div>
          </div>
        </div>
        <button
          className="text-white p-[10px] bg-[#337AB7] font-[600] rounded-[5px] self-end mt-2"
          onClick={type.includes('Waiting') && !claimed ? handleConfirmBooking : handleBack}
        >
          {type.includes('Waiting') && !claimed ? "Confirm the ticket" : "Back"}
        </button>
      </div>
    </div>
  );
}
