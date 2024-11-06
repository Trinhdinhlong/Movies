"use client";

import Image from "next/image";
import defaultAva from "@/public/defaultAva.jpg";
import { useEffect, useState } from "react";
import MovieConfirmation from "components/TableMovieConfirm";
import UserConfirmation from "components/UserConfirmation";
import { useRouter } from "next/navigation";
import axiosInstance from "@/axios";
import Loading from "components/LoadingScreen";

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

interface User {
  address: string;
  dateOfBirth: string;
  email: string;
  fullName: string;
  gender: "MALE" | "FEMALE";
  identityCard: string;
  password: string;
  phone: string;
  role: Role;
  createdDate: string;
  id: number;
  roleName: string;
  updatedTime: string;
  userId: number;
  username: string;
}

interface Role {
  createdDate: string;
  id: number;
  roleName: string;
  updatedTime: string;
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
  const [success, setSuccess] = useState(false);
  const [userInfo, setUserInfo] = useState<User>();
  const [loading, setLoading] = useState(false);
  const arrayData: {
    seatId: number;
    showTimeId: string;
    userId: number | undefined;
  }[] = [];
  seatObjArray.map((el) =>
    arrayData.push({
      seatId: el.id,
      showTimeId: showTimeId,
      userId: userInfo?.userId,
    })
  );
  const [roomName, setRoomName] = useState<Room>();
  useEffect(() => {
    if(localStorage.getItem("isLogin") === null) {
      router.push("/login")
    }
  })
  //movieName, date, time, seat, price

  useEffect(() => {
    axiosInstance.get(`/api/room/${roomId}`).then((response) => {
      setRoomName(response.data);
    });
  }, []);

  useEffect(() => {
    if (localStorage.getItem("account"))
      axiosInstance
        .get(`/api/user/${localStorage.getItem("account")}`)
        .then((response) => {
          setUserInfo(response.data);
        });
  }, []);

  async function handleConfirmBooking() {
    await axiosInstance
      .post(
        "http://localhost:8080/api/ticket/booking",
        arrayData,
        {
          headers: {
            "ngrok-skip-browser-warning": "skip-browser-warning",
          },
        }
      )
      .then((response) => {
        setSuccess(true);
      });
  }

  if (loading) {
    return <Loading />;
  }
  function handleBack() {
    setTimeout(() => {
      setLoading(true);
    }, 1000);
    router.push("/user/dashboard/home");
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
              <UserConfirmation
                fullName={userInfo?.fullName}
                email={userInfo?.email}
                identityCard={userInfo?.identityCard}
                phoneNumber={userInfo?.phone}
              />
            </div>
          </div>
          {success && (
            <span className="text-green-500 block text-center font-bold mt-5">
              You have successfully confirmed the ticket!
            </span>
          )}
          {success && (
            <span className="text-red-500 block text-center font-bold">
              Please pick up the ticket 30 minutes before the show. After that
              time ticket will automatically cancel!
            </span>
          )}
        </div>
        <button
          className="text-white p-[10px] bg-[#337AB7] font-[600] rounded-[5px] self-end mt-2"
          onClick={!success ? handleConfirmBooking : handleBack}
        >
          {!success ? "Confirm booking ticket" : "Back"}
        </button>
      </div>
    </div>
  );
}
