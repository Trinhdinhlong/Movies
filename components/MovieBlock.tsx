import Image from "next/image";
import defaultAva from "@/public/defaultAva.jpg";

export default function MovieBlock(props: any) {
  return (
    <div className="flex flex-col">
      {/* Ensure Tailwind is correctly configured to handle arbitrary values like bg-[#F46E6E] */}
      <div className="bg-[#F46E6E]">
        {/* This div will have the orange-red background color */}
        <Image src={defaultAva} alt="" width={200} height={120} />
      </div>
      <div className="bg-[#F46E6E] p-2"> {/* Added padding for spacing */}
        {/* This div will also have the orange-red background color */}
        <span className="text-center block">View Schedule</span> {/* Ensure text-center works by making span a block element */}
      </div>
    </div>
  );
}
