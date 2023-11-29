import { TbVaccine } from "react-icons/tb";
import { MdPets } from "react-icons/md";
import { FaShower } from "react-icons/fa";
import { IoCutSharp } from "react-icons/io5";

interface AgendamentosFuncionarios {
  nomeDono: string;
  nomeAnimal: string;
  servico:string;
  porte: string;
  preco: number;
  data: string;
  // handleSchedulingOpen: () => void;
}

export default function FuncionarioCard(props: AgendamentosFuncionarios) {
  const { nomeDono, nomeAnimal, servico, porte, preco, data } = props;

  return (
    <div className="w-full min-w-100 p-4 bg-white flex space-x-4 rounded-lg shadow-md hover:scale-105 transition transform duration-500 cursor-pointer">
      <div className="mt-4 ml-2">Nome do dono:{nomeDono}</div>
      <div className="mt-4 ml-2">Animal:{nomeAnimal}</div>
      <div className="mt-4 ml-2">Tamanho:{porte}</div>
      <div className="mt-4 ml-2">Serviço:{servico}</div>
      <div className="mt-4 ml-2">Preço:{preco}</div>
      <div className="w-full">{data}
        <h1 className="mt-4 ml-2 p-2 text-center text-xl font-bold text-gray-700 mb-4">
        x
        </h1>
      </div>
    </div>
  );
}
