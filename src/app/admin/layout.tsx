"use client";

import Header from "components/Header";
import Sidebar from "components/Sidebar";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function RegisterLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const router = useRouter();
  useEffect(() => {
    if (
      localStorage.getItem("isLogin") === null ||
      localStorage.getItem("role") === "User"
    ) {
      router.push("/error");
    }
  }, []);

  return (
    <div className="flex flex-col overflow-hidden h-screen">
      <div className="h-[10%] w-full bg-[#205081] text-white">
        <Header />
      </div>
      <div className="flex flex-row w-full h-[90%]">
        <Sidebar />
        <div className="w-[85%]">{children}</div>
      </div>
    </div>
  );
}
