import { TbVaccine } from "react-icons/tb";
import { MdPets } from "react-icons/md";
import { FaShower } from "react-icons/fa";
import { IoCutSharp } from "react-icons/io5";

interface ScheduleFuncionarioCardProps {
  nomeDono: string;
  nomeAnimal: string;
  servico: string;
  porte: string;
  preco: number;
  data: string;
  // handleSchedulingOpen: () => void;
}

export default function ScheduleFuncionarioCard(props: ScheduleFuncionarioCardProps) {
  const { nomeDono, nomeAnimal, servico, porte, preco, data } = props;

  return (
    <div className="mr-2 w-full grid grid-cols-3 gap-4 border border-gray-500 mb-4 mt-4 min-w-100 p-4 bg-white space-x-2 rounded-lg shadow-md hover:scale-105 transition transform duration-500 cursor-pointer">
      <div className="ml-2 col-span-2">
        <div>
          <span className="font-bold text-sm">Nome do dono: </span>
          {nomeDono}
        </div>
        <div>
          <span className="font-bold text-sm">Animal: </span>
          {nomeAnimal}
        </div>
        <div>
          <span className="font-bold text-sm">Tamanho: </span>
          {porte}
        </div>
        <div>
          <span className="font-bold text-sm">Serviço: </span>
          {servico}
        </div>
      </div>
      <div className="mr-4 text-end  pl-11 grid grid-cols-2 gap-2 justify-center  ">
        <div className="..."></div>
        <div className="... mr-2">{data}</div>
        <div className="col-span-2 ..."> </div>
        <div className="..."></div>
        <div className="..."></div>
        <div className="col-span-2 ..."></div>
        <div className="..."></div>
        <div className="..."></div>
        <div className="col-span-2  mr-2  ... ">
          {" "}
          <span className="font-bold text-sm">Preço:</span> {preco}
        </div>
      </div>
    </div>
  );
}
