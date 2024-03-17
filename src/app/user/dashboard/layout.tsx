import Header from "components/Header";

export default function RegisterLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <Header/>
    <div className="h-screen">
      {children}
    </div>
  );
}
