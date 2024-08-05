import { createBrowserRouter } from "react-router-dom";
import { LoginPage } from "./pages/login";
import { RegisterPage } from "./pages/register";
import { HomePage } from "./pages/home/page";
import { UserLayout } from "./layout/user-layout";
import { OccurrencesPage } from "./pages/occurrences/page";
import { ProfilePage } from "./pages/profile/page";
import { AdminLayout } from "./layout/admin-layout";
import { GetReportsActive } from "./pages/admin/reports/get-active-reports";
import { AdminHomePage } from "./pages/admin/page";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <UserLayout />,
    children: [
      {
        path: "",
        element: <HomePage />,
      },
      {
        path: "/occurrences",
        element: <OccurrencesPage />,
      },
      {
        path: "/profile",
        element: <ProfilePage />,
      },
    ],
  },
  {
    path: "/admin",
    element: <AdminLayout />,
    children: [
      {
        path: "",
        element: <AdminHomePage />,
      },
      {
        path: "active-reports",
        element: <GetReportsActive />,
      },
    ],
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/register",
    element: <RegisterPage />,
  },
]);
