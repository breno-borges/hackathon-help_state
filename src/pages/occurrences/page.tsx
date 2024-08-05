import { OccurencesHeader } from "./components/occurences-header";
import { OccurencyItem } from "./components/occurency-item";

export function OccurrencesPage() {
  return (
    <main className="flex flex-col">
      <OccurencesHeader />
      <section className="mx-10 mt-10 flex flex-col gap-10">
        <OccurencyItem />
        <OccurencyItem />
        <OccurencyItem />
      </section>
    </main>
  );
}
