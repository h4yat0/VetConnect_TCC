import UnitCard from "../components/UnitCard";
import ServiceCard from "../components/ServiceCard";
import PromoCard from "../components/PromoCard";
import { useEffect, useState } from "react";
import HistoryCard from "../components/HistoryCard";
import useSimpleAuth from "../hooks/useSimpleAuth";
import AgendamentoModal from "../components/SchedulingModal";

const clinicas = {
  clinicas: [
    {
      id: 1,
      nome: "Clínica PetVet - Centro",
      imgPath: "ClínicaPetVet-Centro.jpg",
    },
    {
      id: 2,
      nome: "Clínica PetVet - Zona Oeste",
      imgPath: "Clínica PetVet - Zona Oeste.jpg",
    },
    {
      id: 3,
      nome: "Clínica PetVet - Zona Leste",
      imgPath: "Clínica PetVet - Zona Leste.jpg",
    },
    {
      id: 4,
      nome: "Clínica PetVet - Zona Norte",
      imgPath: "Clínica PetVet - Zona Norte.jpg",
    },
  ],
};

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
      clinicId: 1,
      nome: "Banho",
      dateService: "24/02/2023",
    },
    {
      clinicId: 2,
      nome: "Tosa",
      dateService: "24/01/2023",
    },
    {
      clinicId: 3,
      nome: "Castração",
      dateService: "24/01/2023",
    },
    {
      clinicId: 4,
      nome: "Vacinação",
      dateService: "24/01/2022",
    },
    {
      clinicId: 5,
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

  const [agendamentoIsOpen, setAgendamentoIsOpen] = useState(true);

  return (
    <div className="w-full">
      <AgendamentoModal
        type="new"
        isOpen={agendamentoIsOpen}
        setIsOpen={setAgendamentoIsOpen} 
      />
      <div className="p-0 font-inter ">
        <PromoCard></PromoCard>

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
                  />
                ))}
              </div>
            </>
          ) : (
            ""
          )}

          <div className="py-8">
            <h1 className="text-2xl font-black ">Clinicas</h1>
            <div className="flex flex-row gap-20 pt-2">
              {clinicas.clinicas.map((clinica) => (
                <UnitCard
                  key={clinica.id}
                  title={clinica.nome}
                  clinicId={clinica.id}
                  imgPath={"src/assets/imgs/" + clinica.imgPath}
                />
              ))}
            </div>
          </div>
        </div>
      </div>
      
    </div>
  );
}
