import Image from "next/image";
import defaultMovieAva from "@/public/defaultAva.jpg";
import Link from "next/link";

export default function MovieShowTime(props: any) {
  const movieNameEn = props.movieEn;
  const movieId = props.movieId;
  const roomId = props.room;
  const defaultMovieStringVie = "Oppenheimer";
  const defaultMovieStringEn = "Coconut The Dragon";
  const now = new Date();
  const day = now.getDate();
  const month = now.getMonth() + 1;
  const year = now.getFullYear();
  const formattedDate = `${day}-${month}-${year}`;

  return (
    <div className="flex flex-row py-[5px] gap-10 border-b-[1px]">
      <div className="w-[15rem] h-[15rem]">
        <img
          src={
            process.env.NEXT_PUBLIC_API_BASE_URL + "/images/" + props.imageURL
          }
          alt=""
          className="w-full h-full"
        />
      </div>
      <div className="flex flex-col gap-2 w-full">
        <span className="text-[#337AB7] text-[1.5rem]">
          {props.movieVie || defaultMovieStringVie}
        </span>
        <span>{props.movieEn || defaultMovieStringEn}</span>
        <div className="flex flex-row p-[10px] gap-5">
          {props.showtime.map((el: any) => (
            <Link
              key={el.id}
              href={`/${
                localStorage.getItem("role") !== null
                  ? localStorage.getItem("role")?.toLowerCase()
                  : "user"
              }/dashboard/showtime/seats?movieId=${movieId}&showTimeId=${
                el.id
              }&roomId=${roomId}&showTime=${el.startTime.slice(
                0,
                -3
              )}&date=${formattedDate}&movieName=${movieNameEn}`}
              className="bg-[#E0E5E8] py-[5px] px-[18px] rounded-[5px] cursor-pointer"
            >
              <span className="font-[600]">{el.startTime.slice(0, -3)}</span>
            </Link>
          ))}
        </div>
      </div>
    </div>
  );
}
