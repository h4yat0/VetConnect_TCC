import { TbVaccine } from "react-icons/tb";
import { MdPets } from "react-icons/md";
import { FaShower } from "react-icons/fa";
import { IoCutSharp } from "react-icons/io5";

interface ServiceCardProps {
  title: string;
  serviceId?: number | null;
  handleSchedulingOpen: () => void;
}

function iconSelect(title: string): any {
  switch (title) {
    case "Banho":
      return <FaShower className="h-10 w-10" />;
    case "Tosa":
      return <IoCutSharp className="h-10 w-10" />;
    case "Castração":
      return <MdPets className="h-10 w-10" />;
    case "Vacinação":
      return <TbVaccine className="h-10 w-10" />;
    default:
      return <MdPets className="h-10 w-10" />;
  }
}

export default function ServiceCard(props: ServiceCardProps) {
  const { title, handleSchedulingOpen } = props;

  return (
    <div
      className="w-full min-w-100 p-4 bg-white flex space-x-4 rounded-lg shadow-md hover:scale-105 transition transform duration-500 cursor-pointer"
      onClick={() => handleSchedulingOpen()}
    >
      <div className="mt-4 ml-2">{iconSelect(title)}</div>
      <div className="w-full">
        <h1 className="mt-4 p-2 text-center text-lg font-bold text-gray-700 mb-4">
          {title}
        </h1>
      </div>
    </div>
  );
}
