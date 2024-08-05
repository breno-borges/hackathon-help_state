import { CardDetailsDialog } from "./card-details-modal";
interface ShelterCardProps {
  id: string;
  title: string;
  description: string;
}
export function ShelterCard({ id, title, description }: ShelterCardProps) {
  return (
    <div className="w-[220px] h-[280px] flex flex-col rounded-md gap-5 border border-emerald-200 pb-2">
      <div className="w-full h-32 bg-emerald-600 rounded-t-md" />
      <h1 className="px-2">{title}</h1>
      <p className="px-2">{description} </p>
      <CardDetailsDialog />
    </div>
  );
}
