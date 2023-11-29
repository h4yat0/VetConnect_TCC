import { Dialog, Listbox, Transition } from "@headlessui/react";
import { Fragment, useEffect, useRef, useState } from "react";
import { XMarkIcon } from "@heroicons/react/20/solid";
import ButtonPrimary from "./buttons/ButtonPrimary";
import ButtonDanger from "./buttons/ButtonDanger";
import { DatePicker, TimePicker } from "@mui/x-date-pickers";
import { getAccessToken, getAnimals, getId, getSchedules, updateSchedules } from "../redux/client";
import { useDispatch, useSelector } from "react-redux";
import dayjs from "dayjs";
import api from "../api/axios";
import AlertConfirm from "./AlertConfirm";
import { getUnits } from "../redux/unit";

interface Schedules {
  id: number;
  client: string;
  animal: string;
  unit: string;
  service: string;
  scheduledDate: string;
  scheduledTime: string;
  scheduledValue: number;
  canceled: boolean;
  observation: string;
}

interface AgendamentoModalProps {
  type: "new" | "inProgress" | "finished";
  isOpen: boolean;
  unitId?: number;
  serviceId?: number;
  scheduleId: number;
  setIsOpen: (isOpen: boolean) => void;
}

const SCHEDULE_URL = "api/agendamento/v1/agendar";
const SCHEDULING_URL = "/api/agendamento/v1/buscarAgendamentos/";
const CANCEL_AGENDAMENTO = "api/agendamento/v1/cancelar-agendamento/";

function parseTimeString(timeString: string): { hour: number; minute: number } {
  const [hourStr, minuteStr] = timeString.split(":");
  const hour = parseInt(hourStr, 10);
  const minute = parseInt(minuteStr, 10);

  return { hour, minute };
}

export default function ScheduleModal({
  type,
  isOpen,
  unitId,
  serviceId,
  scheduleId,
  setIsOpen,
}: AgendamentoModalProps) {
  const dispatch = useDispatch();
  const accessToken = useSelector(getAccessToken);
  const clientId = useSelector(getId);

  const animals = [...useSelector(getAnimals)];
  const units = [...useSelector(getUnits)];
  const schedule = [...useSelector(getSchedules)].find(
    (schedule) => schedule.id === scheduleId
  );

  const [alertConfirmIsOpen, setAlertConfirmIsOpen] = useState(false);
  const [selectedAnimal, setSelectedAnimal] = useState(animals[0]);
  const [selectedUnit, setSelectedUnit] = useState(units[0]);

  const services = [...selectedUnit.services];
  const [selectedService, setSelectedService] = useState(services[0]);

  const newScheduling = type == "new";

  const minDate = dayjs().add(1, "day");
  const maxDate = minDate.add(1, "month");
  const minTime = parseTimeString(selectedUnit.openingTime);
  const maxTime = parseTimeString(selectedUnit.closingTime);

  const [date, setDate] = useState(minDate);
  const [time, setTime] = useState(minTime);

  const [observation, setObservation] = useState("Sem observações");
  const [waitingList, setWaitingList] = useState(false)

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

  const postScheduling = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (selectedService.id == undefined || selectedUnit.id == undefined) {
      return;
    }
    let reponse = await api
      .post(
        SCHEDULE_URL,
        {
          idCliente: clientId,
          idAnimal: selectedAnimal.id,
          idServico: selectedService.id,
          idUnidade: selectedUnit.id,
          dataAgendada: date.format("DD/MM/YYYY"),
          horaAgendada: `${time.hour}:${time.minute}`,
          valorAgendado: selectedService.price,
          observacao: observation,
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      )
      .then(function (response) {
        let data = response.data;
        getSchedulesApi()
        setIsOpen(false)
        console.log(data);
      })
      .catch(function (error) {
        console.log(error);
        console.log(`${time.hour}:${time.minute}`);
        if(error?.status == 409){
          setWaitingList(true)
          setAlertConfirmIsOpen(true)
        }
        if (!error?.message) {
          // setErrorMessage("No server response");
        } else {
          // setErrorMessage(error.message);
        }
        // errRef.current?.focus();
      });
  };

  const cancelScheduling = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    let reponse = await api
      .put(
        `${CANCEL_AGENDAMENTO + scheduleId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      )
      .then(function (response) {
        getSchedulesApi()
        setIsOpen(false);
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  useEffect(() => {
    if (serviceId !== -1) locateServiceId(serviceId);
  }, [serviceId]);

  const locateServiceId = (serviceId: any) => {
    if (units.length > 0 && services.length > 0) {
      for (let i = 0; i < units.length; i++) {
        const unit = units[i];
        const serviceIndex = unit.services.findIndex(
          (service) => service.id === serviceId
        );
        if (serviceIndex !== -1) {
          setSelectedUnit(
            unitId != i && unitId != undefined && unitId > 0
              ? units[unitId - 1]
              : units[i]
          );
          setSelectedService(services[serviceIndex]);
        }
      }
    }
  };

  return (
    <>
      <AlertConfirm
        isOpen={alertConfirmIsOpen}
        setIsOpen={setAlertConfirmIsOpen}
        type="insert"
        onConfirmFunction={newScheduling ? postScheduling : cancelScheduling}
        message={
          newScheduling
            ? waitingList 
              ? "Deseja entrar na fila de espera?" 
              : "Confirme o agendamento"
            : "Confirme o cancelamento do agendamento"
        }
      />

      <Transition appear show={isOpen} as={Fragment}>
        <Dialog
          as="div"
          className="relative z-10"
          onClose={() => setIsOpen(!isOpen)}
        >
          <Transition.Child
            as={Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-black/25" />
          </Transition.Child>

          <div className="fixed inset-0 overflow-y-auto font-inter">
            <div className="flex min-h-full items-center justify-center p-4 text-center">
              <Transition.Child
                as={Fragment}
                enter="ease-out duration-300"
                enterFrom="opacity-0 scale-95"
                enterTo="opacity-100 scale-100"
                leave="ease-in duration-200"
                leaveFrom="opacity-100 scale-100"
                leaveTo="opacity-0 scale-95"
              >
                <Dialog.Panel className="min-w-[550px] transform rounded-2xl bg-white p-6 text-left align-middle shadow-xl transition-all">
                  <div className="justify-between items-center flex">
                    <Dialog.Title
                      as="h3"
                      className="text-2xl font-black leading-6 pb-2 text-gray-900"
                    >
                      {() => {
                        switch (type) {
                          case "new":
                            return <span>Novo agendamento</span>;
                          case "inProgress":
                            return <span>Agendamento em progresso</span>;
                          case "finished":
                            return <span>Agendamento concluído</span>;
                          default:
                            return <span></span>;
                        }
                      }}
                    </Dialog.Title>

                    <button
                      type="button"
                      className="h-8 w-8"
                      onClick={() => setIsOpen(!isOpen)}
                    >
                      <XMarkIcon />
                    </button>
                  </div>
                  <div className="mt-2">
                    <div className="justify-between gap items-center flex py-2 gap-3">
                      <div className="w-full">
                        {newScheduling ? (
                          <>
                            <span className="font-extrabold">
                              Selecione o animal
                            </span>
                            <Listbox
                              value={selectedAnimal}
                              onChange={setSelectedAnimal}
                            >
                              <div className="relative">
                                <Listbox.Button className="relative w-full min-h-[56px] p-[18px] cursor-pointer rounded text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-vetConnectPrimaryGreen sm:text-sm">
                                  <span className="block truncate">
                                    {selectedAnimal.name}
                                  </span>
                                </Listbox.Button>
                                <Transition
                                  as={Fragment}
                                  leave="transition ease-in duration-100"
                                  leaveFrom="opacity-100"
                                  leaveTo="opacity-0"
                                >
                                  <Listbox.Options className="absolute max-h-44 z-50 mt-1 cursor-pointer w-full overflow-auto rounded py-1 bg-white text-base shadow-lg focus:outline-none sm:text-sm">
                                    {animals.map((animal) => (
                                      <Listbox.Option
                                        key={animal.id}
                                        className={({ active }) =>
                                          `relative cursor-default select-none py-2 pl-10 pr-4 ${
                                            active
                                              ? "text-vetConnectPrimaryGreen font-extrabold"
                                              : "text-gray-900"
                                          }`
                                        }
                                        value={animal}
                                      >
                                        {({ selected }) => (
                                          <>
                                            <span
                                              className={`block truncate ${
                                                selected
                                                  ? "font-medium"
                                                  : "font-normal"
                                              }`}
                                            >
                                              {animal.name}
                                            </span>
                                          </>
                                        )}
                                      </Listbox.Option>
                                    ))}
                                  </Listbox.Options>
                                </Transition>
                              </div>
                            </Listbox>
                          </>
                        ) : (
                          <div className="p-5 rounded border border-black  ">
                            <div className="text-black text-sm font-extrabold ">
                              Animal:{" "}
                              {schedule != undefined ? schedule.animal : "Animal"}
                            </div>
                          </div>
                        )}
                      </div>
                      <div className="w-full">
                        {newScheduling ? (
                          <>
                            <span className="font-extrabold">
                              Selecione a unidade
                            </span>
                            <Listbox value={selectedUnit} onChange={setSelectedUnit}>
                              <div className="relative">
                                <Listbox.Button className="relative w-full p-[18px] cursor-pointer rounded text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-vetConnectPrimaryGreen sm:text-sm">
                                  <span className="block truncate">
                                    {selectedUnit.name}
                                  </span>
                                </Listbox.Button>
                                <Transition
                                  as={Fragment}
                                  leave="transition ease-in duration-100"
                                  leaveFrom="opacity-100"
                                  leaveTo="opacity-0"
                                >
                                  <Listbox.Options className="absolute max-h-44 z-50 mt-1 cursor-pointer w-full overflow-auto rounded py-1 bg-white text-base shadow-lg focus:outline-none sm:text-sm">
                                    {units.map((unit) => (
                                      <Listbox.Option
                                        key={unit.id}
                                        className={({ active }) =>
                                          `relative cursor-default select-none py-2 pl-10 pr-4 ${
                                            active
                                              ? "text-vetConnectPrimaryGreen font-extrabold"
                                              : "text-gray-900"
                                          }`
                                        }
                                        value={unit}
                                      >
                                        {({ selected }) => (
                                          <>
                                            <span
                                              className={`block truncate ${
                                                selected
                                                  ? "font-medium"
                                                  : "font-normal"
                                              }`}
                                            >
                                              {unit.name}
                                            </span>
                                          </>
                                        )}
                                      </Listbox.Option>
                                    ))}
                                  </Listbox.Options>
                                </Transition>
                              </div>
                            </Listbox>
                          </>
                        ) : (
                          <div className="p-5 rounded border border-black  ">
                            <div className="text-black text-sm font-extrabold ">
                              Unidade:{" "}
                              {schedule != undefined ? schedule.unit : "Unidade"}
                            </div>
                          </div>
                        )}
                      </div>
                    </div>
                  </div>
                  <div className="items-start gap-3 inline-flex py-2 min-h-min">
                    <div className="flex gap-3">
                      <div>
                        {newScheduling ? (
                          <>
                            <span className="font-extrabold">
                              Selecione o serviço
                            </span>
                            <Listbox
                              value={selectedService}
                              onChange={setSelectedService}
                            >
                              <div className="relative">
                                <Listbox.Button className="w-full p-[18px] cursor-pointer rounded text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-vetConnectPrimaryGreen sm:text-sm">
                                  <span className="block truncate">
                                    {selectedService.name}
                                  </span>
                                </Listbox.Button>
                                <Transition
                                  as={Fragment}
                                  leave="transition ease-in duration-100"
                                  leaveFrom="opacity-100"
                                  leaveTo="opacity-0"
                                >
                                  <Listbox.Options className="absolute max-h-44 z-50 mt-1 cursor-pointer w-full overflow-auto rounded py-1 bg-white text-base shadow-lg focus:outline-none sm:text-sm">
                                    {services.map((service) => (
                                      <Listbox.Option
                                        key={"service" + service.id}
                                        className={({ active }) =>
                                          `relative cursor-default select-none py-2 pl-10 pr-4 ${
                                            active
                                              ? "text-vetConnectPrimaryGreen font-extrabold"
                                              : "text-gray-900"
                                          }`
                                        }
                                        value={service}
                                      >
                                        {({ selected }) => (
                                          <>
                                            <span
                                              className={`block truncate ${
                                                selected
                                                  ? "font-medium"
                                                  : "font-normal"
                                              }`}
                                            >
                                              {service.name}
                                            </span>
                                          </>
                                        )}
                                      </Listbox.Option>
                                    ))}
                                  </Listbox.Options>
                                </Transition>
                              </div>
                            </Listbox>
                          </>
                        ) : (
                          <div className="p-5 rounded border border-black ">
                            <div className="text-black text-sm font-extrabold ">
                              Serviço:{" "}
                              {schedule != undefined ? schedule.service : "Serviço"}
                            </div>
                          </div>
                        )}
                      </div>
                      <div className="max-w-[2000px]">
                        {newScheduling ? (
                          <>
                            <span className="font-extrabold">
                              Data e horário do agendamento
                            </span>
                            <div className="flex gap-2">
                              <DatePicker
                                disablePast
                                value={date}
                                onChange={() => setDate(date)}
                                defaultValue={minDate}
                                maxDate={maxDate}
                              />

                              <TimePicker
                                defaultValue={dayjs().set("hour", 6).startOf("hour")}
                                minTime={dayjs()
                                  .set("hour", minTime.hour)
                                  .set("minute", minTime.minute)}
                                maxTime={dayjs()
                                  .set("hour", maxTime.hour)
                                  .set("minute", maxTime.minute)}
                              />
                            </div>
                          </>
                        ) : (
                          <div className="p-5 rounded border border-black  ">
                            <div className="text-black text-sm font-extrabold ">
                              {schedule != undefined
                                ? `${schedule.scheduledDate} - ${schedule.scheduledTime}`
                                : "data - hora"}
                            </div>
                          </div>
                        )}
                      </div>
                      <div
                        className={`rounded border border-black ${
                          newScheduling ? "mt-6 p-4" : "p-5"
                        }`}
                      >
                        <div className="text-black text-sm font-extrabold ">
                          R${" "}
                          {schedule != undefined && scheduleId != -1
                            ? schedule.scheduledValue
                                .toFixed(2)
                                .toString()
                                .replace(".", ",")
                            : selectedService.price.toString().replace(".", ",")}
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="py-2">
                    <label
                      htmlFor="observation"
                      className="text-sm leading-6 font-extrabold text-gray-900"
                    >
                      Oberservações
                    </label>
                    <div className="my-2">
                      <textarea
                        id="observation"
                        name="observation"
                        maxLength={200}
                        value={scheduleId > 0 ? schedule?.observation : observation }
                        disabled={scheduleId > 0 ? true : false}
                        onChange={(e) => setObservation(e.target.value)}
                        className="block w-full rounded border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                      />
                    </div>
                  </div>

                  <div className="items-start flex mt-5">
                    {newScheduling ? (
                      <ButtonPrimary
                        type="submit"
                        text="Agendar"
                        width="w-full"
                        onClickFunction={() =>
                          setAlertConfirmIsOpen(!alertConfirmIsOpen)
                        }
                      />
                    ) : (
                      <ButtonDanger
                        text="Cancelar agendamento"
                        width="w-full"
                        onClickFunction={() =>
                          setAlertConfirmIsOpen(!alertConfirmIsOpen)
                        }
                      />
                    )}
                  </div>
                </Dialog.Panel>
              </Transition.Child>
            </div>
          </div>
        </Dialog>
      </Transition>
    </>
  );
}
