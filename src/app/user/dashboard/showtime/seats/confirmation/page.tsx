"use client";

import Image from "next/image";
import defaultAva from "@/public/defaultAva.jpg";
import { useEffect, useState } from "react";
import MovieConfirmation from "components/TableMovieConfirm";
import UserConfirmation from "components/UserConfirmation";
import axios from "axios";
import { useRouter } from "next/navigation";

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

interface SeatObj {
  id: number;
  price: number;
  seatColumn: string;
  seatRow: number;
}

export default function Home({
  params,
  searchParams,
}: {
  params: Params;
  searchParams: SearchParams;
}) {
  const router = useRouter();
  const seatsObj = JSON.parse(searchParams.seats);
  const seatObjArray: SeatObj[] = seatsObj as SeatObj[];
  const roomId = searchParams.roomId;
  const showTime = searchParams.showTime;
  const date = searchParams.date;
  const movieName = searchParams.movieName;
  const showTimeId = searchParams.showTimeId;
  const arrayData: { seatId: number; showTimeId: string; userId: number }[] =
    [];
  seatObjArray.map((el) =>
    arrayData.push({
      seatId: el.id,
      showTimeId: showTimeId,
      userId: 9
    })
  );
  const [roomName, setRoomName] = useState<Room>();
  //movieName, date, time, seat, price

  useEffect(() => {
    axios
      .get(`https://9817-14-232-224-226.ngrok-free.app/api/room/${roomId}`, {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      })
      .then((response) => {
        setRoomName(response.data);
      });
  }, []);

  async function handleConfirmBooking() {
    await axios.post(
      "https://9817-14-232-224-226.ngrok-free.app/api/ticket/booking",
      arrayData,
      {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      }
    ).then(response => console.log(response.data));
    router.push("/user/dashboard/home")
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
                {movieName.toUpperCase()}
              </span>
              <MovieConfirmation
                seatsObj={seatObjArray}
                roomName={roomName?.nameRoom}
                showTime={showTime}
                date={date}
                showTimeId={showTimeId}
              />
              <span className="text-[1.3rem] text-[#337AB7] font-[400] block">
                Check your booking ticket confirmation
              </span>
              <UserConfirmation />
            </div>
          </div>
        </div>
        <button className="text-white p-[10px] bg-[#337AB7] font-[600] rounded-[5px] self-end mt-2"
        onClick={handleConfirmBooking}>
          Confirm booking ticket
        </button>
      </div>
    </div>
  );
}
