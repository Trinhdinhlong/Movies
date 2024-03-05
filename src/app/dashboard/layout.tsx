import Header from "../../../components/Header";
import "../globals.css";

export default function RegisterLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    //Searchbar Component
    <div className="h-screen">
      <Header />
      {children}
    </div>
  );
}
