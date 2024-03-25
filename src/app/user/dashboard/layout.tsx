"use client";

import axios from "axios";
import Header from "components/Header";
import { useEffect, useState } from "react";

export default function RegisterLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  useEffect(() => {
    axios.get("", )
  })
  return (
    <div className="flex flex-col h-screen overflow-hidden">
      <div className="h-[10%] w-full bg-[#205081] text-white">
        <Header/>
      </div>
      <div className="flex flex-row w-full h-[90%]">
        {children}
      </div>
    </div>
  );
}
