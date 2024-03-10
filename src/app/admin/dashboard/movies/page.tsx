"use client"
import { useRouter } from "next/navigation";
import { useEffect } from "react";

export default function ListMovie() {
    const router = useRouter()
    useEffect(() => {
        router.push("/admin/dashboard/movies/add")
    }, [])
    return null;
}