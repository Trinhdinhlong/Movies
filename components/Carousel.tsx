import { useEffect, useState } from "react";

export default function Carousel({
  children: slides,
  autoSlide,
  autoSlideInterval,
}: any) {
  const [curr, setCurr] = useState(0);

  const prev = () =>
    setCurr((curr) => (curr === 0 ? slides.length - 1 : curr - 1));
  const next = () =>
    setCurr((curr) => (curr === slides.length - 1 ? 0 : curr + 1));

  useEffect(() => {
    if (!autoSlide) return;
    const slideInterval = setInterval(next, autoSlideInterval);
    return () => clearInterval(slideInterval);
  }, []);
  return (
    <div className="relative w-full flex flex-row h-full">
      <div
        className="flex w-full h-full transition-transform ease-out duration-500"
        style={{ transform: `translateX(-${curr * 100}%)` }}
      >
        {slides}
      </div>
      <div className="absolute bottom-4 right-0 left-0">
        <div className="flex items-center justify-center gap-2">
          {slides.map((_: any, i: any) => (
            <div
              key={i}
              className={`
              transition-all w-3 h-3 bg-white rounded-full
              ${curr === i ? "p-2" : "bg-opacity-50"}
            `}
            />
          ))}
        </div>
      </div>
    </div>
  );
}
