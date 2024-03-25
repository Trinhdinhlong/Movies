import { useRouter } from "next/navigation";

export default function MovieBlock(props: any) {
  const router = useRouter();

  function handleClickMovie(e: any) {
    router.push(
      `/${
        localStorage.getItem("role") !== null
          ? localStorage.getItem("role")?.toLowerCase()
          : "user"
      }/dashboard/showtime?search=${e}`
    );
  }

  return (
    <div
      className="flex flex-col text-white cursor-pointer bg-black hover:bg-white hover:text-black"
      onClick={() => handleClickMovie(props.movieName)}
    >
      <div className="w-[14rem] h-[17rem]">
        <img
          src={
            process.env.NEXT_PUBLIC_API_BASE_URL + "/images/" + props.imageURL
          }
          alt=""
          className="w-full h-full"
        />
      </div>
      <div className="p-2 cursor-pointer">
        <span className="text-center block font-[500]">{props.movieName}</span>
      </div>
    </div>
  );
}
