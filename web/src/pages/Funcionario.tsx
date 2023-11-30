import { useEffect, useState } from "react";
import Cachorro from "../assets/imgs/Cachorro.jpg";
import ScheduleModal from "../components/SchedulingModal";
import AnimalModal from "../components/AnimalModal";
import ButtonPrimary from "../components/buttons/ButtonPrimary";

import { useSelector } from "react-redux";
import { GoChevronLeft, GoChevronRight } from "react-icons/go";
import { useNavigate } from "react-router-dom";
import FuncionarioCard from "../components/FuncionarioCard";

export default function Funcionario() {
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);

  var funcionarioAgendamento = [
    {
      nomeDono: "Nome do Dono 1",
      nomeAnimal: "Nome do Animal 1",
      servico: "Serviço 1",
      porte: "Porte 1",
      preco: 50.0,
      data: "2023-11-29",
    },
    {
      nomeDono: "Nome do Dono 2",
      nomeAnimal: "Nome do Animal 2",
      servico: "Serviço 2",
      porte: "Porte 2",
      preco: 75.0,
      data: "2023-11-30",
    },
    {
      nomeDono: "Nome do Dono 3",
      nomeAnimal: "Nome do Animal 3",
      servico: "Serviço 3",
      porte: "Porte 3",
      preco: 100.0,
      data: "2023-12-01",
    },
  ];

  return (
    <>
      <div>
        <h1 className="font-bold m-2">Agendamentos</h1>
      </div>

      <div>
        <div>
          <div>Pendentes</div>
          <div>Em atendimento</div>
          <div>Concluído</div>
        </div>

        <div>
          {funcionarioAgendamento.map((Agendamento: any) => (
            <FuncionarioCard
              nomeDono={Agendamento.nomeDono}
              nomeAnimal={Agendamento.nomeAnimal}
              servico={Agendamento.servico}
              porte={Agendamento.porte}
              preco={Agendamento.preco}
              data={Agendamento.data}
            />
          ))}
        </div>
      </div>
    </>
  );
}
