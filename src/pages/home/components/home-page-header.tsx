import { MapPin } from "lucide-react";
import { FailureBadge } from "../../../components/failure-badge";
import { SuccessBadge } from "../../../components/success-badge";

export function HomePageHeader() {
  const stateStatus = "OK";
  const description = "No momento esta tudo certo com a região onde reside";

  return (
    <div className="w-full py-20 flex gap-[200px] items-center justify-center">
      <h1 className="text-emerald-500 font-bold  text-[64px] max-w-[450px]">
        Bem Vindo (a), Lucas
      </h1>
      <section className="flex flex-col gap-4 ">
        <div className="flex flex-col gap-1">
          <span className="font-bold text-2xl">Status Atual do estado</span>

          <div className="flex gap-2 items-center">
            <MapPin />
            <span className="font-bold text-lg">SP</span>
          </div>
        </div>
        {stateStatus === "OK" ? <SuccessBadge /> : <FailureBadge />}
        <span className="max-w-[250px]">{description}</span>
      </section>
    </div>
  );
}
