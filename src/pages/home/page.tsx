import { HomePageHeader } from "./components/home-page-header";
import { OccurencyCard } from "./components/occurency-card";
import { ShelterList } from "./components/shelter-list";
import { NewOccurencesDialog } from "../occurrences/components/new-occurences-dialog";
import { NoDataMessage } from "./components/no-data-message";
import { useContext } from "react";
import { AuthContext } from "../../context/auth-context";
import { useNavigate } from "react-router-dom";
export function HomePage() {
  const navigate = useNavigate();
  const { isAuthenticated, isAdmin } = useContext(AuthContext);

  if (!isAuthenticated) return navigate("/login");

  if (isAdmin) return navigate("/admin");

  const fakeOccurencies: any = [
    { title: "Ocorrencia 1", description: "asn dihbsahiu" },
  ];
  return (
    <main className="flex flex-col gap-10">
      <HomePageHeader />
      <ShelterList />
      <div className="w-full flex flex-col gap-10 items-center">
        <h1 className="text-3xl font-bold text-emerald-500">
          Suas ultimas Ocorrencias
        </h1>
        <ul className="flex gap-10">
          {fakeOccurencies.length === 0 && (
            <NoDataMessage
              message="Nenhuma ocorrencia foi registrada ainda"
              children={<NewOccurencesDialog />}
            />
          )}
          {fakeOccurencies.map((occurency: any, index: number) => (
            <li key={index}>
              <OccurencyCard
                id=""
                title={occurency.title}
                description={occurency.description}
              />
            </li>
          ))}
        </ul>
      </div>
    </main>
  );
}
