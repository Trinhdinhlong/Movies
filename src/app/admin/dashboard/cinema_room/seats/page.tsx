"use client";

import Image from "next/image";
import continueImage from "@/public/Continue.png";
import { useEffect, useState } from "react";
import axios from "axios";
import { useRouter, useSearchParams } from "next/navigation";
import Link from "next/link";
import SeatAdmin from "components/SeatAdmin";
import axiosInstance from "@/axios";

interface SeatDetail {
  id: number;
  seatColumn: string;
  seatRow: number;
  seatType: string;
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
  const router = useRouter();
  const cinemaId = searchParams.id;
  const [listSeat, setListSeat] = useState<SeatDetail[]>([]);
  const [selectedSeat, setSelectedSeat] = useState<number[]>([]);

  useEffect(() => {
    axiosInstance.get(`/api/room/${cinemaId}/seats`).then((response) => {
      setListSeat(response.data);
    });
  }, []);

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

  function handleAddSeat(selectedId: any) {
    // Toggle seat type upon selection
    const updatedList = listSeat.map((seat) => {
      if (seat.id === selectedId) {
        return {
          ...seat,
          seatType: seat.seatType === "VIP" ? "NORMAL" : "VIP", // Toggle between VIP and NORMAL
        };
      }
      return seat;
    });
    setListSeat(updatedList); // Update the state with the new list

    // Optionally update the selection state if necessary
    // This part depends on whether you also need to track selected seats separately
    if (selectedSeat.includes(selectedId)) {
      setSelectedSeat(selectedSeat.filter((id) => id !== selectedId));
    } else {
      setSelectedSeat([...selectedSeat, selectedId]);
    }
  }

  function handleBack() {
    router.push("/admin/dashboard/cinema_room");
  }

  function handleUpdateSeat() {
    axiosInstance
      .put(
        `/api/seats`,
        listSeat.map((el) => ({
          id: el.id,
          seatType: el.seatType,
        }))
      )
      .then((response) => {
        router.push("/admin/dashboard/cinema_room");
      });
  }

  return (
    <div className="w-full bg-[#EFF0F3] text-black flex flex-col items-center overflow-auto h-full">
      <div className="flex flex-col items-center w-[70%] gap-2 mb-10">
        <div className="bg-white rounded-[5px] w-full flex flex-col gap-20 py-10 mt-20 justify-center">
          <div className="flex flex-row justify-center gap-20">
            <div className="w-[20%] shrink-0 flex flex-row flex-wrap gap-5 items-start">
              {listSeatLeft.map((seat) => (
                <SeatAdmin
                  key={seat.id}
                  id={seat.id}
                  seatColumn={seat.seatColumn}
                  seatRow={seat.seatRow}
                  seatType={seat.seatType}
                  handleAddSeat={handleAddSeat}
                />
              ))}
            </div>
            <div className="w-[20%] shrink-0 flex flex-row flex-wrap gap-5">
              {listSeatRight.map((seat) => (
                <SeatAdmin
                  key={seat.id}
                  id={seat.id}
                  seatColumn={seat.seatColumn}
                  seatRow={seat.seatRow}
                  seatType={seat.seatType}
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
                <div className="w-[1rem] h-[1rem] bg-[#3C99EA] rounded-[3px]"></div>
                <span>Seat is VIP</span>
              </div>
              <div className="flex flex-row gap-3 items-center justify-center">
                <div className="w-[1rem] h-[1rem] bg-[#BEC8CF] rounded-[3px]"></div>
                <span>Seat normal</span>
              </div>
            </div>
          </div>
        </div>
        <div className="flex flex-row gap-3">
          <span className="self-end flex flex-row gap-3 items-center justify-center bg-[#337AB7] text-white rounded-[5px] py-[5px] px-[10px] cursor-pointer">
            <Image src={continueImage} alt="" />
            <span
              className="font-[500] cursor-pointer"
              onClick={handleUpdateSeat}
            >
              Save
            </span>
          </span>
          <span
            onClick={handleBack}
            className="self-end flex flex-row gap-3 items-center justify-center bg-[#337AB7] text-white rounded-[5px] py-[5px] px-[10px] cursor-pointer"
          >
            <span className="font-[500]">X</span>
            <span className="font-[500]">Back</span>
          </span>
        </div>
      </div>
    </div>
  );
}
