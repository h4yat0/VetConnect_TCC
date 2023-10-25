import { useState } from "react";
import Cachorro from "../assets/imgs/Cachorro.jpg";
import AgendamentoModal from "../components/AgendamentoModal";
import RegisterAnimalModal from "../components/AnimalModal";
import ButtonPrimary from "../components/buttons/ButtonPrimary";
import { getAnimals } from "../redux/client";
import { useSelector } from "react-redux";

export default function Animals() {
  const [isOpen, setIsOpen] = useState(false);
  const animals = useSelector(getAnimals);

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
        animalId={animals[0].id}
        isOpen={isOpen}
        setIsOpen={setIsOpen}
      />
      <div className="pt-4 font-inter rounded-xl px-10">
        <div className="flex justify-between py-2">
          <h1 className="font-bold my-2">
            {animals[0].name} - {animals[0].race} -{" "}
            {calculateAge(animals[0].birthDate)}
          </h1>
          <ButtonPrimary
            text="Editar Informações"
            onClickFunction={() => setIsOpen(!isOpen)}
          />
        </div>

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
