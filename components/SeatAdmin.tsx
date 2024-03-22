"use client";

import { useState } from "react";

export default function SeatAdmin(props: any) {
  const [selecting, setSelecting] = useState(false);

  const seatVip = {
    outline: "#3C99EA",
    inside: "#89CCF2",
  };
  const seatCanChoose = {
    outline: "#BEC8Cf",
    inside: "#EFF0F3",
  };

  const seatColors = props.seatType === "VIP" ? seatVip : seatCanChoose;

  function handleOnClick() {
    // Call the passed handler from parent component
    props.handleAddSeat(props.id);
  }

  return (
    <div
      className={`w-[3rem] h-[2.5rem] text-[#655B5B] flex flex-row 
        items-center justify-center rounded-[10px]
        pt-[0.4rem] pl-[0.5rem] pb-[0.588rem] pr-[0.353rem]
        cursor-pointer bg-[${seatColors.outline}]`}
        style={{backgroundColor: seatColors.outline}}
      onClick={handleOnClick}
    >
      <span
        className={`w-[2rem] h-[1.875rem] text-center
        rounded-[5px] `}
        style={{ backgroundColor: seatColors.inside }}
      >
        {props.seatColumn + props.seatRow}
      </span>
    </div>
  );
}
