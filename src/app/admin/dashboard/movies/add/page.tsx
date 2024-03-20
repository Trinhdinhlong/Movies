"use client";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function Home() {
  const router = useRouter()
  const [movNameEn, setMovNameEn] = useState("");
  const [movNameVie, setMovNameVie] = useState("");
  const [fromDate, setFromDate] = useState("");
  const [toDate, setToDate] = useState("");
  const [actor, setActor] = useState("");
  const [movProductionComp, setMovProductionComp] = useState("");
  const [director, setDirector] = useState("");
  const [duration, setDuration] = useState("");
  const [movType, setMovType] = useState<String[]>([]);
  const [room, setRoom] = useState("");
  const [schedule, setSchedule] = useState<String[]>([]);
  const [content, setContent] = useState("");
  const [fileName, setFileName] = useState<File>();

  function checkFormFilled() {
    const allStringsFilled = movNameEn && movNameVie && fromDate && toDate && actor && 
                             movProductionComp && director && duration && 
                             room && content;
    const allArraysFilled = movType.length > 0 && schedule.length > 0;
    const fileSelected = fileName !== undefined;
    return allStringsFilled && allArraysFilled && fileSelected;
  }

  const handleFileChange = (e: any) => {
    setFileName(e.target.files[0]);
  };

  function handleCheckBoxType(e: any) {
    const value = e.target.value;
    if (e.target.checked) {
      setMovType((prev) => [...prev, value]);
    } else {
      setMovType((prev) => prev.filter((type) => type !== value));
    }
  }

  function handleCheckBoxSchedule(e: any) {
    const value = e.target.value;
    if (e.target.checked) {
      setSchedule((prev) => [...prev, value]);
    } else {
      setMovType((prev) => prev.filter((type) => type !== value));
    }
  }

  function handleAddMovie(e: any) {
    e.preventDefault()
    if(checkFormFilled()) {
      console.log("true")
      axios.post("http://localhost:8080/api/movie", {
        "content": content,
        "movieNameEnglish": movNameEn,
        "movieNameVN": movNameVie,
        "actor": actor,
        "director": director,
        "duration": Number(duration),
        "movieProductionCompany": movProductionComp,
        "startedDate": fromDate,
        "endDate": toDate,
        "imageURL": fileName?.name
      }).then(response => {
        console.log(response.data)
        router.push("/admin/dashboard/movies")
      }).catch(error => console.log(error))
    }
  }

  return (
    <div className="bg-[#EFF0F3] w-full h-full overflow-auto flex flex-col items-center text-black overflow-auto">
      <form className="w-[95%] bg-white m-10 p-10 flex flex-col gap-3" onSubmit={(e) => handleAddMovie(e)}>
        <label
          htmlFor="movie_name"
          className="block text-sm font-medium text-gray-700"
        >
          Movie name (ENG):
          <span className="text-red-500">*</span>
        </label>
        <input
          id="movie_name"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setMovNameEn(e.target.value)}
        />
        <label
          htmlFor="movie_name_VN"
          className="block text-sm font-medium text-gray-700"
        >
          Movie name (VN):
          <span className="text-red-500">*</span>
        </label>
        <input
          id="movie_name_VN"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setMovNameVie(e.target.value)}
        />
        <label
          htmlFor="from_date"
          className="block text-sm font-medium text-gray-700"
        >
          From date:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="from_date"
          type="date"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setFromDate(e.target.value)}
        />
        <label
          htmlFor="to_date"
          className="block text-sm font-medium text-gray-700"
        >
          To date:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="to_date"
          type="date"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setToDate(e.target.value)}
        />
        <label
          htmlFor="actor"
          className="block text-sm font-medium text-gray-700"
        >
          Actor:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="actor"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setActor(e.target.value)}
        />
        <label
          htmlFor="movie_production_company"
          className="block text-sm font-medium text-gray-700"
        >
          Movie Production Company:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="movie_production_company"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setMovProductionComp(e.target.value)}
        />
        <label
          htmlFor="director"
          className="block text-sm font-medium text-gray-700"
        >
          Director:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="director"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setDirector(e.target.value)}
        />
        <label
          htmlFor="duration"
          className="block text-sm font-medium text-gray-700"
        >
          Duration:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="duration"
          type="text"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] p-2"
          onChange={(e) => setDuration(e.target.value)}
        />
        <label
          htmlFor="version"
          className="block text-sm font-medium text-gray-700 mt-2"
        >
          Type:
          <span className="text-red-500">*</span>
        </label>
        <div className="text-black w-[60%] gap-7 flex flex-row flex-wrap mt-6">
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="hd"
              type="checkbox"
              value="Hành động"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="hd">Hành động</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="hh"
              type="checkbox"
              value="Hài hước"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="hh">Hài hước</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="lm"
              type="checkbox"
              value="Lãng mạn"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="lm">Lãng mạn</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="tc"
              type="checkbox"
              value="Tình cảm"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="tc">Tình cảm</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="ct"
              type="checkbox"
              value="Chiến tranh"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="ct">Chiến tranh</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="kh"
              type="checkbox"
              value="Kiếm hiệp"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="kh">Kiếm hiệp</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="an"
              type="checkbox"
              value="Âm nhạc"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="an">Âm nhạc</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="kd"
              type="checkbox"
              value="Kinh dị"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="kd">Kinh dị</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="pl"
              type="checkbox"
              value="Phiêu lưu"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="pl">Phiêu lưu</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="tl"
              type="checkbox"
              value="Tâm lý 18+"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="tl">Tâm lý 18+</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="hh2"
              type="checkbox"
              value="Hoạt hình"
              onChange={(e) => handleCheckBoxType(e)}
            />
            <label htmlFor="hh2">Hoạt hình</label>
          </div>
        </div>
        <label
          htmlFor="sched"
          className="block text-sm font-medium text-gray-700"
        >
          Cinema room:
          <span className="text-red-500">*</span>
        </label>
        <select
          id="sched"
          className="text-black h-10 rounded-[5px] border-[1px] border-black border-solid p-2"
          onChange={(e) => setRoom(e.target.value)}
        >
          <option value="r1">Cinema room 1</option>
          <option value="r2">Cinema room 2</option>
        </select>
        <label
          htmlFor="version"
          className="block text-sm font-medium text-gray-700 mt-2"
        >
          Schedule:
          <span className="text-red-500">*</span>
        </label>
        <div className="text-black w-[60%] gap-7 flex flex-row flex-wrap mt-5">
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="hd"
              type="checkbox"
              value="8:00"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="hd">8:00</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="hh"
              type="checkbox"
              value="9:00"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="hh">9:00</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="lm"
              type="checkbox"
              value="10:00"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="lm">10:00</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="tc"
              type="checkbox"
              value="11:00"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="tc">11:00</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="ct"
              type="checkbox"
              value="13:30"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="ct">13:30</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="kh"
              type="checkbox"
              value="14:30"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="kh">14:30</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="an"
              type="checkbox"
              value="15:30"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="an">15:30</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="kd"
              type="checkbox"
              value="17:30"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="kd">17:30</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="pl"
              type="checkbox"
              value="18:30"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="pl">18:30</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="tl"
              type="checkbox"
              value="19:00"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="tl">19:00</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="hh2"
              type="checkbox"
              value="20:00"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="hh2">20:00</label>
          </div>
          <div className="w-[calc(30%)] flex flex-row items-center gap-2">
            <input
              id="hh3"
              type="checkbox"
              value="21:00"
              onChange={(e) => handleCheckBoxSchedule(e)}
            />
            <label htmlFor="hh3">21:00</label>
          </div>
        </div>
        <label
          htmlFor="content"
          className="block text-sm font-medium text-gray-700"
        >
          Content:
          <span className="text-red-500">*</span>
        </label>
        <input
          id="content"
          type="textarea"
          className="border-solid border-[1px] border-[#BEC8CF] rounded-[5px] h-10 p-2"
          onChange={(e) => setContent(e.target.value)}
        />
        <div>
          <label
            htmlFor="file-upload"
            className="block text-sm font-medium text-gray-700 mr-2 mb-3"
          >
            Image:
            <span className="text-red-500">*</span>
          </label>
          <div className="border-[1px] border-solid border-black rounded-[5px] p-1">
            <button
              type="button"
              onClick={() => document.getElementById("file-upload")?.click()}
              className="bg-gray-200 rounded p-1 text-sm mr-4 text-black" // Added mr-4 for margin to the right of the button
            >
              Choose File
            </button>
            <input
              id="file-upload"
              type="file"
              className="hidden"
              onChange={handleFileChange}
            />
            {fileName && (
              <span className="text-sm text-black">{fileName.name}</span>
            )}
          </div>
        </div>
        <div className="flex flex-row items-center gap-4 mt-5">
          <button type="submit" className="p-2 bg-[#337AB7] w-[5rem] rounded-[5px] text-white">Save</button>
          <button type="reset" className="p-2 bg-[#337AB7] w-[5rem] rounded-[5px] text-white">Close</button>
        </div>
      </form>
    </div>
  );
}
