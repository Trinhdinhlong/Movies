"use client";

import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

interface TypeMovie {
  id: number;
  typeName: string;
  createdDate: string | null; // Assuming this can be null based on the provided JSON
  updatedTime: string | null;
}

interface ShowTime {
  id: number;
  startTime: string;
  endTime: string;
}

interface Movie {
  id: number;
  movieNameEnglish: string;
  movieNameVN: string;
  actor: string;
  duration: number;
  movieProductionCompany: string;
  startedDate: string;
  version: string;
  imageURL: string;
  typeMovies: TypeMovie[];
  showTimes: ShowTime[];
}

export default function ListMovie() {
  const [listMovieType, setMovieType] = useState<Movie[]>([]);
  const router = useRouter()
  useEffect(() => {
    axios
      .get("https://9817-14-232-224-226.ngrok-free.app/api/movies/admin", {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      })
      .then((response) => {
        setMovieType(response.data);
      })
      .catch((error) => console.error(error));
  }, []);

  function handleDelete(id: any) {
    axios
      .delete(`https://9817-14-232-224-226.ngrok-free.app/api/movies/${id}`, {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      })
      .then((response) => {
        location.reload()
      })
  }

  function handleCreate() {
    router.push("/admin/dashboard/movies/add")
  }

  function handleUpdate(id: any) {
    router.push(`/admin/dashboard/movies/edit?id=${id}`)
  }

  return (
    <div className="bg-[#EFF0F3] w-full h-full flex flex-col items-center overflow-auto">
      <div className="w-[96%] bg-white mt-8 mb-8">
        <div className="flex justify-between items-center p-4 bg-white">
          <button className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none"
          onClick={handleCreate}>
            Add new
          </button>
          <div className="flex space-x-2">
            <input
              className="px-4 py-2 border rounded-md"
              placeholder="Search"
            />
            <button className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none">
              Search
            </button>
          </div>
        </div>
        <div className="overflow-x-auto relative shadow-md sm:rounded-lg">
          <table className="w-full text-sm text-left text-gray-500">
            <thead className="text-xs text-gray-700 uppercase bg-gray-50">
              <tr>
                <th scope="col" className="py-3 px-6">
                  #
                </th>
                <th scope="col" className="py-3 px-6">
                  Movie (ENG)
                </th>
                <th scope="col" className="py-3 px-6">
                  Movie (VN)
                </th>
                <th scope="col" className="py-3 px-6">
                  Released date
                </th>
                <th scope="col" className="py-3 px-6">
                  Movie Production Company
                </th>
                <th scope="col" className="py-3 px-6">
                  Duration
                </th>
                <th scope="col" className="py-3 px-6">
                  Version
                </th>
                <th scope="col" className="py-3 px-6">
                  Detail
                </th>
                <th scope="col" className="py-3 px-6">
                  Delete
                </th>
              </tr>
            </thead>
            <tbody>
              {listMovieType.map((el, index) => (
                <tr className="bg-white border-b">
                  <td className="py-4 px-6">{index+1}</td>
                  <td className="py-4 px-6">{el.movieNameEnglish}</td>
                  <td className="py-4 px-6">{el.movieNameVN}</td>
                  <td className="py-4 px-6">{el.startedDate}</td>
                  <td className="py-4 px-6">{el.movieProductionCompany}</td>
                  <td className="py-4 px-6">{el.duration}</td>
                  <td className="py-4 px-6">{el.version}</td>
                  <td className="py-4 px-6">
                    <button className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none"
                    onClick={() => handleUpdate(el.id)}>
                      Detail
                    </button>
                  </td>
                  <td className="py-4 px-6">
                    <button className="px-4 py-2 bg-red-500 text-white rounded-md hover:bg-red-600 focus:outline-none"
                    onClick={() => handleDelete(el.id)}>
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className="flex justify-between items-center p-4 bg-white">
          <span className="text-sm text-gray-700">
            Showing 1 to 4 of 4 entries
          </span>
          <div className="flex space-x-1">
            <button className="px-3 py-1 border rounded-md hover:bg-gray-200 focus:outline-none">
              Previous
            </button>
            <button className="px-3 py-1 border rounded-md hover:bg-gray-200 focus:outline-none">
              1
            </button>
            <button className="px-3 py-1 border rounded-md hover:bg-gray-200 focus:outline-none">
              Next
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
