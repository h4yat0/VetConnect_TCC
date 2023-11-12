import { useEffect, useState } from "react";
import Cachorro from "../assets/imgs/Cachorro.jpg";
import AgendamentoModal from "../components/SchedulingModal";
import RegisterAnimalModal from "../components/AnimalModal";
import ButtonPrimary from "../components/buttons/ButtonPrimary";
import { getAnimals } from "../redux/client";
import { useSelector } from "react-redux";
import { GoChevronLeft, GoChevronRight } from "react-icons/go";

export default function Animals() {
  const [isOpen, setIsOpen] = useState(false);
  const [currentAnimal, setCurrentAnimal] = useState(0);

  let animals = useSelector(getAnimals);
  animals = [...animals];

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
      <RegisterAnimalModal
        type="update"
        animalsStore={animals}
        animalId={animals[currentAnimal].id}
        isOpen={isOpen}
        setIsOpen={setIsOpen}
      />
      <div className="pt-4 font-inter rounded-xl px-10">
        <div className="flex justify-between py-2">
          <h1 className="font-bold my-2">
            {animals[currentAnimal].name} - {animals[currentAnimal].race} -{" "}
            {calculateAge(animals[currentAnimal].birthDate)}
          </h1>
          <ButtonPrimary
            text="Editar Informações"
            onClickFunction={() => setIsOpen(!isOpen)}
          />
        </div>
        <div className="flex items-center justify-center">
          <GoChevronLeft
            className="mr-5 h-8 w-8 color text-gray-600 cursor-pointer"
            onClick={() => decrementCurrentAnimal()}
          />

          <div className="grid gap-4 grid-cols-3  " style={{ maxHeight: 400 }}>
            <div className="col-span-2">
              <img
                src={Cachorro}
                alt="tailwind logo"
                className="rounded-xl w-full h-2/3 object-cover"
              />
            </div>
            <div className="flex justify-center  bg-vetConnectSecundaryGreen h-2/3 rounded-xl">
              <div className="flex items-center justify-center font-bold">
                Prontuário
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
          <h1 className="font-bold m-2">Histórico de Serviços</h1>
          <div className=" rounded-xl px-4 ">
            <div className="grid gap-4 grid-cols-3 h-2/3">
              <div className=" col-span-2">Tosa - Unidade Centro Jundiaí</div>
              <div className="flex justify-center  h-2/3">21 Jan</div>
            </div>

            <div className="grid gap-4 grid-cols-3 h-2/3">
              <div className=" col-span-2">Banho - Unidade Centro Jundiaí</div>
              <div className="flex justify-center  h-2/3">21 Jan</div>
            </div>

            <div className="grid gap-4 grid-cols-3 h-2/3">
              <div className=" col-span-2">Castração - Unidade Retiro</div>
              <div className="flex justify-center  h-2/3">30 Jan</div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
