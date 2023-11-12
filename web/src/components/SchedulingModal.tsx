import { Dialog, Listbox, Transition } from "@headlessui/react";
import { Fragment, useEffect, useRef, useState } from "react";
import { XMarkIcon } from "@heroicons/react/20/solid";
import ButtonPrimary from "./buttons/ButtonPrimary";
import ButtonDanger from "./buttons/ButtonDanger";
import { MobileDateTimePicker } from "@mui/x-date-pickers";
import { getAccessToken, getAnimals } from "../redux/client";
import { useSelector } from "react-redux";
import dayjs from "dayjs";
import api from "../api/axios";

interface AgendamentoModalProps {
  type: "new" | "inProgress" | "finished";
  isOpen: boolean;
  setIsOpen: (isOpen: boolean) => void;
}

export default function AgendamentoModal({
  type,
  isOpen,
  setIsOpen,
}: AgendamentoModalProps) {

  const accessToken = useSelector(getAccessToken);

  const today = dayjs();
  const maxDate = today.add(1, "month");

  const animalsStore = useSelector(getAnimals);
  const animals = [...animalsStore];

  const services = [
    {
      id: 1,
      name: "Banho",
      value: 30.0,
    },
    {
      id: 2,
      name: "Tosa",
      value: 10.0,
    },
    {
      id: 3,
      name: "Castração",
      value: 20.0,
    },
    {
      id: 4,
      name: "Vacinação",
      value: 50.0,
    },
  ];

  const [selectedAnimal, setSelectedAnimal] = useState(animals[0]);
  const [selectedService, setSelectedService] = useState(services[0]);
  // const [selectedUnit, setSelectedUnit] = useState(units[0]);
  const [query, setQuery] = useState("");

  const newScheduling = type == "new";

  return (
    <>
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
                                <Listbox.Button className="relative w-full p-[18px] cursor-pointer rounded text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-vetConnectPrimaryGreen sm:text-sm">
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
                                  <Listbox.Options className="absolute z-50 mt-1 cursor-pointer w-full overflow-auto rounded py-1 bg-white text-base shadow-lg focus:outline-none sm:text-sm">
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
                              Animal: Dog
                            </div>
                          </div>
                        )}
                      </div>
                      <div className="min-w-[135px]">
                        {newScheduling ? (
                          <>
                            <span className="font-extrabold">
                              Data e horário do agendamento
                            </span>
                            <MobileDateTimePicker
                              defaultValue={today}
                              disablePast={true}
                              maxDateTime={maxDate}
                              ampm={false}
                              className="w-full"
                            />
                          </>
                        ) : (
                          <span className="text-black text-base font-extrabold">
                            22 mar - 15:00
                          </span>
                        )}
                      </div>
                    </div>
                  </div>
                  <div className="items-start gap-3 inline-flex py-2">
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
                                <Listbox.Button className="w-full p-4 cursor-pointer rounded text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-vetConnectPrimaryGreen sm:text-sm">
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
                                  <Listbox.Options className="absolute z-50 mt-1 cursor-pointer w-full overflow-auto rounded py-1 bg-white text-base shadow-lg focus:outline-none sm:text-sm">
                                    {services.map((service) => (
                                      <Listbox.Option
                                        key={service.id}
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
                              Serviço: Tosa
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
                          Valor: R$ 20,00
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
                          required
                          // onChange={(e) => setEmail(e.target.value)}
                          className="block w-full rounded border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                        />
                      </div>
                    </div>
    
                  <div className="items-start flex mt-5">
                    {newScheduling ? (
                      <ButtonPrimary type="button" text="Agendar" width="w-full" />
                    ) : (
                      <ButtonDanger text="Cancelar atendimento" width="w-full" />
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
