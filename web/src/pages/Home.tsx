import UnitCard from "../components/UnitCard";
import ServiceCard from "../components/ServiceCard";
import PromoCard from "../components/PromoCard";
import { useEffect, useState } from "react";
import HistoryCard from "../components/HistoryCard";
import useSimpleAuth from "../hooks/useSimpleAuth";
import SchedulingModal from "../components/SchedulingModal";
import { useSelector } from "react-redux";
import { getUnits } from "../redux/unit";
import unitsAndServices from "../hooks/useStoreRestock";
import useRestockUnitsAndServices from "../hooks/useStoreRestock";
import AlertConfirm from "../components/AlertConfirm";
import api from "../api/axios";

const SERVICE_URL = "/api/servico/v1/somente-servicos";

interface ApiService {
  id: number;
  nome: string;
  preco: number;
}

interface Service {
  id: number;
  name: string;
  price: number;
}

function mapApiServicesToServices(apiServices: ApiService[]): Service[] {
  return apiServices.map((apiService) => ({
    id: apiService.id,
    name: apiService.nome,
    price: apiService.preco,
  }));
}

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


export default function Home() {
  const loggedIn = useSimpleAuth();

  let units = useSelector(getUnits);
  units = [...units];

  const { getUnitsAndServices } = useRestockUnitsAndServices();

  const [services, setServices] = useState<Service[]>([]);
  const [schedulingIsOpen, setSchedulingIsOpen] = useState(false);
  const [serviceId, setServiceId] = useState<number>(-1)

  const handleSchedulingOpen = (serviceId : any) => {
    return ()=> {
      setServiceId(serviceId);
      setSchedulingIsOpen(true);
    }
  };

  useEffect(() => {
    if (units.length == 0) getUnitsAndServices();
    if (services.length == 0) getServices();
    setSchedulingIsOpen(false);
  }, []);

  const getServices = async () => {
    let response = await api
      .get(SERVICE_URL)
      .then(function (response) {
        let data = response.data;
        setServices(mapApiServicesToServices(data).slice(0,5));
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <div className="w-full">
      {units.length > 0 ? (
        <SchedulingModal
          type="inProgress"
          isOpen={schedulingIsOpen}
          serviceId={serviceId}
          setIsOpen={setSchedulingIsOpen}
        />
      ) : null}
      <div className="p-0 font-inter ">
        <PromoCard />

        <div className="py-8">
          {loggedIn ? (
            <>
              <h1 className="text-2xl font-black ">Serviços</h1>
              <div className="flex flex-row gap-10 pt-2 pb-6">
                {services.map((service) => (
                  <ServiceCard
                    key={service.id}
                    title={service.name}
                    serviceId={service.id}
                    handleSchedulingOpen={handleSchedulingOpen(service.id)}
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
