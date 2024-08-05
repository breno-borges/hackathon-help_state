import { ComponentProps } from "react";

interface InputProps extends ComponentProps<"input"> {
  title: string;
  htmlFor: string;
}
export function Input({ title, htmlFor, ...props }: InputProps) {
  return (
    <div className="flex flex-col flex-1 gap-1">
      <label className="font-bold">{title}</label>
      <input
        {...props}
        className="border border-gray-400 px-3 py-4 text-gray-950 placeholder:text-gray-950"
      />
    </div>
  );
}
