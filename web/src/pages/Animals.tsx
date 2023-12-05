import { useEffect, useState } from "react";
import Cachorro from "../assets/imgs/Cachorro.jpg";
import ScheduleModal from "../components/SchedulingModal";
import AnimalModal from "../components/AnimalModal";
import ButtonPrimary from "../components/buttons/ButtonPrimary";
import { getAnimals, getSchedules } from "../redux/client";
import { useSelector } from "react-redux";
import { GoChevronLeft, GoChevronRight } from "react-icons/go";
import { useNavigate } from "react-router-dom";
import ButtonSecundary from "../components/buttons/ButtonSecundary";
import ProntuarioModal from "../components/ProntuarioModal";

export default function Animals() {
  const navigate = useNavigate();

  const [animalModalIsOpen, setAnimalModalIsOpen] = useState(false);
  const [prontuarioModalIsOpen, setProntuarioModalIsOpen] = useState(false);

  const [currentAnimal, setCurrentAnimal] = useState(0);

  const animals = [...useSelector(getAnimals)];
  const schedules = [...useSelector(getSchedules)].filter(
    (schedule) => schedule.animal === animals[currentAnimal].name && schedule.status != 1
  );

  useEffect(() => {
    if (animals.length == 0 && animals[0].id !== -1 ) navigate("user/client");
  }, []);

  function incrementCurrentAnimal() {
    if (currentAnimal < animals.length - 1) {
      setCurrentAnimal(currentAnimal + 1);
    }
  }

  function decrementCurrentAnimal() {
    if (currentAnimal > 0) {
      setCurrentAnimal(currentAnimal - 1);
    }
  }

  function calculateAge(dateOfBirth: string): number {
    const today = new Date();
    const birthdayDate = new Date(dateOfBirth);
    let age = today.getFullYear() - birthdayDate.getFullYear();

    // Check if the birthday has already occurred this year
    if (
      today <
      new Date(today.getFullYear(), birthdayDate.getMonth(), birthdayDate.getDate())
    ) {
      age--;
    }

    return age;
  }

  return (
    <>
      <AnimalModal
        type="update"
        animalsStore={animals}
        animalId={animals[currentAnimal].id}
        isOpen={animalModalIsOpen}
        setIsOpen={setAnimalModalIsOpen}
      />
      <ProntuarioModal
        isOpen={prontuarioModalIsOpen}
        setIsOpen={setProntuarioModalIsOpen}
        animalId={animals[currentAnimal].id}
      />
      <div className="pt-4 font-inter rounded-xl px-10">
        <div className="flex items-center justify-center">
          <GoChevronLeft
            className="mr-5 h-8 w-8 color text-gray-600 cursor-pointer"
            onClick={() => decrementCurrentAnimal()}
          />

          <div className="grid gap-4 grid-cols-2 w-full lg:grid-cols-3">
            <div className="col-span-2">
              <div className="w-full h-full overflow-hidden max-h-[350px]">
                {animals[currentAnimal].imgs[0].length > 0 &&
                animals[currentAnimal].imgs[0] !== undefined ? (
                  <img
                    src={`data:image/jpg;base64,${animals[currentAnimal].imgs[0]}`}
                    alt="tailwind logo"
                    className="rounded-xl w-full h-full object-cover"
                  />
                ) : (
                  <div className="rounded-xl w-full h-full min-h-[300px] bg-vetConnectGray flex justify-center items-center">
                    <span className="text-white">Sem imagem</span>
                  </div>
                )}
              </div>
            </div>
            <div className="p-4 rounded-lg border-vetConnectSecundaryGreen max-h-[350px]">
              <h1 className="text-3xl font-bold">{animals[currentAnimal].name}</h1>
              <div className="pt-4 gap-y-6 flex flex-col">
                <div className="text-lg">
                  <p>
                    <b>Idade:</b>{" "}
                    {calculateAge(animals[currentAnimal].birthDate) == 1
                      ? `${calculateAge(animals[currentAnimal].birthDate)} Ano`
                      : `${calculateAge(animals[currentAnimal].birthDate)} Anos`}
                  </p>
                  <p>
                    <b>Raça:</b> {animals[currentAnimal].race}
                  </p>
                  <p>
                    <b>Cor:</b> {animals[currentAnimal].color}
                  </p>
                  <p>
                    <b>Peso:</b>{" "}
                    {parseInt(animals[currentAnimal].weigth)
                      .toFixed(2)
                      .replace(".", ",")}
                    kg
                  </p>
                  <p>
                    <b>Tamanho:</b>{" "}
                    {parseInt(animals[currentAnimal].size)
                      .toFixed(2)
                      .replace(".", ",")}
                    cm
                  </p>
                  <p>
                    <b>Sexo:</b>{" "}
                    {animals[currentAnimal].sex == "M" ? "Macho" : "Fêmea"}
                  </p>
                  <div className="pt-6 flex flex-col gap-1">
                    <ButtonPrimary
                      text="Editar Informações"
                      onClickFunction={() =>
                        setAnimalModalIsOpen(!animalModalIsOpen)
                      }
                      width="w-full"
                    />
                    <ButtonSecundary
                      text="Prontuário"
                      onClickFunction={() =>
                        setProntuarioModalIsOpen(!prontuarioModalIsOpen)
                      }
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>

          <GoChevronRight
            className="ml-5 h-8 w-8 color text-gray-600 cursor-pointer"
            onClick={() => incrementCurrentAnimal()}
          />
        </div>

        {/* Histórico de Serviços */}
        <div>
          <h1 className="font-bold text-2xl m-2">Histórico de Serviços</h1>
          <div className=" rounded-xl px-4 ">
            {schedules.length > 0 ? (
              schedules.map((schedule) => (
                <div className="flex justify-between" key={schedule.id}>
                  <div className="flex items-center justify-center">
                    <div
                      className={`h-5 w-5 rounded-full mr-4 ${
                        schedule.status == 3
                          ? "bg-red-400"
                          : schedule.status == 2
                          ? "bg-vetConnectSecundaryGreen"
                          : "bg-white border border-black"
                      }`}
                    />
                    <div className=" col-span-2">
                      {schedule.service} - {schedule.unit}
                    </div>
                  </div>
                  <div className="flex justify-center  h-2/3">
                    {schedule.scheduledDate}
                  </div>
                </div>
              ))
            ) : (
              <div>Sem serviços prévios</div>
            )}
          </div>
        </div>
      </div>
    </>
  );
}
