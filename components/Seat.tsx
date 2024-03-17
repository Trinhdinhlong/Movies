export default function Seat(props: any) {
  const seatVip = "#3C99EA";
  const seatCanChoose = "#BEC8Cf"
  const seatSold = "#F30E0E"
  const seatSelecting = "#18C53E"

  return (
    <div
      className={`w-[3rem] h-[2.5rem] text-[#655B5B] bg-[${props.seatType === 'VIP' ? seatVip : seatCanChoose}] bg-[${props.seatSold ? seatSold : ""}] flex flex-row 
        items-center justify-center rounded-[10px]
        pt-[0.4rem] pl-[0.5rem] pb-[0.588rem] pr-[0.353rem]
        cursor-pointer`}
    >
      <span className={`bg-[#EFF0F3] w-[2rem] h-[1.875rem] text-center`}>{props.seatColumn + props.seatRow}</span>
    </div>
  );
}
