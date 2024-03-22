import Image from "next/image";
import defaultAva from "@/public/defaultAva.jpg";
import { useRouter } from "next/navigation";

export default function MovieBlock(props: any) {
  const router = useRouter();

  function handleShowtime() {
    router.push("/user/dashboard/showtime");
  }

  return (
    <div className="flex flex-col">
      <div className="bg-[#F46E6E]">
        <Image
          src={defaultAva || props.imageURL}
          alt=""
          width={200}
          height={120}
        />
      </div>
      <div className="bg-[#F46E6E] p-2 cursor-pointer hover:bg-red-500"
      onClick={handleShowtime}>
        <span className="text-center block font-[500]">View Schedule</span>
      </div>
    </div>
  );
}
