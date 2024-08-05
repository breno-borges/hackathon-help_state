import { ShelterCard } from "./shelter-card";

export function ShelterList() {
  return (
    <div className="w-full flex flex-col gap-10 items-center">
      <h1 className="text-3xl font-bold text-emerald-500">Abrigos Seguros</h1>
      <ul className="flex gap-10">
        <li>
          <ShelterCard id="" title="Abrigo 1" description="Rua 1" />
        </li>
        <li>
          <ShelterCard id="" title="Abrigo 1" description="Rua 1" />
        </li>
        <li>
          <ShelterCard id="" title="Abrigo 1" description="Rua 1" />
        </li>
        <li>
          <ShelterCard id="" title="Abrigo 1" description="Rua 1" />
        </li>
      </ul>
    </div>
  );
}
