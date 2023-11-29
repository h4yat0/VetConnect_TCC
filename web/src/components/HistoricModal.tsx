import { Dialog, Listbox, Transition } from "@headlessui/react";
import { Fragment, useEffect, useRef, useState } from "react";
import { XMarkIcon } from "@heroicons/react/20/solid";
import ButtonPrimary from "./buttons/ButtonPrimary";
import ButtonDanger from "./buttons/ButtonDanger";
import { DatePicker, TimePicker } from "@mui/x-date-pickers";
import { getAccessToken, getAnimals, getId, getSchedules } from "../redux/client";
import { useSelector } from "react-redux";
import dayjs from "dayjs";
import api from "../api/axios";
import AlertConfirm from "./AlertConfirm";
import { getUnits } from "../redux/unit";

interface HistoricModalProps {
  isOpen: boolean;
  setIsOpen: (isOpen: boolean) => void;
}

export default function HistoricModal({ isOpen, setIsOpen }: HistoricModalProps) {
  const schedules = [...useSelector(getSchedules)].filter(
    (schedule) => schedule.status != 1 
  );



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
                <Dialog.Panel className="min-w-[550px] min-h-[500px] max-h-[600px] overflow-y-auto transform rounded-2xl bg-white p-6 text-left align-middle shadow-xl transition-all">
                  <div className="justify-between items-center flex">
                    <Dialog.Title
                      as="h3"
                      className="text-2xl font-black leading-6 pb-2 text-gray-900"
                    >
                      <span>Histórico de Agendamentos</span>
                    </Dialog.Title>

                    <button
                      type="button"
                      className="h-8 w-8"
                      onClick={() => setIsOpen(!isOpen)}
                    >
                      <XMarkIcon />
                    </button>
                  </div>
                  <div>
                    {schedules.length > 0 ? (
                      schedules.map((schedule) => (
                        <div
                        key={schedule.id}
                          className={`my-3 p-4 flex space-x-4 rounded-lg border border-black shadow-md hover:scale-105 transition transform duration-500 cursor-pointer min-w-[275px]
                                    ${
                                      schedule.status == 3
                                        ? "bg-red-400"
                                        : schedule.status == 2
                                        ? "bg-vetConnectSecundaryGreen"
                                        : "bg-white"
                                    }`}
                        >
                          <div>
                            <h1 className="text-start text-xl font-bold text-gray-700">
                              {schedule.animal}
                            </h1>
                            <div className="flex flex-col">
                              <span className="pt-[2px]">
                                <b>Data e hora: </b>
                                {schedule.scheduledDate} - {schedule.scheduledTime}
                              </span>
                              <span>
                                <b>Unidade:</b> {schedule.unit}
                              </span>

                              <span>
                                <b>Serviço</b> {schedule.service}
                              </span>
                            </div>
                          </div>
                        </div>
                      ))
                    ) : (
                      <span>Nenhum agendamento para ser exíbido</span>
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
