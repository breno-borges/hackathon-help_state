import { ComponentProps, ReactNode } from "react";
import { NavLink } from "react-router-dom";

interface CustomLinkProps extends ComponentProps<"a"> {
  to: string;
  title: string;
  children?: ReactNode;
}
export function CustomLink({ title, to, ...props }: CustomLinkProps) {
  return (
    <NavLink
      to={to}
      className={({ isActive }) =>
        isActive ? "bg-emerald-600 px-4 py-2 rounded-lg" : ""
      }
      {...props}
    >
      {title}
    </NavLink>
  );
}
