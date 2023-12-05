import { useState, Fragment, useEffect, ChangeEvent } from "react";
import ButtonDanger from "./buttons/ButtonDanger";
import ButtonPrimary from "./buttons/ButtonPrimary";
import { useDispatch, useSelector } from "react-redux";
import { getAccessToken, getId, getName, getAnimals, updateAnimals } from "../redux/client";
import { Dialog, Listbox, Transition } from "@headlessui/react";
import { axiosPrivate } from "../api/axios";
import AlertModal from "./AlertModal";
import { useNavigate } from "react-router-dom";

interface AnimalModalProps {
  type: "register" | "update";
  isOpen: boolean;
  animalsStore: Animal[];
  animalId: number;
  setIsOpen: (isOpen: boolean) => void;
}

interface Animal {
  id: number;
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
const ANIMALS_URL = "/api/animal/v1/buscar/";

const sex = ["M", "F"];

export default function AnimalModal({
  type,
  isOpen,
  animalsStore,
  animalId,
  setIsOpen,
}: AnimalModalProps) {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  
  const [alertType, setAlertType] = useState<"success" | "caution" | "danger">(
    "success"
  );
  const [alertMessage, setAlertMessage] = useState("");
  const [alertIsOpen, setAlertIsOpen] = useState(false);

  // cosnt animals = [...animalsStore];
  
  const animals = [...useSelector(getAnimals)];
  const currentAnimal = animals.findIndex((animal) => animal.id === animalId);

  const [name, setName] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [color, setColor] = useState("");
  const [race, setRace] = useState("");
  const [weigth, setWeigth] = useState("");
  const [size, setSize] = useState("");
  const [specie, setSpecie] = useState("");
  const [selectedSex, setSelectedSex] = useState(sex[0]);
  const [img, setImg] = useState("");
  const [file, setFile] = useState<File | null>(null);
  const [base64Image, setBase64Image] = useState<string>("");

  const id = useSelector(getId);
  const accessToken = useSelector(getAccessToken);

  useEffect(() => {
    if (animalId !== -1) {
      setName(animals[currentAnimal].name);
      setBirthDate(animals[currentAnimal].birthDate);
      setColor(animals[currentAnimal].color);
      setRace(animals[currentAnimal].race);
      setWeigth(animals[currentAnimal].weigth);
      setSize(animals[currentAnimal].size);
      setSpecie(animals[currentAnimal].specie);
      setSelectedSex(sex[animals[currentAnimal].sex == "M" ? 0 : 1]);
    }
  }, [currentAnimal]);

  const validateInput = (input: string): string => {
    const sanitizedInput = input.replace(/[^0-9.]/g, "");

    // Garanta que há apenas uma vírgula
    const indexOfDot = sanitizedInput.indexOf(".");

    if (indexOfDot !== -1) {
      const afterComma = sanitizedInput.slice(indexOfDot + 1);

      if (afterComma.includes(".")) {
        return sanitizedInput.slice(0, indexOfDot + 1);
      }
    }
    return sanitizedInput;
  };

  const handleWeightChange = (event: ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value;
    const sanitizedInput = validateInput(inputValue);
    setWeigth(sanitizedInput);
  };

  const handleSizeChange = (event: ChangeEvent<HTMLInputElement>) => {
    const inputValue = event.target.value;
    const sanitizedInput = validateInput(inputValue);
    setSize(sanitizedInput);
  };

  function isEmptyString(str: string) {
    return str.trim() === "";
  }

  function handleImgChange(e: ChangeEvent<HTMLInputElement>) {
    const files = e.target.files;

    if (files && files.length > 0) {
      const selectedFile = files[0];
      setImg(URL.createObjectURL(files[0]));

      setFile(selectedFile);
      const reader = new FileReader();

      reader.onload = (event) => {
        // 'event.target.result' contains the base64 representation of the image
        if (typeof event.target?.result === "string") {
          setBase64Image(event.target.result);
        }
      };

      reader.readAsDataURL(selectedFile);
    }
  }

  function formatBase64(base64: string) {
    const commaIndex = base64.indexOf(",");
    return [base64.substring(commaIndex + 1).trim()];
  }

  const getAnimalsApi = async () => {
    let response = await axiosPrivate
      .get(ANIMALS_URL + id, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then(function (response) {
        let data = response.data;
        dispatch(updateAnimals(data));
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  const postAnimal = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    let reponse = await axiosPrivate
      .post(
        ANIMALRECORD_URL,
        {
          idCliente: id,
          nome: name,
          cor: color,
          raca: race,
          dataNascimento: birthDate,
          peso: weigth,
          tamanho: size,
          especie: specie,
          sexo: selectedSex,
          imagens: formatBase64(base64Image),
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      )
      .then(function (response) {
        let data = response.data;

        getAnimalsApi();
        setAlertMessage("Cadastro bem sucedido");
        setAlertType("success");
        setAlertIsOpen(true);
        setIsOpen(false);
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
    let reponse = await axiosPrivate
      .put(
        `api/animal/v1/alterar/${animalId}`,
        {
          idCliente: id,
          nome: isEmptyString(name) ? animals[animalId]?.name : name,
          cor: isEmptyString(color) ? animals[animalId]?.color : color,
          raca: isEmptyString(race) ? animals[animalId]?.race : race,
          dataNascimento: isEmptyString(birthDate)
            ? animals[animalId]?.birthDate
            : birthDate,
          peso: isEmptyString(weigth) ? animals[animalId]?.weigth : weigth,
          tamanho: isEmptyString(size) ? animals[animalId]?.size : size,
          especie: isEmptyString(specie) ? animals[animalId]?.specie : specie,
          sexo: sex ? animals[animalId]?.sex : sex,
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
        getAnimalsApi();
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
    let reponse = await axiosPrivate
      .delete(`api/animal/v1/delete/${animalId}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then(function (response) {
        getAnimalsApi();
        setAlertMessage("Exclusão bem sucedido");
        setAlertType("success");
        setAlertIsOpen(true);

        setIsOpen(false);

        if (animals.length == 0) navigate("user/client");

        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <>
      <AlertModal
        type={alertType}
        isOpen={alertIsOpen}
        setIsOpen={setAlertIsOpen}
        message={alertMessage}
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
                      ? "Cadastre seu animalzinho"
                      : "Edite as informações do seu animalzinho"}
                  </Dialog.Title>
                  <div className="px-2 py-3 border-2 rounded-lg ">
                    <h1 className="font-inter font-black text-2xl w-full text-center pb-5 uppercase">
                      {type == "register"
                        ? "Cadastre seu animalzinho"
                        : "Edite as informações do seu animalzinho"}
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

                    <form className="space-y-5" onSubmit={postAnimal} method="POST">
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
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>

                      <div>
                        <label
                          htmlFor="weigth"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Peso (kg)
                        </label>
                        <div className="mt-2">
                          <input
                            id="weigth"
                            name="weigth"
                            type="text"
                            autoComplete="weigth"
                            required
                            value={weigth}
                            onChange={(e) => handleWeightChange(e)}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>

                      <div>
                        <label
                          htmlFor="size"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Tamanho (cm)
                        </label>
                        <div className="mt-2">
                          <input
                            id="size"
                            name="size"
                            type="text"
                            autoComplete="size"
                            required
                            value={size}
                            onChange={(e) => handleSizeChange(e)}
                            className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                          />
                        </div>
                      </div>
                      <div>
                        <label className="block text-sm font-medium leading-6 text-gray-900">
                          Sexo
                        </label>
                        <Listbox value={selectedSex} onChange={setSelectedSex}>
                          <div className="relative">
                            <Listbox.Button className="relative w-full min-h-[56px] p-[18px] cursor-pointer rounded text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-vetConnectPrimaryGreen sm:text-sm">
                              <span className="block truncate">{selectedSex}</span>
                            </Listbox.Button>
                            <Transition
                              as={Fragment}
                              leave="transition ease-in duration-100"
                              leaveFrom="opacity-100"
                              leaveTo="opacity-0"
                            >
                              <Listbox.Options className="absolute max-h-44 z-50 mt-1 cursor-pointer w-full overflow-auto rounded py-1 bg-white text-base shadow-lg focus:outline-none sm:text-sm">
                                {sex.map((sex, i) => (
                                  <Listbox.Option
                                    key={i}
                                    className={({ active }) =>
                                      `relative cursor-default select-none py-2 pl-10 pr-4 ${
                                        active
                                          ? "text-vetConnectPrimaryGreen font-extrabold"
                                          : "text-gray-900"
                                      }`
                                    }
                                    value={sex}
                                  >
                                    {({ selected }) => (
                                      <>
                                        <span
                                          className={`block truncate ${
                                            selected ? "font-medium" : "font-normal"
                                          }`}
                                        >
                                          {sex}
                                        </span>
                                      </>
                                    )}
                                  </Listbox.Option>
                                ))}
                              </Listbox.Options>
                            </Transition>
                          </div>
                        </Listbox>
                      </div>

                      <div>
                        <label
                          htmlFor="img"
                          className="block text-sm font-medium leading-6 text-gray-900"
                        >
                          Faça o upload de uma imagem do seu animalzinho
                        </label>
                        <div className="mt-2">
                          <input
                            id="img"
                            name="img"
                            type="file"
                            onChange={(e) => handleImgChange(e)}
                            maxLength={1}
                            className="block w-full border border-gray-300 rounded-md file:rounded file:bg-vetConnectGray file:text-white file:p-2 ring-0 text-gray-900 shadow-sm placeholder:text-gray-400 sm:text-sm sm:leading-6"
                          />

                          {img && (
                            <img
                              src={img}
                              alt="Imagem selecionada"
                              className="mt-1 rounded"
                            />
                          )}
                        </div>
                      </div>

                      {type == "update" ? (
                        <>
                          <div className="flex flex-col gap-1">
                            <ButtonPrimary
                              text="Alterar dados do animal"
                              width="w-full"
                              onClickFunction={postEditedInformation}
                            />
                            <ButtonDanger
                              text="Excluir animal"
                              onClickFunction={deleteAnimal}
                              width="w-full"
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
