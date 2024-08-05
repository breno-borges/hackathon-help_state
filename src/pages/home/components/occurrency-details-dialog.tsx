import * as Dialog from "@radix-ui/react-dialog";
import { X } from "lucide-react";
import { ReactNode, useState } from "react";
import { FailureBadge } from "../../../components/failure-badge";

interface OccurrencyDetailsDialogProps {
  triggerElement: ReactNode;
}
export function OccurrencyDetailsDialog({
  triggerElement,
}: OccurrencyDetailsDialogProps) {
  const [open, setOpen] = useState<boolean>(false);
  const handleUpdateOccurencyStatus = () => {
    // put method here
    alert("Att status");
    setOpen(false);
  };
  return (
    <Dialog.Root open={open} onOpenChange={setOpen}>
      <Dialog.Trigger asChild>{triggerElement}</Dialog.Trigger>
      <Dialog.Portal>
        <Dialog.Overlay className="bg-stone-900 data-[state=open]:animate-overlayShow fixed inset-0 opacity-80" />
        <Dialog.Content className="data-[state=open]:animate-contentShow overflow-y-auto bg-emerald-50 fixed top-[50%] left-[50%] max-h-[85vh] w-[90vw] max-w-[450px] translate-x-[-50%] translate-y-[-50%] rounded-[6px] bg-radix-gray-2 p-[25px] space-y-8 focus:outline-none">
          <div>
            <Dialog.Title className="m-0 text-[24px] font-bold">
              Detalhes da ocorrencia
            </Dialog.Title>
          </div>
          <div className="flex flex-col gap-5">
            <span>
              <p className="text-sm font-medium">Status</p>
              <FailureBadge message="Não resolvido" />
            </span>
            <span>
              <p className="text-sm font-medium">Titulo</p>
              <span>Teste Ocorrencia 1</span>
            </span>
            <span>
              <p className="text-sm font-medium">Descrição</p>
              <span>SasdsadsadsaP</span>
            </span>
            <span>
              <p className="text-sm font-medium">Categoria</p>
              <span>Teste cat</span>
            </span>
            <span>
              <p className="text-sm font-medium">Sub Categoria</p>
              <span>Teste sub</span>
            </span>
            <span>
              <p className="text-sm font-medium">Criado em</p>
              <span>22/07/2024</span>
            </span>
          </div>
          <div className="flex justify-end">
            <button
              onClick={handleUpdateOccurencyStatus}
              className="px-4 py-2 font-bold ring-inset ring-1 ring-emerald-300 hover:bg-emerald-200 rounded-md text-gray-700"
            >
              Marcar como resolvida
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
