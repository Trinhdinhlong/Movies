"use client";
import Image from "next/image";
import minimize from "@/public/minimize.svg";

export default function Home(props : any) {
  return (
    <div>
      <div className="flex flex-row text-black w-full">
        <div className="flex flex-col bg-white w-[16%] h-screen">
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
        <div className="min-h-screen bg-gray-100 p-8 w-full">
          <div className="bg-white shadow rounded-md p-6">
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-lg font-semibold text-gray-700">
                Employee List
              </h2>
              <div className="flex items-center">
                <label htmlFor="searchBar" className="text-gray-700 mr-2">
                  Search:
                </label>
                <input
                  id="searchBar"
                  type="text"
                  className="border border-gray-300 rounded-md p-2 focus:ring-blue-500 focus:border-blue-500"
                />
              </div>
            </div>
            <div className="mb-4">
              <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
                Add new
              </button>
            </div>
            <div className="overflow-x-auto">
              <table className="min-w-full bg-white">
                <thead className="bg-gray-800 text-white">
                  <tr>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      #
                    </th>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Username
                    </th>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Full name
                    </th>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Date of birth
                    </th>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Gender
                    </th>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Email
                    </th>
                    {/* Add other headers */}
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Edit
                    </th>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Delete
                    </th>
                  </tr>
                </thead>
                <tbody className="text-gray-700">
                  <tr>
                    <td className="text-left py-3 px-4">1</td>
                    <td className="text-left py-3 px-4">BacVN2</td>
                    <td className="text-left py-3 px-4">Vuong Nhat Bac</td>
                    <td className="text-left py-3 px-4">12/12/1997</td>
                    <td className="text-left py-3 px-4">F</td>
                    <td className="text-left py-3 px-4">bacvn2@gmail.com</td>
                    {/* Add other data cells */}
                    <td className="text-left py-3 px-4">Edit</td>
                    <td className="text-left py-3 px-4">Delete</td>
                  </tr>
                  {/* Repeat rows as needed */}
                </tbody>
              </table>
            </div>
            <div className="flex justify-between items-center mt-4">
              <span className="text-sm text-gray-700">
                Showing <span className="font-semibold">1</span> to{" "}
                <span className="font-semibold">10</span> of{" "}
                <span className="font-semibold">100</span> Entries
              </span>
              <div className="inline-flex">
                <button className="text-sm bg-gray-200 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded-l">
                  Prev
                </button>
                <button className="text-sm bg-gray-200 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded-r">
                  Next
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
