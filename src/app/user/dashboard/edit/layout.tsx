"use client";

import Header from "components/Header";
import SidebarProfile from "components/SidebarProfile";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function EditLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const router = useRouter();
  useEffect(() => {
    if(localStorage.getItem("isLogin") === null) {
      router.push("/login")
    }
  })

  return (
    <div className="flex flex-1 flex-row w-full">
      <SidebarProfile />
      <div className="w-[85%] h-full">{children}</div>
    </div>
  );
}
