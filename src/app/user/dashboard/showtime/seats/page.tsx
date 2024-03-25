"use client";

import Seat from "components/Seat";
import Image from "next/image";
import continueImage from "@/public/Continue.png";
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import axiosInstance from "@/axios";

interface SeatDetail {
  id: number;
  seatColumn: string;
  seatRow: number;
  price: number;
  seatType: string;
  available: boolean;
}

interface Params {
  slug: string;
}

interface SearchParams {
  [key: string]: string;
}

interface SeatChosen {
  id: number;
  seatColumn: string;
  seatRow: number;
  price: number;
}

export default function Home({
  params,
  searchParams,
}: {
  params: Params;
  searchParams: SearchParams;
}) {
  const router = useRouter();
  const movieId = searchParams.movieId;
  const movieName = searchParams.movieName;
  const showTimeId = searchParams.showTimeId;
  const showTime = searchParams.showTime;
  const date = searchParams.date;
  const roomId = searchParams.roomId;
  const [listSeat, setListSeat] = useState<SeatDetail[]>([]);
  const [selectedSeat, setSelectedSeat] = useState<SeatChosen[]>([]);
  useEffect(() => {
    if(localStorage.getItem("isLogin") === null) {
      router.push("/login")
    }
  })

  useEffect(() => {
    if (
      movieId === undefined ||
      movieName === undefined ||
      showTime === undefined ||
      showTimeId === undefined ||
      date === undefined ||
      roomId === undefined
    ) {
      router.push("/error");
    }
  }, []);

  useEffect(() => {
    axiosInstance
      .get(`/api/movie/${movieId}/room/${roomId}/showtime/${showTimeId}/seats`)
      .then((response) => {
        setListSeat(response.data);
      });
  }, [movieId, roomId, showTimeId, showTime, date, roomId]);

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

  function doesInclude(target: SeatChosen) {
    return selectedSeat.filter((el) => el.id === target.id).length > 0;
  }

  function handleAddSeat(target: SeatChosen) {
    if (doesInclude(target)) {
      setSelectedSeat((seat) => seat.filter((el) => el.id !== target.id));
    } else {
      setSelectedSeat((seat) => [...seat, target]);
    }
  }

  function redirectConfirmation() {
    const resultJson = JSON.stringify(selectedSeat);
    const result = encodeURIComponent(resultJson);
    const url = `/user/dashboard/showtime/seats/confirmation?seats=${result}&roomId=${roomId}&showTime=${showTime}&date=${date}&movieName=${movieName}&showTimeId=${showTimeId}`;
    router.push(url);
  }

  return (
    <div className="w-full bg-[#EFF0F3] text-black flex flex-col items-center overflow-auto">
      <div className="flex flex-col items-center w-[70%] gap-2 mb-10">
        <div className="bg-white rounded-[5px] w-full flex flex-col gap-20 py-10 mt-20 justify-center">
          <div className="flex flex-row justify-center gap-20">
            <div className="w-[15%] shrink-0 flex flex-row flex-wrap gap-5 items-start">
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
            <div className="w-[15%] shrink-0 flex flex-row flex-wrap gap-5">
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
        <span
          onClick={redirectConfirmation}
          className="self-end flex flex-row gap-3 items-center justify-center bg-[#337AB7] text-white rounded-[5px] py-[5px] px-[10px] cursor-pointer"
        >
          <Image src={continueImage} alt="" />
          <span className="font-[500]">Continue</span>
        </span>
      </div>
    </div>
  );
}
