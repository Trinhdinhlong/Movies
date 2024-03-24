export default function AdminConfirmation(props: any) {
  return (
    <div className="w-full flex-flex-col">
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Member ID:
        </span>
        <span className="block grow w-1/2 text-[0.9rem]">
          {props.memberID}
        </span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Full name:
        </span>
        <span className="block grow w-1/2 text-[0.9rem]">
          {props.fullName}
        </span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px] border-b-[1px] border-solid border-black">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Identity card:
        </span>
        <span className="block grow w-1/2 text-[0.9rem]">{props.identityCard}</span>
      </div>
      <div className="flex flex-row w-full px-[15px] py-[7px]">
        <span className="block grow text-[1.125rem] font-[500] w-1/2">
          Phone number:
        </span>
        <span className="block grow w-1/2 text-[0.9rem]">{props.phone}</span>
      </div>
    </div>
  );
}
