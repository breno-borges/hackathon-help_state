import { Link } from "react-router-dom";

export function AdminHomePage() {
  return (
    <div>
      <div className="pt-40">
        <h1 className="text-5xl">Bem vindo XXXX, Como podemos ajudar hoje?</h1>
      </div>
      <div className="pt-20 flex justify-center">
        <div id="Cartões" className="space-y-10 pt-10">
          <div>
            <div className="w-96 shadow-xl border-4 border-black bg-white rounded">
              <figure>
                <img src="./public/report.svg" alt="reports" />
              </figure>
              <div className="card-body">
                <h2 className="card-title">Verificar ocorrências ativas</h2>
                <p>Verifique municipios disponíveis em seu estado</p>
                <div className="card-actions justify-end">
                  <Link to={"/admin/active-reports"}>
                    <button className="btn btn-primary">Verificar</button>
                  </Link>
                </div>
              </div>
            </div>
          </div>

          {/* Final card 1 */}

          <div>
            <div className="w-96 shadow-xl border-4 border-black bg-white rounded">
              <figure>
                <img src="./public/history.svg" alt="Shoes" />
              </figure>
              <div className="card-body">
                <h2 className="card-title">Verificar ocorrências concluídas</h2>
                <p>Verifique as ocorrencias concluídas</p>
                <div className="card-actions justify-end">
                  <Link to={"/admin/active-reports"}>
                    <button className="btn btn-primary">Verificar</button>
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
