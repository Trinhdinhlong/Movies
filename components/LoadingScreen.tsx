export default function Loading() {
  return (
    <div className="w-full h-full flex flex-row text-green-500 text-lg font-medium bg-white items-center justify-center gap-3">
      <div className="block h-5 w-5 animate-spin rounded-full border-2 border-t-transparent border-solid border-green-500"></div>
      <span className="block text-[1.5rem]">Redirecting ...</span>
    </div>
  );
}
