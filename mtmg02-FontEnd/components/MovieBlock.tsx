import Image from "next/image";
import defaultAva from "@/public/defaultAva.jpg";

export default function MovieBlock(props: any) {
  return (
    <div className="flex flex-col">
      <div className="bg-[#F46E6E]">
        <Image src={defaultAva || props.imageURL} alt="" width={200} height={120} />
      </div>
      <div className="bg-[#F46E6E] p-2">
        <span className="text-center block">View Schedule</span>
      </div>
    </div>
  );
}
