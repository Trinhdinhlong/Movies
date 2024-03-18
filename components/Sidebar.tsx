import minimize from "@/public/minimize.svg";
import Image from "next/image";
import { useRouter } from "next/navigation";

export default function Sidebar() {
  const router = useRouter()

  function handleEmployees() {
    router.push("/admin/dashboard/employees")
  }

  function handleMovies() {
    router.push("/admin/dashboard/movies")
  }

  return (
    <div className="text-black w-[15%]">
      <div className="z-[1] flex flex-col h-screen bg-white left-0 top-0 overflow-x-hidden">
        <div className="flex flex-row justify-end px-5 py-3">
          <Image src={minimize} alt="" />
        </div>
        <span className="px-5 py-3 cursor-pointer" onClick={handleEmployees}>Employees</span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black cursor-pointer"
        onClick={handleMovies}>
          Movies
        </span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black cursor-pointer">
          Cinema Room
        </span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black cursor-pointer">
          Booking List
        </span>
        <span className="px-5 py-3 border-t-[1px] border-solid border-t-black border-b-[1px] border-b-black cursor-pointer">
          Promotion
        </span>
      </div>
    </div>
  );
}
