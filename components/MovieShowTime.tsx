import Image from "next/image";
import defaultMovieAva from "@/public/defaultAva.jpg";
import Link from "next/link";

export default function MovieShowTime(props: any) {
  const movieName = props.movie?.roomName;
  const movieId = props.movieId;
  const roomId = props.room?.id;
  const defaultMovieStringVie = "Oppenheimer";
  const defaultMovieStringEn = "Coconut The Dragon";

  return (
    <div className="flex flex-row py-[5px] gap-10 border-b-[1px]">
      <div className="">
        <Image src={defaultMovieAva || props.imageUrl} alt="" width={200} />
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
              href={`/user/dashboard/showtime/seats?movieId=${movieId}&showTimeId=${el.id}&roomId=${roomId}`}
              className="bg-[#E0E5E8] py-[5px] px-[18px] rounded-[5px] cursor-pointer"
            >
              <span className="font-[600]">{el.startTime.slice(0, -3)}</span>
            </Link>
          ))}
          {/* http://localhost:8080/api/movie/1/room/1/showtime/1/seats */}
        </div>
      </div>
    </div>
  );
}
