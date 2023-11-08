import { useEffect, useState, Fragment } from "react";
import ButtonDanger from "./buttons/ButtonDanger";
import ButtonPrimary from "./buttons/ButtonPrimary";
import ButtonSecundary from "./buttons/ButtonSecundary";
import { useDispatch, useSelector } from "react-redux";
import {
  getAccessToken,
  getAnimals,
  getId,
  getName,
  updateAnimals,
} from "../redux/client";
import { useNavigate } from "react-router-dom";
import { Dialog, Transition } from "@headlessui/react";
import api from "../api/axios";
import AlertModal from "./shared/AlertModal";
import { router } from "../routes/routes";

interface AnimalModalProps {
  type: "register" | "update";
  isOpen: boolean;
  animalsStore: Animal[];
  animalId: number;
  setIsOpen: (isOpen: boolean) => void;
}

interface Animal {
  id: number
  clientId: number;
  name: string;
  color: string;
  race: string;
  birthDate: string;
  weigth: string;
  size: string;
  specie: string;
  sex: string;
}

const ANIMALRECORD_URL = "api/animal/v1/cadastro";


export default function AgendamentoModal({
  type,
  isOpen,
  animalsStore,
  animalId,
  setIsOpen,
}: AnimalModalProps) {
  const dispatch = useDispatch();

  let animals = [...animalsStore]

  const [name, setName] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [color, setColor] = useState("");
  const [race, setRace] = useState("");
  const [weigth, setWeigth] = useState("");
  const [size, setSize] = useState("");
  const [specie, setSpecie] = useState("");
  const [sex, setSex] = useState("");

  const idStore = useSelector(getId);
  const nameStore = useSelector(getName);
  const accessToken = useSelector(getAccessToken);

  function isEmptyString(str: string) {
    return str.trim() === "";
  }
function removeAnimalById(animals: Animal[], idToRemove: number): Animal[] {
  return animals.filter((animal) => animal.id !== idToRemove);
}

  const postAnimal = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    let reponse = await api
      .post(
        ANIMALRECORD_URL,
        {
          idCliente: idStore,
          nome: name,
          cor: color,
          raca: race,
          dataNascimento: birthDate,
          peso: weigth,
          tamanho: size,
          especie: specie,
          sexo: sex,
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      )
      .then(function (response) {
        let data = response.data;
        console.log(data);
      })
      .catch(function (error) {
        console.log(error);
        if (!error?.message) {
          // setErrorMessage("No server response");
        } else {
          // setErrorMessage(error.message);
        }
        // errRef.current?.focus();
      });
  };

  const postEditedInformation = async (e?: React.FormEvent<HTMLFormElement>) => {
    if (e) {
      e.preventDefault();
    }
    let reponse = await api
      .put(
        `api/animal/v1/alterar/${animalId}`,
        {
          idCliente: idStore,
          nome: isEmptyString(name) ? animals[animalId]?.name : name,
          cor: isEmptyString(color) ? animals[animalId]?.color : color,
          raca: isEmptyString(race) ? animals[animalId]?.race : race,
          dataNascimento: isEmptyString(birthDate)
            ? animals[animalId]?.birthDate
            : birthDate,
          peso: isEmptyString(weigth) ? animals[animalId]?.weigth : weigth,
          tamanho: isEmptyString(size) ? animals[animalId]?.size : size,
          especie: isEmptyString(specie) ? animals[animalId]?.specie : specie,
          sexo: isEmptyString(sex) ? animals[animalId]?.sex : sex,
          imagens: [""],
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      )
      .then(function (response) {
        let data = response.data;

        animals[animalId] = {
          id: data.id,
          clientId: data.idCliente,
          name: data.nome,
          color: data.cor,
          race: data.raca,
          birthDate: data.dataNascimento,
          weigth: data.peso,
          size: data.tamanho,
          specie: data.especie,
          sex: data.sexo,
        };
        dispatch(updateAnimals(animals));

        router.push({pathname: ''})
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  const deleteAnimal = async (e?: React.FormEvent<HTMLFormElement>) => {
    if (e) {
      e.preventDefault();
    }
    let reponse = await api
      .delete(`api/animal/v1/delete/${animalId}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then(function (response) {
        removeAnimalById(animals, animalId);
        dispatch(updateAnimals(animals))
        
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  


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
            <div className="fixed inset-0 bg-black bg-opacity-25" />
          </Transition.Child>

          <div className="fixed inset-0 overflow-y-auto">
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
                <Dialog.Panel className="w-full max-w-md transform overflow-hidden rounded-2xl bg-white p-6 text-left align-middle shadow-xl transition-all">
                  <Dialog.Title
                    as="h3"
                    className="text-lg font-medium leading-6 text-gray-900 hidden"
                  >
                    {type == "register"
                      ? "Faça seu agendamento"
                      : "Faça seu agendamento"}
                  </Dialog.Title>
                  <div className="px-2 py-10 border-2 rounded-lg ">
                    <h1 className="font-inter font-black text-2xl w-full text-center pb-5 uppercase">
                      {type == "register"
                        ? "Faça seu agendamento"
                        : "Faça seu agendamento"}
                    </h1>

                    {/* {type == "update" ? (
                      <div className="flex justify-between items-center mb-8">
                        <div className="flex justify-between items-center gap-4">
                          <div className="w-10 h-10 bg-slate-800 rounded-full"></div>
                          <span className="font-bold text-xl">{nameStore}</span>
                        </div>
                        <ButtonSecundary
                          text="Editar informações"
                          onClickFunction={handleSetEditEnabled}
                        />
                      </div>
                    ) : (
                      <></>
                    )} */}

                    <form className="space-y-6" onSubmit={postAnimal} method="POST">
                    <div>
                        <label
                          htmlFor="animalName"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Selecione o funcionário
                        </label>
                        <div className="mt-2">
                          <input
                            id="animalName"
                            name="animalName"
                            type="text"
                            required
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>
                      <div>
                        <label
                          htmlFor="animalBirthDate"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Data de Agendamento
                        </label>
                        <div className="mt-2">
                          <input
                            id="animalBirthDate"
                            name="animalBirthDate"
                            type="date"
                            required
                            value={birthDate}
                            onChange={(e) => setBirthDate(e.target.value)}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>
                      <div className="w-full rounded border flex-col justify-start items-start gap-3 inline-flex">
              <div className="w-full text-black text-xl font-extrabold font-['Inter']">
                Valor: R$ 20,00{" "}
              </div>
            </div>
                      {type == "update" ? (
                        <>
                          <div>
                            <ButtonPrimary
                              text="Agendar"
                              width="w-full"
                              onClickFunction={postEditedInformation}
                            />
                          </div>
                          <div>
                            <ButtonDanger
                              text="Cancelar"
                              onClickFunction={deleteAnimal}
                            />
                          </div>
                        </>
                      ) : (
                        <ButtonPrimary
                          text="Cadastrar"
                          width="w-full"
                          type="submit"
                        />
                      )}
                    </form>
                  </div>
                  {/* BOTÃO DE FECHAR */}
                  {/* <div className="mt-4">
                    <button
                      type="button"
                      className="inline-flex justify-center rounded-md border border-transparent bg-blue-100 px-4 py-2 text-sm font-medium text-blue-900 hover:bg-blue-200 focus:outline-none focus-visible:ring-2 focus-visible:ring-blue-500 focus-visible:ring-offset-2"
                      onClick={() => setIsOpen(!isOpen)}
                    >
                      Got it, thanks!
                    </button>
                  </div> */}
                </Dialog.Panel>
              </Transition.Child>
            </div>
          </div>
        </Dialog>
      </Transition>
    </>
  );
}

  
  




