import { Outlet, useNavigate } from "react-router-dom";
import Navbar from "../components/navbar";
import SideBar from "../components/sidebar";
import { useContext, useEffect } from "react";
import { AuthContext } from "../context/auth-context";

export function AdminLayout() {
  const navigate = useNavigate();
  const { isAuthenticated, isAdmin } = useContext(AuthContext);
  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/login");
    } else if (!isAdmin) {
      navigate("/admin/active-reports");
    }
  }, [isAuthenticated, isAdmin, navigate]);

  if (!isAuthenticated || !isAdmin) {
    return null;
  }
  return (
    <>
      <h1>admin</h1>
      <Outlet />
      <Navbar />
      <SideBar />
    </>
  );
}
