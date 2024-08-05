import { useState } from "react";
import * as Dialog from "@radix-ui/react-dialog";
import { X } from "lucide-react";
export function RemoveOccurencyValidationDialog() {
  const [open, setOpen] = useState<boolean>(false);
  const handleRemoveOccurency = () => {
    // delete method here
    alert("Removida");
    setOpen(false);
  };
  return (
    <Dialog.Root open={open} onOpenChange={setOpen}>
      <Dialog.Trigger asChild>
        <button className="bg-emerald-500 border border-emerald-400 px-6 py-2 hover:bg-emerald-400 text-gray-50 rounded-md font-bold">
          Remover
        </button>
      </Dialog.Trigger>
      <Dialog.Portal>
        <Dialog.Overlay className="bg-stone-900 data-[state=open]:animate-overlayShow fixed inset-0 opacity-80" />
        <Dialog.Content className="data-[state=open]:animate-contentShow l bg-emerald-50 fixed top-[50%] left-[50%] max-h-[85vh] w-[90vw] max-w-[450px] translate-x-[-50%] translate-y-[-50%] rounded-[6px] bg-radix-gray-2 p-[25px] space-y-4 focus:outline-none">
          <Dialog.Title className="m-0 text-[24px] font-bold">
            Remover
          </Dialog.Title>
          <Dialog.Description className="text-[14px] ">
            Tem certeza que deseja remover essa ocorrencia?
          </Dialog.Description>
          <div className="flex  justify-end">
            <button
              onClick={handleRemoveOccurency}
              className="px-4 py-2 hover:bg-emerald-200 rounded-md text-gray-700"
            >
              Remover
            </button>
            <button
              type="button"
              className="px-4 py-2 hover:bg-emerald-200 rounded-md text-gray-700"
              onClick={() => setOpen(false)}
            >
              Fechar
            </button>
          </div>
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
