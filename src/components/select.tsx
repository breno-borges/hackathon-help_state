import * as Select from "@radix-ui/react-select";
import { ChevronDownIcon } from "lucide-react";
import { ReactNode } from "react";

interface RadixSelectProps extends Select.SelectProps {
  children: ReactNode;
  placeholder: string;
}
export function RadixSelect({
  children,
  placeholder,
  ...props
}: RadixSelectProps) {
  return (
    <Select.Root {...props}>
      <Select.Trigger className="inline-flex items-center disabled:opacity-50 bg-gray-50 border border-stone-800 hover:border-stone-200 justify-between rounded py-6 px-4 text-md leading-none h-[35px]  shadow-[0_2px_10px] shadow-black/10  outline-none">
        <Select.Value placeholder={placeholder} className="text-stone-600" />
        <Select.Icon className="text-stone-600">
          <ChevronDownIcon />
        </Select.Icon>
      </Select.Trigger>
      <Select.Portal>
        <Select.Content
          position="popper"
          side="bottom"
          className="w-[--radix-select-trigger-width] max-h-32 bg-gray-50 rounded-sm shadow-[0px_10px_38px_-10px_rgba(22,_23,_24,_0.35),0px_10px_20px_-15px_rgba(22,_23,_24,_0.2)]"
        >
          <Select.Viewport className="px-4  text-stone-950">
            <Select.Group className="space-y-2">{children}</Select.Group>
          </Select.Viewport>
        </Select.Content>
      </Select.Portal>
    </Select.Root>
  );
}
