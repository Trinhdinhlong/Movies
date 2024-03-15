import avaBlank from "@/public/avaBlank.png";
import Image from "next/image";

export default function SidebarProfile() {
  return (
    <div className="text-black w-[16%] grow flex-1">
      <div className="flex flex-col h-screen bg-white left-0 top-0 overflow-x-hidden">
        <div id="test" className="flex flex-col items-center gap-5 py-[10px]">
            <Image src={avaBlank} alt="" className="pt-4"/>
            <span className="font-[700] block">julientlam</span>
        </div>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black block">
          Account information
        </span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black block">
          History
        </span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black block">
          Booked ticket
        </span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black border-b-[1px] border-b-black block">
          Canceled ticket
        </span>
      </div>
    </div>
  );
}
