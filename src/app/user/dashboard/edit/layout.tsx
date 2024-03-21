"use client";

import Header from "components/Header";
import SidebarProfile from "components/SidebarProfile";
import { useState } from "react";

export default function EditLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const [open, setOpen] = useState(false);

  function handleOpenPopup() {
    setOpen(!open);
  }
  return (
    <div className="flex flex-1 flex-row w-full">
      <SidebarProfile />
      <div className="w-[85%] h-full">{children}</div>
    </div>
  );
}
