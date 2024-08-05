import * as Dialog from "@radix-ui/react-dialog";
import { ChevronRight, Plus, X } from "lucide-react";
import { useState } from "react";
export function CardDetailsDialog() {
  const [open, setOpen] = useState<boolean>(false);
  return (
    <Dialog.Root open={open} onOpenChange={setOpen}>
      <Dialog.Trigger asChild>
        <button className="text-emerald-500 hover:bg-emerald-100 px-4 py-2 rounded-lg flex gap-2 items-center mx-auto">
          <span>Visualizar</span>
          <ChevronRight className="w-4 h-4" />
        </button>
      </Dialog.Trigger>
      <Dialog.Portal>
        <Dialog.Overlay className="bg-stone-900 data-[state=open]:animate-overlayShow fixed inset-0 opacity-80" />
        <Dialog.Content className="data-[state=open]:animate-contentShow bg-emerald-50 fixed top-[50%] left-[50%] max-h-[85vh] w-[90vw] max-w-[450px] translate-x-[-50%] translate-y-[-50%] rounded-[6px] bg-radix-gray-2 p-[25px] space-y-8 focus:outline-none">
          <div>
            <Dialog.Title className="m-0 text-[24px] font-bold">
              Detalhes
            </Dialog.Title>
          </div>
          <div className="flex flex-col gap-5">
            <span>
              <p className="text-sm font-medium">Nome do local</p>
              <span>Teste local seguro</span>
            </span>
            <span>
              <p className="text-sm font-medium">Estado</p>
              <span>SP</span>
            </span>
            <span>
              <p className="text-sm font-medium">Municipio</p>
              <span>Municipio Teste</span>
            </span>
            <span>
              <p className="text-sm font-medium">Endereço</p>
              <span>Rua: Teste, 199 - Jd teste</span>
            </span>
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
