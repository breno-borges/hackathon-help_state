import { ChevronRight } from "lucide-react";
import { OccurrencyDetailsDialog } from "./occurrency-details-dialog";
interface OccurencyCardProps {
  id: string;
  title: string;
  description: string;
}
export function OccurencyCard({ id, title, description }: OccurencyCardProps) {
  return (
    <div className="w-[220px] h-[280px] flex flex-col rounded-md gap-5 border border-emerald-200 pb-2">
      <div className="w-full h-32 bg-emerald-600 rounded-t-md" />
      <h1 className="px-2">{title}</h1>
      <p className="px-2">{description} </p>
      <OccurrencyDetailsDialog
        triggerElement={
          <button className="text-emerald-500 hover:bg-emerald-100 px-4 py-2 rounded-lg flex gap-2 items-center mx-auto">
            <span>Visualizar</span>
            <ChevronRight className="w-4 h-4" />
          </button>
        }
      />
    </div>
  );
}
