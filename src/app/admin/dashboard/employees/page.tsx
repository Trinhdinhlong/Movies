"use client";
import Image from "next/image";
import minimize from "@/public/minimize.svg";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import axios from "axios";

interface Role {
  id: number;
  roleName: string;
  createdDate: string;
  updatedTime: string;
}

interface Employee {
  userId: number;
  username: string;
  password: string;
  fullName: string;
  dateOfBirth: string;
  gender: "MALE" | "FEMALE"; // Assuming these are the only two options
  email: string;
  address: string;
  phone: string;
  identityCard: string;
  imageURL: string;
  role: Role;
}

export default function Home(props: any) {
  const router = useRouter();
  const [employees, setEmployees] = useState<Employee[]>();
  useEffect(() => {
    axios
      .get("https://9817-14-232-224-226.ngrok-free.app/api/employee", {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      })
      .then((response) => {
        console.log(response.data);
        setEmployees(response.data);
      });
  }, []);

  function handleAddEmployee() {
    router.push("/admin/dashboard/employees/create");
  }

  function handleDelete(username: any) {
    axios
      .delete(`https://9817-14-232-224-226.ngrok-free.app/api/employee/${username}`, {
        headers: {
          "ngrok-skip-browser-warning": "skip-browser-warning",
        },
      })
      .then((response) => {
        location.reload()
      });
  }

  function handleEdit(username: any) {
    router.push(`/admin/dashboard/employees/edit?username=${username}`)
  }

  return (
    <div className="w-full">
      <div className="flex flex-row text-black w-full">
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
              <button
                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                onClick={handleAddEmployee}
              >
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
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Identity card
                    </th>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Phone number
                    </th>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Address
                    </th>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Edit
                    </th>
                    <th className="text-left py-3 px-4 uppercase font-semibold text-sm">
                      Delete
                    </th>
                  </tr>
                </thead>
                <tbody className="text-gray-700">
                  {employees?.map((e, index) => (
                    <tr>
                      <td className="text-left py-3 px-4">{index+1}</td>
                      <td className="text-left py-3 px-4">{e.username}</td>
                      <td className="text-left py-3 px-4">{e.fullName}</td>
                      <td className="text-left py-3 px-4">{e.dateOfBirth}</td>
                      <td className="text-left py-3 px-4">{e.gender}</td>
                      <td className="text-left py-3 px-4">{e.email}</td>
                      <td className="text-left py-3 px-4">{e.identityCard}</td>
                      <td className="text-left py-3 px-4">{e.phone}</td>
                      <td className="text-left py-3 px-4">{e.address}</td>
                      <td className="text-left py-3 px-4 text-green-500 cursor-pointer font-bold"
                      onClick={() => handleEdit(e.username)}>EDIT</td>
                      <td className="text-left py-3 px-4 text-red-500 cursor-pointer font-bold"
                      onClick={() => handleDelete(e.userId)}>DELETE</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
            <div className="flex justify-between items-center mt-4">
              <div className="inline-flex">
                <button className="text-sm bg-gray-200 hover:bg-gray-400 text-gray-800 font-semibold py-2 px-4 rounded-l ">
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
