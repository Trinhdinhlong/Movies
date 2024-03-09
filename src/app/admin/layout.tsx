export default function RegisterLayout({
    children,
  }: Readonly<{
    children: React.ReactNode;
  }>) {
    return (
      //Searchbar Component
      <div className="h-screen">
        {children}
      </div>
    );
  }
  