import { useEffect, useState } from "react";
import Cachorro from "../assets/imgs/Cachorro.jpg";
import SchedulingModal from "../components/SchedulingModal";
import AnimalModal from "../components/AnimalModal";
import ButtonPrimary from "../components/buttons/ButtonPrimary";
import { getFuncionario } from "../redux/client";
import { useSelector } from "react-redux";
import { GoChevronLeft, GoChevronRight } from "react-icons/go";
import { useNavigate } from "react-router-dom";

export default function CadastroFuncionario() {
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const [currentAnimal, setCurrentAnimal] = useState(0);

  let Funcionario = useSelector(getFuncionario);
  Funcionario = [...Funcionario];

  useEffect(()=> {
    if (Funcionario.length == 0) navigate("user/client");
  },[])


  function incrementCurrentAnimal() {
    if (currentAnimal < Funcionario.length - 1) {
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
      <div>
        <h1 className="font-bold m-2">Cadastro de Funcionários</h1>
      </div>
    </>
  );
}
