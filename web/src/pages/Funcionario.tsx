import { useEffect, useState } from "react";
import Cachorro from "../assets/imgs/Cachorro.jpg";
import ScheduleModal from "../components/SchedulingModal";
import AnimalModal from "../components/AnimalModal";
import ButtonPrimary from "../components/buttons/ButtonPrimary";

import { useSelector } from "react-redux";
import { GoChevronLeft, GoChevronRight } from "react-icons/go";
import { useNavigate } from "react-router-dom";
import FuncionarioCard from "../components/ScheduleFuncionarioCard";

const SCHEDULES_URL = "/api/agendamento/v1/buscar-por-id-funcionario/";

export default function Funcionario() {
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);

  // const getSchedulesApi = async () => {
  //   let response = await api
  //     .get(SCHEDULES_URL + clientId, {
  //       headers: {
  //         Authorization: `Bearer ${accessToken}`,
  //       },
  //     })
  //     .then(function (response) {
  //       let data = response.data;
  //       dispatch(updateSchedules(data));
  //     })
  //     .catch(function (error) {
  //       console.log(error);
  //     });
  // };

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
        <h1 className="text-2xl font-bold m-2">Agendamentos</h1>
      </div>

      <div className="flex flex-col justify-center items-center">
        <div className="flex gap-5">
          <button className="text-xl border-b-2 focus:border-vetConnectPrimaryGreen">
            Pendentes
          </button>
          <button className="text-xl border-b-2 focus:border-vetConnectPrimaryGreen">
            Concluídos
          </button>
          <button className="text-xl border-b-2 focus:border-vetConnectPrimaryGreen">
            Cancelados
          </button>
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
