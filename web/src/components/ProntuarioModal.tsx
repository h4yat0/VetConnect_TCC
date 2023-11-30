import { Dialog, Listbox, Transition } from "@headlessui/react";
import { Fragment, useEffect, useRef, useState } from "react";
import { XMarkIcon } from "@heroicons/react/20/solid";
import ButtonPrimary from "./buttons/ButtonPrimary";
import ButtonDanger from "./buttons/ButtonDanger";
import { DatePicker, TimePicker } from "@mui/x-date-pickers";
import {
  getAccessToken,
  getAnimals,
  getId,
  getSchedules,
  updateAnimals,
} from "../redux/client";
import { useDispatch, useSelector } from "react-redux";
import dayjs from "dayjs";
import api from "../api/axios";
import AlertConfirm from "./AlertConfirm";
import { getUnits } from "../redux/unit";
import { useFetcher } from "react-router-dom";

const PRONTUARIO_URL = "/api/prontuario/v1/buscar/";
const ITENS_PRONTUARIO_URL = "/api/itemProntuario/v1/buscar-itens-prontuarios/";

interface ProntuarioModalProps {
  animalId: number;
  isOpen: boolean;
  setIsOpen: (isOpen: boolean) => void;
}

interface Prontuario {
  id: number;
  dataAbertura: string;
  enfermidade: string;
  alergia: string;
  medicamento: string;
}

interface ItemProntuario {
  id: number;
  idProntuario: number;
  data: string;
  sintomas: string;
  examesSolicitados: string;
  diagnostico: string;
  prescricao: string;
  observacoes: string;
}

export default function ProntuarioModal({
  animalId,
  isOpen,
  setIsOpen,
}: ProntuarioModalProps) {
  const accessToken = useSelector(getAccessToken);
  // console.log(accessToken);

  const [prontuario, setProntuario] = useState<Prontuario>();
  const [itensProntuario, setItensProntuario] = useState<ItemProntuario[]>();

  const getProntuario = async () => {
    let response = await api
      .get(`${PRONTUARIO_URL + animalId}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then(function (response) {
        let data = response.data;
        setProntuario(data);      
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  const getItensProntuario = async () => {
    let response = await api
      .get(ITENS_PRONTUARIO_URL + prontuario?.id, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then(function (response) {
        let data = response.data;
        setItensProntuario(data);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  useEffect(() => {
    getProntuario();
  }, [isOpen]);

  useEffect(()=> {
    if (prontuario !== undefined) getItensProntuario();    
  }, [prontuario])
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
                      <span>Prontuário</span>
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
                    <div className="flex gap-10">
                      <div className="flex flex-col gap-10">
                        <div>
                          <h1 className="text-lg font-bold">Alergias</h1>
                          <ul className="list-disc">
                            {prontuario?.alergia !== undefined ? (
                              prontuario?.alergia.split(",").length > 0 ? (
                                prontuario?.alergia.split(",").map((e) => (
                                  <li key={prontuario.id + e} className="ml-8">
                                    {e}
                                  </li>
                                ))
                              ) : (
                                <li>Sem enfermidades registradas</li>
                              )
                            ) : (
                              <li>Sem enfermidades registradas</li>
                            )}
                          </ul>
                        </div>
                        <div>
                          <h1 className="text-lg font-bold">Enfermidades</h1>
                          <ul className="list-disc">
                            {prontuario?.enfermidade !== undefined ? (
                              prontuario?.enfermidade.split(",").length > 0 ? (
                                prontuario?.enfermidade.split(",").map((e) => (
                                  <li key={prontuario.id + e} className="ml-8">
                                    {e}
                                  </li>
                                ))
                              ) : (
                                <li>Sem enfermidades registradas</li>
                              )
                            ) : (
                              <li>Sem enfermidades registradas</li>
                            )}
                          </ul>
                        </div>
                        <div>
                          <h1 className="text-lg font-bold">Medicamentos</h1>
                          <ul className="list-disc">
                            {prontuario?.medicamento !== undefined ? (
                              prontuario?.medicamento.split(",").length > 0 ? (
                                prontuario?.medicamento.split(",").map((e) => (
                                  <li key={prontuario.id + e} className="ml-8">
                                    {e}
                                  </li>
                                ))
                              ) : (
                                <li>Sem medicamentos registrados</li>
                              )
                            ) : (
                              <li>Sem medicamentos registrados</li>
                            )}
                          </ul>
                        </div>
                      </div>
                      <div className="flex flex-col w-full gap-5">
                        
                        {itensProntuario !== undefined &&
                        itensProntuario.length > 0 ? (
                          itensProntuario.map((item) => (
                            <div className="border border-black rounded p-4 w-full" key={item.id}>
         

                              <h1 className="text-lg font-bold">{item.data}</h1>
                              <div className="flex flex-col gap-2">

                              <div className="border border-black rounded p-2">
                                <h2 className="font-bold">Sintomas</h2>
                                <p>{item.sintomas}</p>
                              </div>
                              <div className="border border-black rounded p-2">
                                <h2 className="font-bold">Diagnóstico</h2>
                                <p>{item.diagnostico}</p>
                              </div>

                              <div className="border border-black rounded p-2">
                                <h2 className="font-bold">Prescrição</h2>
                                <p>{item.prescricao}</p>
                              </div>

                              <div className="border border-black rounded p-2">
                                <h2 className="font-bold">Exames Solicitados</h2>
                                <p>{item.examesSolicitados}</p>
                              </div>
                              <div className="border border-black rounded p-2">
                                <h2 className="font-bold">Observações</h2>
                                <p>{item.observacoes}</p>
                              </div>
                              </div>
                             </div>
                          ))
                          ) : (
                             <div className="border border-black rounded p-4 w-full" >
                               <h1>Não há nenhum dado de consultas</h1>
                             </div>
                            )}
                           
                      </div>
                    </div>
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
