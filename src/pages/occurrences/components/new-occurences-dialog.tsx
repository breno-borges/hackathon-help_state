import * as Dialog from "@radix-ui/react-dialog";
import { ChevronRight, Plus, X } from "lucide-react";
import { useState } from "react";
import { NewOccurencesForm } from "./new-occurences-form";
export function NewOccurencesDialog() {
  const [open, setOpen] = useState<boolean>(false);
  return (
    <Dialog.Root open={open} onOpenChange={setOpen}>
      <Dialog.Trigger asChild>
        <button className=" bg-emerald-500 border-emerald-400 rounded-md px-4 py-2 flex gap-2 items-center text-gray-50 font-bold hover:bg-emerald-400">
          <Plus className="w-5 h-5" />
          <span>Criar</span>
        </button>
      </Dialog.Trigger>
      <Dialog.Portal>
        <Dialog.Overlay className="bg-stone-900 data-[state=open]:animate-overlayShow fixed inset-0 opacity-80" />
        <Dialog.Content className="data-[state=open]:animate-contentShow overflow-y-scroll bg-emerald-50 fixed top-[50%] left-[50%] max-h-[85vh] w-[90vw] max-w-[450px] translate-x-[-50%] translate-y-[-50%] rounded-[6px] bg-radix-gray-2 p-[25px] space-y-8 focus:outline-none">
          <div>
            <Dialog.Title className="m-0 text-[24px] font-bold">
              Adicionar ocorrência
            </Dialog.Title>
          </div>
          <NewOccurencesForm onClose={() => setOpen(false)} />
          <Dialog.Close asChild>
            <button
              className="hover:bg-stone-200 absolute top-[-5px] right-[10px] inline-flex h-[25px] w-[25px] appearance-none items-center justify-center rounded-full focus:shadow-[0_0_0_2px] focus:outline-none"
              aria-label="Close"
            >
              <X />
            </button>
          </Dialog.Close>
        </Dialog.Content>
      </Dialog.Portal>
    </Dialog.Root>
  );
}
