import SidebarProfile from "components/SidebarProfile";

export default function EditLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <div className="flex flex-1 flex-row">
      <SidebarProfile />
      <div className="w-[80%] h-full">{children}</div>
    </div>
  );
}
