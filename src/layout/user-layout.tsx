import { Link, Outlet, useNavigate } from "react-router-dom";
import LogoNavbar from "../assets/logo-navbar";
import { CustomLink } from "../components/link";
import { useContext, useEffect } from "react";
import { AuthContext } from "../context/auth-context";
export function UserLayout() {
  const navigate = useNavigate();
  const { isAuthenticated, isAdmin, signOut } = useContext(AuthContext);

  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/login");
    } else if (isAdmin) {
      navigate("/admin");
    }
  }, [isAuthenticated, isAdmin, navigate]);

  if (!isAuthenticated || !isAdmin) {
    return null;
  }
  const handleLogout = () => {
    signOut();
    return navigate("/login");
  };
  return (
    <main className="pb-10 h-screen w-screen">
      <nav className="flex justify-between px-10 py-5 bg-emerald-500 border-b border-emerald-400 text-gray-50">
        <Link to={"/"}>
          <LogoNavbar />
        </Link>
        <div className="space-x-5 font-bold">
          <CustomLink to="/" title="Inicio" />
          <CustomLink to="/occurrences" title="Minhas Ocorrencias" />
          <CustomLink to="/profile" title="Perfil" />
          <button onClick={handleLogout}>Sair</button>
        </div>
      </nav>
      <Outlet />
    </main>
  );
}
