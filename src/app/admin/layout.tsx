"use client";

import Header from "components/Header";
import Sidebar from "components/Sidebar";
import { useState } from "react";

export default function RegisterLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const [open, setOpen] = useState(false);

  function handleOpenPopup() {
    setOpen(!open);
  }
  return (
    <div className="flex flex-col overflow-hidden h-screen">
      <div className="h-[10%] w-full bg-[#205081] text-white">
        <Header handleOpen={() => handleOpenPopup()} />
      </div>
      {open && (
        <div className="z-[1] bg-white absolute top-[70px] left-[35%] flex flex-col gap-2 items-start rounded-[5px] p-2 w-[150px] max-h-[200px] overflow-auto text-black font-[500]">
          <span className="cursor-pointer p-2">Action</span>
          <span className="cursor-pointer p-2">Romantic</span>
          <span className="cursor-pointer p-2">Demo1</span>
          <span className="cursor-pointer p-2">Demo2</span>
          <span className="cursor-pointer p-2">Demo2</span>
          <span className="cursor-pointer p-2">Demo2</span>
          <span className="cursor-pointer p-2">Demo2</span>
          <span className="cursor-pointer p-2">Demo2</span>
          <span className="cursor-pointer p-2">Demo2</span>
          <span className="cursor-pointer p-2">Demo2</span>
        </div>
      )}
      <div className="flex flex-row w-full h-[90%]">
        <Sidebar />
        <div className="w-[85%]">{children}</div>
      </div>
    </div>
  );
}
