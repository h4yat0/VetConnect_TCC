import { Link } from "react-router-dom";

interface HistoryProps {
  nome: string;
  clinicId: number;
  dateService: string;
}
export default function HistoryCard(props: HistoryProps) {
  const { nome, clinicId, dateService } = props;

  return (
    <Link to={`unidades/${clinicId}`}>
      <div className="w-full min-w-100 p-4 bg-white flex space-x-4 rounded-lg shadow-md hover:scale-105 transition transform duration-500 cursor-pointer">
        <div className="mt-4 ml-2">
            <h3 className="pt-1 font-semibold text-lg"> {nome}</h3>
            <h4 className="pt-1 font-semibold text-lg"> {dateService}</h4>
        </div>
      </div>
    </Link>
  );
}
