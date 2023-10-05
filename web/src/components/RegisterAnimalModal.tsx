import Cleave from "cleave.js";
import { useEffect, useState, Fragment } from "react";
import ButtonDanger from "./buttons/ButtonDanger";
import ButtonPrimary from "./buttons/ButtonPrimary";
import ButtonSecundary from "./buttons/ButtonSecundary";
import { useDispatch, useSelector } from "react-redux";
import { getAccessToken, getId, getName } from "../redux/client";
import { useNavigate } from "react-router-dom";
import { Dialog, Transition } from "@headlessui/react";
import api from "../api/axios";

interface RegisterAnimalModalProps {
  isOpen: boolean;
  setIsOpen: (isOpen: boolean) => void;
}

const ANIMALRECORD_URL = "api/animal/v1/cadastro";

export default function RegisterAnimalModal({ isOpen, setIsOpen }: RegisterAnimalModalProps) {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [editDisabled, setEditDisabled] = useState(false);

  const handleSetEditEnabled = () => {
    setEditDisabled(!editDisabled);
  };

  function closeModal() {
    setIsOpen(false);
  }

  function openModal() {
    setIsOpen(true);
  }

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

  const postAnimalRecord = async (e: React.FormEvent<HTMLFormElement>) => {
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
                    Cadastre seu animalzinho
                  </Dialog.Title>
                  <div className="mx-4  px-2 py-10 border-2 rounded-lg ">
                    <h1 className="font-inter font-black text-2xl w-full text-center pb-5 uppercase">
                      Cadastre seu animalzinho
                    </h1>

                    {false ? (
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
                    )}

                    <form
                      className="space-y-6"
                      onSubmit={postAnimalRecord}
                      method="POST"
                    >
                      <div>
                        <label
                          htmlFor="animalName"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Nome
                        </label>
                        <div className="mt-2">
                          <input
                            id="animalName"
                            name="animalName"
                            type="text"
                            required
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            disabled={editDisabled}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>

                      <div>
                        <label
                          htmlFor="animalBirthDate"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Data de Nascimento
                        </label>
                        <div className="mt-2">
                          <input
                            id="animalBirthDate"
                            name="animalBirthDate"
                            type="date"
                            required
                            value={birthDate}
                            onChange={(e) => setBirthDate(e.target.value)}
                            disabled={editDisabled}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>

                      <div>
                        <label
                          htmlFor="specie"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Espécie
                        </label>
                        <div className="mt-2">
                          <input
                            id="specie"
                            name="specie"
                            type="text"
                            required
                            value={specie}
                            onChange={(e) => setSpecie(e.target.value)}
                            disabled={editDisabled}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>

                      <div>
                        <label
                          htmlFor="race"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Raça
                        </label>
                        <div className="mt-2">
                          <input
                            id="race"
                            name="race"
                            type="text"
                            autoComplete="race"
                            required
                            value={race}
                            onChange={(e) => setRace(e.target.value)}
                            disabled={editDisabled}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>

                      <div>
                        <label
                          htmlFor="color"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Cor
                        </label>
                        <div className="mt-2">
                          <input
                            id="color"
                            name="color"
                            type="text"
                            required
                            value={color}
                            onChange={(e) => setColor(e.target.value)}
                            disabled={editDisabled}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>

                      <div>
                        <label
                          htmlFor="weigth"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Peso
                        </label>
                        <div className="mt-2">
                          <input
                            id="weigth"
                            name="weigth"
                            type="text"
                            autoComplete="weigth"
                            required
                            value={weigth}
                            onChange={(e) => setWeigth(e.target.value)}
                            disabled={editDisabled}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>

                      <div>
                        <label
                          htmlFor="size"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Tamanho
                        </label>
                        <div className="mt-2">
                          <input
                            id="size"
                            name="size"
                            type="text"
                            autoComplete="size"
                            required
                            value={size}
                            onChange={(e) => setSize(e.target.value)}
                            disabled={editDisabled}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>

                      <div>
                        <label
                          htmlFor="sex"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Sexo
                        </label>
                        <div className="mt-2">
                          <input
                            id="sex"
                            name="sex"
                            type="text"
                            required
                            value={sex}
                            onChange={(e) => setSex(e.target.value)}
                            disabled={editDisabled}
                            maxLength={1}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>
                      {false ? (
                        <>
                          <div>
                            <ButtonPrimary
                              text="Alterar dados"
                              width="w-full"
                              disabled={editDisabled}
                              // onClickFunction={postEditedInformation}
                            />
                          </div>
                          <div>
                            <ButtonDanger
                              text="Deletar conta"
                              disabled={editDisabled}
                              // onClickFunction={deleteAccount}
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
