import minimize from "@/public/minimize.svg";
import Image from "next/image";

export default function Sidebar() {
  return (
    <div className="text-black w-[16%]">
      <div className="z-[1] flex flex-col h-screen bg-white left-0 top-0 overflow-x-hidden">
        <div className="flex flex-row justify-end px-5 py-3">
          <Image src={minimize} alt="" />
        </div>
        <span className="px-5 py-3">Employees</span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black">
          Movies
        </span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black">
          Cinema Room
        </span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black">
          Booking List
        </span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black border-b-[1px] border-b-black">
          Promotion
        </span>
      </div>
    </div>
  );
}
