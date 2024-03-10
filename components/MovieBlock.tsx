import Image from "next/image";
import defaultAva from "@/public/defaultAva.jpg" 
export default function MovieBlock(props : any) {
    return (
        <div className="bg-[#F46E6E] gap-3">
            <Image src={defaultAva} alt="" className="w-[1rem] h-[1rem]"/>
            <span>{props.movieName}</span>
            <span>View Schedule</span>
        </div>
    )
}