import * as Select from "@radix-ui/react-select";
import { CheckIcon } from "lucide-react";
import { ReactNode } from "react";

interface SelectItemProps {
  value: string;
  children: ReactNode;
}
export function SelectItem({ value, children }: SelectItemProps) {
  return (
    <Select.Item
      value={value}
      className="w-[--radix-select-trigger-width] py-2 px-5 flex items-center data-[highlighted]:cursor-pointer data-[highlighted]:bg-gray-200 data-[highlighted]:text-stone-950 rounded-[3px] outline-none "
    >
      <Select.ItemIndicator className="absolute right-3 inline-flex items-center justify-center">
        <CheckIcon className="text-green-300 w-4 h-4 data-[highlighted]:text-stone-900" />
      </Select.ItemIndicator>
      <Select.ItemText>{children}</Select.ItemText>
    </Select.Item>
  );
}
