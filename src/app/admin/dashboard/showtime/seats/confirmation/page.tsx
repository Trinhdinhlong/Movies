"use client";

import Image from "next/image";
import defaultAva from "@/public/defaultAva.jpg";
import { useState } from "react";
import MovieConfirmation from "components/TableMovieConfirm";
import UserConfirmation from "components/UserConfirmation";
import AdminConfirmation from "components/AdminConfirmation";
import { useRouter } from "next/navigation";
import axios from "axios";

interface Params {
  slug: string;
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

interface Room {
  id: number;
  nameRoom: string;
  seatQuantity: number;
}

interface UserRole {
  id: number;
  roleName: string;
  createdDate: string;
  updatedTime: string;
}

interface UserDetails {
  userId: number;
  username: string;
  password: string;
  fullName: string;
  dateOfBirth: string;
  gender: 'MALE' | 'FEMALE'; // Assuming only these two genders for simplicity; adjust as necessary.
  email: string;
  address: string;
  phone: string;
  identityCard: string;
  role: UserRole;
}


export default function Home({
  params,
  searchParams,
}: {
  params: Params;
  searchParams: SearchParams;
}) {
  const [confirmOpen, setConfirmOpen] = useState(false);
  const [userDetail, setUserDetail] = useState<UserDetails>()
  const [userId, setUserId] = useState("");
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
      userId: userDetail?.userId || 0,
    })
  );
  const [roomName, setRoomName] = useState<Room>();
  //movieName, date, time, seat, price

  function handleConfirmOpen() {
    axios.get(
      `https://9817-14-232-224-226.ngrok-free.app/api/employee/booking/${userId}`,
      {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      }
    ).then(response => {
      setUserDetail(response.data)
    });
    setConfirmOpen(!confirmOpen);
  }

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
    router.push("/admin/dashboard/booking_list")
  }

  return (
    <div className="bg-[#EFF0F3] w-full overflow-auto flex flex-col items-center gap-10 py-10 pb-20 h-full overflow-auto">
      <span className="font-bold text-[1.5rem]">CONFIRM BOOKING TICKET</span>
      <div className="w-[85%] flex flex-col items-center">
        <div className="bg-white w-full px-[3rem] py-[2rem]">
          <div className="flex flex-row gap-7 w-full">
            <div className="grow shrink-0 w-[12rem]">
              <Image src={defaultAva} alt="" />
            </div>
            <div className="flex flex-col gap-5 w-full">
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
              <div className="flex flex-row">
                <div className="grow block w-1/2 font-[500]">
                  <input
                    type="searchbox"
                    className="h-full p-2 text-sm w-[95%] border-[1px] rounded-[5px] border-solid border-black"
                    placeholder="Member ID or Identity card"
                    onChange={(e) => setUserId(e.target.value)}
                  />
                </div>
                <div className="w-1/2">
                  <button
                    className="text-white p-[10px] bg-[#337AB7] font-[600] rounded-[5px] self-end"
                    onClick={handleConfirmOpen}
                  >
                    Check
                  </button>
                </div>
              </div>
              <div className="w-full">
                {confirmOpen && <AdminConfirmation memberID={userDetail?.userId} fullName={userDetail?.fullName} identityCard={userDetail?.identityCard} phone={userDetail?.phone}/>}
              </div>
            </div>
          </div>
        </div>
        <div className=" self-end mt-5 flex flex-row gap-5">
          <button className="text-white p-[10px] bg-[#337AB7] font-[600] rounded-[5px] cursor-pointer"
          onClick={handleConfirmBooking}>
            Confirm booking ticket
          </button>
          <button className="text-white p-[10px] bg-[#337AB7] font-[600] rounded-[5px]">
            Back
          </button>
        </div>
      </div>
    </div>
  );
}
