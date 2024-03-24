import minimize from "@/public/minimize.svg";
import Image from "next/image";
import { useRouter } from "next/navigation";

export default function Sidebar() {
  const router = useRouter();

  function handleEmployees() {
    router.push("/admin/dashboard/employees");
  }

  function handleMovies() {
    router.push("/admin/dashboard/movies");
  }

  function handleBookingList() {
    router.push("/admin/dashboard/booking_list")
  }

  function handleCinemaRoom() {
    router.push("/admin/dashboard/cinema_room")
  }

  return (
    <aside className="w-1/6 bg-white min-h-screen shadow-lg">
      <div className="flex flex-col">
        <div className="py-6 px-4 text-gray-600">
          <button className="text-xl hover:text-gray-800">
            {/* Icon or text can go here */}
            {'<'}
          </button>
        </div>
        <nav className="flex flex-col mt-4">
          {/* Replace 'a' with NavLink if using react-router-dom */}
          <span className="py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out"
          onClick={handleEmployees}>
            Employees
          </span>
          <span className="py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out"
          onClick={handleMovies}>
            Movies
          </span>
          <span className="py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out"
          onClick={handleCinemaRoom}>
            Cinema Room
          </span>
          <span className="py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out"
          onClick={handleBookingList}>
            Booking List
          </span>
          <span className="py-4 text-base font-medium text-gray-700 hover:bg-gray-100 hover:text-gray-900 px-6 transition duration-300 ease-in-out">
            Promotion
          </span>
        </nav>
      </div>
    </aside>
  );
}
