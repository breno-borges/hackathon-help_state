import { NewOccurencesDialog } from "./new-occurences-dialog";

export function OccurencesHeader() {
  return (
    <div className="flex gap-4 items-center mt-8 px-10">
      <h1 className="text-3xl text-emerald-600 font-bold">
        Minhas Ocorrencias
      </h1>
      <NewOccurencesDialog />
    </div>
  );
}
