interface Room {
  id: number;
  nameRoom: string;
  seatQuantity: number;
}

interface SeatObj {
  id: number;
  price: number;
  seatColumn: string;
  seatRow: number;
}

export default function MovieConfirmationSingleTicket({
  rowName,
  colName,
  price,
  roomName,
  showTime,
  date,
}: {
  rowName: number;
  colName: string;
  price: number | undefined;
  roomName: string | undefined;
  showTime: string | undefined;
  date: string | undefined;
}) {
  return (
    <div className="w-full flex-flex-col">
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="grow block w-1/2 text-[1.125rem] font-[500]">
          Screen:
        </span>
        <span className="grow block w-1/2 text-[0.9rem]">{roomName}</span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Date:
        </span>
        <span className="block grow w-1/2 text-[0.9rem]">{date}</span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Time:
        </span>
        <span className="block grow w-1/2 text-[0.9rem]">{showTime}</span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Seat:
        </span>
        <span className="block grow w-1/2 text-[0.9rem] flex flex-row gap-1">
          <span>{colName + rowName}</span>
        </span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Price:
        </span>
        <div className="grow flex flex-col w-1/2 text-[0.9rem]">
          <div className="flex flex-col">
            <span className="block mr-3">
              {colName + rowName}: {price}
            </span>
          </div>
        </div>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px]">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Total:
        </span>
        <span className="block grow text-[#439B57] w-1/2 text-[0.9rem]">
          {price}
        </span>
      </div>
    </div>
  );
}
