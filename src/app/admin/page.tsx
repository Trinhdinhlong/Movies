z"use client"

import { useRouter } from "next/navigation";
import { useEffect } from "react";

export default function Home() {
  const router = useRouter();
  useEffect(() => {
    router.push("/admin/dashboard");
  }, [router])

  return (
    null
  );
}
