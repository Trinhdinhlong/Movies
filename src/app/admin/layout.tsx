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
      <div className="h-[10%]">
        <Header handleOpen={() => handleOpenPopup()} />
      </div>
      {open && (
        <div className="bg-white absolute top-[60px] left-[30%] flex flex-col gap-2 items-start rounded-[5px] p-2 w-[150px] max-h-[200px] overflow-auto text-black font-[500]">
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
        {children}
      </div>
    </div>
  );
}
