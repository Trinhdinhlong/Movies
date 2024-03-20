"use client"

import { useState } from "react";

export default function Seat(props: any) {
  const [selecting, setSelecting] = useState(false);

  const seatVip = {
    outline: "#3C99EA",
    inside: "#89CCF2",
  };
  const seatCanChoose = {
    outline: "#BEC8Cf",
    inside: "#EFF0F3",
  };
  const seatSold = {
    outline: "#F30E0E",
    inside: "#F2A9B6",
  };
  const seatSelecting = {
    outline: "#18C53E",
    inside: "#86EB21",
  };

  function handleOnClick() {
    if (props.available) {
      // Prevent toggling selection if the seat is sold
      setSelecting(!selecting);
    }
  }

  // Determine the outline color
  const outlineColor = !props.available
    ? seatSold.outline
    : selecting
    ? seatSelecting.outline
    : props.seatType === "VIP"
    ? seatVip.outline
    : seatCanChoose.outline;

  // Determine the inside color
  const insideColor = !props.available
    ? seatSold.inside
    : selecting
    ? seatSelecting.inside
    : props.seatType === "VIP"
    ? seatVip.inside
    : seatCanChoose.inside;

  return (
    <div
      className={`w-[3rem] h-[2.5rem] text-[#655B5B] flex flex-row 
        items-center justify-center rounded-[10px]
        pt-[0.4rem] pl-[0.5rem] pb-[0.588rem] pr-[0.353rem]
        cursor-pointer bg-[${outlineColor}]`}
        style={{backgroundColor: outlineColor}}
      onClick={handleOnClick}
    >
      <span
        className={`w-[2rem] h-[1.875rem] text-center
        rounded-[5px] `}
        style={{backgroundColor: insideColor}}
      >
        {props.seatColumn + props.seatRow}
      </span>
    </div>
  );
}
