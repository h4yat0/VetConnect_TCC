import { useSelector } from "react-redux";
import { getUnit, getUnits } from "../redux/unit";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import Cleave from "cleave.js/react";
import ServiceCard from "../components/ServiceCard";
import ScheduleModal from "../components/SchedulingModal";
import useRestockUnitsAndServices from "../hooks/useStoreRestock";
import useSimpleAuth from "../hooks/useSimpleAuth";

export default function UnitPage() {

  const loggedIn = useSimpleAuth();

  const location = useLocation();
  const navigate = useNavigate();

  const { getUnitsAndServices } = useRestockUnitsAndServices();

  const unitId: number = parseInt(location.pathname.replace("/unidades/", ""));
  const units = [...useSelector(getUnits)];
  const unit = units.find((unit) => unit.id === unitId);

  useEffect(() => {
    if (units.length == 0) getUnitsAndServices();
    setSchedulingIsOpen(false);
  }, []);
  
  if (unit === undefined) {
    navigate("/", { replace: true });
    return null
  }

  const [schedulingIsOpen, setSchedulingIsOpen] = useState(false);
  const [serviceId, setServiceId] = useState<number>(-1);
  
  const services = [...unit.services];

    const handleSchedulingOpen = (serviceId: any) => {
      return () => {
        setServiceId(serviceId);
        setSchedulingIsOpen(true);
      };
    };

  return (
    <>
      {units.length > 0 && loggedIn ? (
        <ScheduleModal
          type="new"
          scheduleId={-1}
          isOpen={schedulingIsOpen}
          serviceId={serviceId}
          unitId={unit.id}
          setIsOpen={setSchedulingIsOpen}
        />
      ) : null}
      <div className="px-44 pt-10 h-screen font-inter">
        <div className="grid grid-cols-2 gap-7">
          <img
            className="w-full h-full object-cover rounded-lg"
            src={`data:image/webp;base64,${unit.images[0]}`}
            alt="Imagem da Unidade"
          />
          <div className="p-4 rounded-lg border-vetConnectSecundaryGreen">
            <h1 className="text-3xl font-bold">{unit.name}</h1>
            <div className="pt-4 gap-y-6 flex flex-col">
              <div className="text-lg">
                <p>
                  <b>Horário de Funcionamento:</b> {`${unit.openingTime}`} -{" "}
                  {`${unit.closingTime}`}
                </p>
                <p>
                  <b>Contato:</b>

                  <Cleave
                    type="text"
                    options={{ phone: true, phoneRegionCode: "br" }}
                    value={unit.contact}
                    disabled
                    className="appearance-none border-none"
                  />
                </p>
              </div>
              <div className="flex flex-col p-2 rounded-lg border-2">
                <p className="mb-2">
                  <b>Especialidades:</b>
                </p>
                <div className="flex flex-wrap gap-2">
                  {unit.specialty.split(",").map((especialit,i) => (
                    <span className="p-1 rounded-md border-2 border-gray-400" key={i}>
                      {especialit.trim()}
                    </span>
                  ))}
                </div>
              </div>
            </div>
          </div>
        </div>
        <div className="pt-12">
          <h1 className="text-2xl font-bold">Serviços</h1>
          <div className="grid grid-cols-4 gap-5 mt-3">
            {services.map((service) => (
              <ServiceCard
                key={service.id}
                title={service.name}
                serviceId={service.id}
                handleSchedulingOpen={handleSchedulingOpen(service.id)}
              />
            ))}
          </div>
        </div>
      </div>
    </>
  );
}
