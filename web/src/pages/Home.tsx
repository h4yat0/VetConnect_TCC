import UnitCard from "../components/UnitCard";
import PromoCard from "../components/PromoCard";
import { useEffect, useState } from "react";
import useSimpleAuth from "../hooks/useSimpleAuth";
import ScheduleModal from "../components/SchedulingModal";
import { useDispatch, useSelector } from "react-redux";
import { getUnits } from "../redux/unit";
import useRestockUnitsAndServices from "../hooks/useStoreRestock";
import api from "../api/axios";
import useRefreshToken from "../hooks/useRefreshToken";
import { PlusIcon } from "@heroicons/react/24/outline";
import {
  getAccessToken,
  getAnimals,
  getId,
  getSchedules,
  updateSchedules,
} from "../redux/client";
import ScheduleCard from "../components/ScheduleCard";
import ButtonSecundary from "../components/buttons/ButtonSecundary";
import HistoricModal from "../components/HistoricModal";
import dayjs from "dayjs";
import { useNavigate } from "react-router-dom";

const SCHEDULING_URL = "/api/agendamento/v1/buscarAgendamentos/";

export default function Home() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const loggedIn = useSimpleAuth();
  const clientId = useSelector(getId);
  const accessToken = useSelector(getAccessToken);
  const schedules = [...useSelector(getSchedules)].filter(
    (schedule) => schedule.status == 1
  );
  const units = [...useSelector(getUnits)];
  const animals = useSelector(getAnimals);

  const animalsValidation = animals.length > 0 && animals[0].id !== -1;

  const { getUnitsAndServices } = useRestockUnitsAndServices();

  const [scheduleId, setScheduleId] = useState(0);
  const [schedulingIsOpen, setSchedulingIsOpen] = useState(false);
  const [historicIsOpen, setHistoricIsOpen] = useState(false);
  const [schedulingType, setSchedulingType] = useState<
    "new" | "inProgress" | "finished"
  >("new");

  const [serviceId, setServiceId] = useState<number>(-1);

  const formatDate = (date: string) => {
    const formatedDate = dayjs(date).format("DD/MM/YYYY");
    return formatedDate;
  };

  const handleNewSchedule = (serviceId: number) => {
    return () => {
      setSchedulingType("new");
      setServiceId(serviceId);
      setScheduleId(-1);
      setSchedulingIsOpen(true);
    };
  };

  const handleSchedule = (
    schedulingType: "inProgress" | "finished",
    scheduleId: number
  ) => {
    return () => {
      setSchedulingType(schedulingType);
      setScheduleId(scheduleId);
      setSchedulingIsOpen(true);
    };
  };

  const getSchedulesApi = async () => {
    let response = await api
      .get(SCHEDULING_URL + clientId, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then(function (response) {
        let data = response.data;
        dispatch(updateSchedules(data));
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  useEffect(() => {
    if (units.length == 0) getUnitsAndServices();
    if (loggedIn) getSchedulesApi();
    setSchedulingIsOpen(false);
  }, []);

  return (
    <div className="w-full">
      {units.length > 0 && loggedIn ? (
        <>
          <ScheduleModal
            type={schedulingType}
            isOpen={schedulingIsOpen}
            serviceId={serviceId}
            scheduleId={scheduleId}
            setIsOpen={setSchedulingIsOpen}
          />

          <HistoricModal isOpen={historicIsOpen} setIsOpen={setHistoricIsOpen} />
        </>
      ) : null}
      <div className="p-0 font-inter ">
        <PromoCard />

        <div className="py-8">
          {loggedIn ? (
            <>
              <div className="flex justify-between">
                <h1 className="text-2xl font-black ">Agendamentos</h1>
                <ButtonSecundary
                  text="Histórico"
                  onClickFunction={() => setHistoricIsOpen(true)}
                />
              </div>

              <div className="flex gap-8 pt-2 pb-6 px-2 overflow-x-auto">
                <button
                  className="min-w-50 p-4 bg-white flex  items-center space-x-4 rounded-lg border-4 border-dashed hover:scale-105 hover:border-vetConnectPrimaryGreen hover:text-vetConnectPrimaryGreen transition transform duration-500 cursor-pointer"
                  onClick={
                    animalsValidation
                      ? handleNewSchedule(-1)
                      : () => navigate("user/client")
                  }
                >
                  <div className="mt-4 ml-2">
                    <PlusIcon className="h-10 w-10" />
                  </div>
                  <div className="w-full">
                    <h1 className="p-2 text-left text-xl font-bold text-gray-700">
                      {animalsValidation ? (
                        <>
                          Novo <br /> Agendamento
                        </>
                      ) : (
                        <>
                          Você não tem um <br /> animal cadatrado
                        </>
                      )}
                    </h1>
                  </div>
                </button>
                {schedules.length > 0
                  ? schedules.map((schedule) => (
                      <ScheduleCard
                        key={schedule.id}
                        id={schedule.id}
                        status={schedule.status}
                        animal={schedule.animal}
                        unit={schedule.unit}
                        service={schedule.service}
                        scheduleDate={formatDate(schedule.scheduledDate)}
                        scheduleTime={schedule.scheduledTime}
                        handleSchedulingOpen={handleSchedule(
                          "inProgress",
                          schedule.id
                        )}
                      />
                    ))
                  : null}
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
