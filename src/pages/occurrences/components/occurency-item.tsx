import { FailureBadge } from "../../../components/failure-badge";
import { OccurrencyDetailsDialog } from "../../home/components/occurrency-details-dialog";
import { RemoveOccurencyValidationDialog } from "./remove-occurency-validation-dialog";

export function OccurencyItem() {
  return (
    <div className="h-40 flex gap-3 border border-stone-300 shadow-md rounded-lg">
      <div className="h-full w-40 bg-emerald-600 " />
      <div className="flex flex-col flex-1 gap-1 py-4">
        <FailureBadge message="Não Resolvido" />
        <h1 className="text-xl font-bold">Ocorrencia 1</h1>
        <small>Criado em: </small>
        <span>sadbnasuyvdusavdsaudasdsa</span>
      </div>
      <div className="flex flex-col justify-center gap-2 py-4 pr-5">
        <OccurrencyDetailsDialog
          triggerElement={
            <button className="bg-emerald-500 border border-emerald-400 px-6 py-2 hover:bg-emerald-400 text-gray-50 rounded-md font-bold">
              Visualizar
            </button>
          }
        />

        <RemoveOccurencyValidationDialog />
      </div>
    </div>
  );
}
