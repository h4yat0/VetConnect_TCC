import UnitCard from "../components/UnitCard";
import ServiceCard from "../components/ServiceCard";
import PromoCard from "../components/PromoCard";
import { useEffect, useState } from "react";
import HistoryCard from "../components/HistoryCard";
import useSimpleAuth from "../hooks/useSimpleAuth";
import AgendamentoModal from "../components/SchedulingModal";
import { useSelector } from "react-redux";
import { getUnits } from "../redux/unit";
import unitsAndServices from "../hooks/useStoreRestock";
import useRestockUnitsAndServices from "../hooks/useStoreRestock";

const servicos = {
  servicos: [
    {
      id: 1,
      nome: "Banho",
      iconName: "ClínicaPetVet-Centro.jpg",
    },
    {
      id: 2,
      nome: "Tosa",
      iconName: "Clínica PetVet - Zona Oeste.jpg",
    },
    {
      id: 3,
      nome: "Castração",
      iconName: "Clínica PetVet - Zona Leste.jpg",
    },
    {
      id: 4,
      nome: "Vacinação",
      iconName: "Clínica PetVet - Zona Norte.jpg",
    },
  ],
};

const HistoryServices = {
  history: [
    {
      id: 1,
      clinicId: 1,
      nome: "Banho",
      dateService: "24/02/2023",
    },
    {
      id: 2,
      clinicId: 2,
      nome: "Tosa",
      dateService: "24/01/2023",
    },
    {
      id: 3,
      clinicId: 3,
      nome: "Castração",
      dateService: "24/01/2023",
    },
    {
      id: 4,
      clinicId: 4,
      nome: "Vacinação",
      dateService: "24/01/2022",
    },
  ],
};

const Agendamentos = {
  servicos: [
    {
      id: 1,
      nome: "Banho",
      iconName: "ClínicaPetVet-Centro.jpg",
    },
    {
      id: 2,
      nome: "Tosa",
      iconName: "Clínica PetVet - Zona Oeste.jpg",
    },
    {
      id: 3,
      nome: "Castração",
      iconName: "Clínica PetVet - Zona Leste.jpg",
    },
    {
      id: 4,
      nome: "Vacinação",
      iconName: "Clínica PetVet - Zona Norte.jpg",
    },
  ],
};

export default function Home() {

  const loggedIn = useSimpleAuth();

  let units = useSelector(getUnits)
  units = [...units]

  const [agendamentoIsOpen, setAgendamentoIsOpen] = useState(true);

  const { getUnitsAndServices } = useRestockUnitsAndServices();


  useEffect(() => {
    if (units.length == 0) getUnitsAndServices();
  }, []);

  return (
    <div className="w-full">
      <AgendamentoModal
        type="new"
        isOpen={agendamentoIsOpen}
        setIsOpen={setAgendamentoIsOpen}
      />
      <div className="p-0 font-inter ">
        <PromoCard />

        <div className="py-8">
          {loggedIn ? (
            <>
              <h1 className="text-2xl font-black ">Agendamentos</h1>
              <div className="flex flex-row gap-20 pt-2 pb-6">
                {servicos.servicos.map((servicos) => (
                  <ServiceCard
                    key={servicos.id}
                    title={servicos.nome}
                    serviceId={servicos.id}
                    iconName={"src/assets/imgs/" + servicos.iconName}
                  />
                ))}
              </div>

              <h1 className="text-2xl font-black ">Últimos serviços utilizados</h1>
              <div className="flex flex-row gap-20 pt-2 pb-6">
                {HistoryServices.history.map((history) => (
                  <HistoryCard
                    nome={history.nome}
                    clinicId={history.clinicId}
                    dateService={history.dateService}
                    key={history.id}
                  />
                ))}
              </div>
            </>
          ) : null}

          <div className="py-8">
            <h1 className="text-2xl font-black ">Unidades</h1>
            <div className="flex-row gap-20 pt-5 grid grid-cols-4 ">
              {units.map((unit) => (
                <UnitCard
                  key={unit.id}
                  title={unit.name}
                  unitId={unit.id}
                  image={unit.images[0]}
                />
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
