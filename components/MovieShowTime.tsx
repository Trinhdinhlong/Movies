import Image from "next/image";
import defaultMovieAva from "@/public/defaultAva.jpg";

export default function MovieShowTime(props: any) {
  const defaultMovieStringVie = "Oppenheimer";
  const defaultMovieStringEn = "Coconut The Dragon";

  return (
    <div className="flex flex-row py-[5px] gap-10 border-b-[1px]">
      <div className="">
        <Image src={defaultMovieAva} alt="" width={200} />
      </div>
      <div className="flex flex-col gap-2 w-full">
        <span className="text-[#337AB7] text-[1.5rem]">
          {props.movieVie ? props.movieVie : defaultMovieStringVie}
        </span>
        <span>{props.movieEn ? props.movieEn : defaultMovieStringEn}</span>
        <div className="flex flex-row p-[10px] gap-5">
          <div className="bg-[#E0E5E8] py-[5px] px-[18px] rounded-[5px] cursor-pointer">
            <span className="font-[600]">8:00</span>
          </div>
          <div className="bg-[#E0E5E8] py-[5px] px-[18px] rounded-[5px] cursor-pointer">
            <span className="font-[600]">8:00</span>
          </div>
          <div className="bg-[#E0E5E8] py-[5px] px-[18px] rounded-[5px] cursor-pointer">
            <span className="font-[600]">8:00</span>
          </div>
        </div>
      </div>
    </div>
  );
}
