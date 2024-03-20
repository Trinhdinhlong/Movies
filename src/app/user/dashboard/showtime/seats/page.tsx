"use client";

import Seat from "components/Seat";
import Image from "next/image";
import continueImage from "@/public/Continue.png";
import { useEffect, useState } from "react";
import axios from "axios";
import { useRouter, useSearchParams } from "next/navigation";
import Link from "next/link";

interface SeatDetail {
  id: number;
  seatColumn: string;
  seatRow: number;
  price: number;
  seatType: string;
  available: boolean;
}

export default function Home(props: any) {
  const searchParams = useSearchParams();
  const movieId = searchParams.get("movieId");
  const showTimeId = searchParams.get("showTimeId");
  const roomId = searchParams.get("movieId");
  const [listSeat, setListSeat] = useState<SeatDetail[]>([]);
  const [selectedSeat, setSelectedSeat] = useState<number[]>([]);

  useEffect(() => {
    axios
      .get(
        `http://localhost:8080/api/movie/${movieId}/room/${roomId}/showtime/${showTimeId}/seats`
      )
      .then((response) => {
        setListSeat(response.data);
      });
  }, [movieId, roomId, showTimeId]);

  const listSeatLeft = listSeat.filter(
    (seat) =>
      seat.seatColumn === "A" ||
      seat.seatColumn === "B" ||
      seat.seatColumn === "C"
  );
  const listSeatRight = listSeat.filter(
    (seat) =>
      seat.seatColumn !== "A" &&
      seat.seatColumn !== "B" &&
      seat.seatColumn !== "C"
  );

  function handleAddSeat(id: any) {
    if (selectedSeat.includes(id)) {
      setSelectedSeat((seat) => seat.filter((el) => el !== id));
    } else {
      setSelectedSeat((seat) => [...seat, id]);
    }
  }

  console.log(selectedSeat);

  return (
    <div className="w-full bg-[#EFF0F3] text-black flex flex-col items-center overflow-auto">
      <div className="flex flex-col items-center w-[70%] gap-2 mb-10">
        <div className="bg-white rounded-[5px] w-full flex flex-col gap-20 py-10 mt-20 justify-center">
          <div className="flex flex-row justify-center gap-20">
            <div className="w-[25%] shrink-0 flex flex-row flex-wrap gap-5 items-start">
              {listSeatLeft.map((seat) => (
                <Seat
                  key={seat.id}
                  id={seat.id}
                  seatColumn={seat.seatColumn}
                  seatRow={seat.seatRow}
                  price={seat.price}
                  seatType={seat.seatType}
                  available={seat.available}
                  handleAddSeat={handleAddSeat}
                />
              ))}
            </div>
            <div className="w-[25%] shrink-0 flex flex-row flex-wrap gap-5">
              {listSeatRight.map((seat) => (
                <Seat
                  key={seat.id}
                  id={seat.id}
                  seatColumn={seat.seatColumn}
                  seatRow={seat.seatRow}
                  price={seat.price}
                  seatType={seat.seatType}
                  available={seat.available}
                  handleAddSeat={handleAddSeat}
                />
              ))}
            </div>
          </div>
          <div className="flex flex-col items-center justify-center gap-3">
            <span className="font-[500] text-[1.2rem]">Screen</span>
            <div className="h-[1px] w-[70%] bg-[#655B5B]"></div>
            <div className="flex flex-row gap-5">
              <div className="flex flex-row gap-3 items-center justify-center">
                <div className="w-[1rem] h-[1rem] bg-[#18C53E] rounded-[3px]"></div>
                <span>Seat is selecting</span>
              </div>
              <div className="flex flex-row gap-3 items-center justify-center">
                <div className="w-[1rem] h-[1rem] bg-[#F30E0E] rounded-[3px]"></div>
                <span>Seat is sold</span>
              </div>
              <div className="flex flex-row gap-3 items-center justify-center">
                <div className="w-[1rem] h-[1rem] bg-[#BEC8CF] rounded-[3px]"></div>
                <span>Seat can choose</span>
              </div>
              <div className="flex flex-row gap-3 items-center justify-center">
                <div className="w-[1rem] h-[1rem] bg-[#3C99EA] rounded-[3px]"></div>
                <span>Seat is VIP</span>
              </div>
            </div>
          </div>
        </div>
        <Link
          href={`/user/dashboard/showtime/seat/confirmation?seats=${selectedSeat}&`}
          className="self-end flex flex-row gap-3 items-center justify-center bg-[#337AB7] text-white rounded-[5px] py-[5px] px-[10px]"
        >
          <Image src={continueImage} alt="" />
          <span className="font-[500]">Continue</span>
        </Link>
      </div>
    </div>
  );
}
