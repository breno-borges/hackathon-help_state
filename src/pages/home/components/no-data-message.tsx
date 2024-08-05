import { ReactNode } from "react";
import NoDataSvg from "../../../assets/no-data";
interface NoDataMessageProps {
  message: string;
  children?: ReactNode;
}
export function NoDataMessage({ message, children }: NoDataMessageProps) {
  return (
    <div className="flex flex-col gap-4 items-center justify-center">
      <NoDataSvg className="" />
      <p>{message}</p>
      {children}
    </div>
  );
}
