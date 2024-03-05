import "../globals.css";

export default function RegisterLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="w-full h-full bg-[#EFF0F3]">{children}</div>
  );
}
