"use client";

import Image from "next/image";
import defaultAva from "@/public/defaultAva.jpg";
import { useState } from "react";
import MovieConfirmation from "components/TableMovieConfirm";
import UserConfirmation from "components/UserConfirmation";

export default function Home(props: any) {
  const [movName, setMovName] = useState("");
  const movNameTemp = "Oppen";

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
                {movName.toUpperCase() || movNameTemp.toUpperCase()}
              </span>
              <MovieConfirmation movName={movName} movNameTemp={movNameTemp} />
              <span className="text-[1.3rem] text-[#337AB7] font-[400] block">
                Check your booking ticket confirmation
              </span>
              <UserConfirmation />
            </div>
          </div>
        </div>
        <button className="text-white p-[10px] bg-[#337AB7] font-[600] rounded-[5px] self-end mt-2">
          Confirm booking ticket
        </button>
      </div>
    </div>
  );
}
