import { Link, useNavigate } from "react-router-dom";

interface UnitCardProps {
  title: string;
  image: string;
  unitId: number;
}
export default function UnitCard(props: UnitCardProps) {
	const navigate = useNavigate()
	const { title, image, unitId } = props;

	return (
      <Link to={`/unidades/${unitId}`}>
        <img
          src={`data:image/webp;base64,${image}`}
          alt=""
          className="h-36 w-[365px] shadow-md rounded-lg object-cover"
        />
        <h3 className="pt-1 font-semibold text-lg">{title}</h3>
      </Link>
  );
}
