export default function MovieConfirmation(props: any) {
  return (
    <div className="w-full flex-flex-col">
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="grow block w-1/2 text-[1.125rem] font-[500]">
          Screen:
        </span>
        <span className="grow block w-1/2 text-[0.9rem]">scrn02</span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Date:
        </span>
        <span className="block grow w-1/2 text-[0.9rem]">02/11/2022</span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Time:
        </span>
        <span className="block grow w-1/2 text-[0.9rem]">20:00</span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Seat:
        </span>
        <span className="block grow w-1/2 text-[0.9rem]"></span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Price:
        </span>
        <div className="grow flex flex col w-1/2 text-[0.9rem]">
          <span className="block mr-3">5B: 120</span>
          <span className="block mr-3">6C: 140</span>
        </div>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px]">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Total:
        </span>
        <span className="block grow text-[#439B57] w-1/2 text-[0.9rem]">
          190.000d
        </span>
      </div>
    </div>
  );
}
