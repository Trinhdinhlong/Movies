"use client"
import MovieShowTime from "components/MovieShowTime";

export default function Home() {
    return (
        <div className="w-full bg-[#EFF0F3] text-black flex flex-col items-center h-full overflow-auto">
            <div className="w-[70%] flex flex-col items-center gap-10 mb-10">
                <span className="w-full text-center mt-10 font-[700] text-[1.5rem]">SHOWTIMES</span>
                <div className="flex flex-col bg-white gap-2 w-full py-10 px-10">
                    <MovieShowTime/>
                    <MovieShowTime/>
                    <MovieShowTime/>
                </div>
            </div>
        </div>
    )
}