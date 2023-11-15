import { useSelector } from "react-redux";
import { getUnits } from "../redux/unit";
import { useEffect } from "react";
import UnitCard from "../components/UnitCard";
import useRestockUnitsAndServices from "../hooks/useStoreRestock";

export default function UnitListPage() {
  const units = useSelector(getUnits);

  const { getUnitsAndServices } = useRestockUnitsAndServices();

  useEffect(() => {
    if (units.length == 0) getUnitsAndServices();
  }, []);
  
  return (
    <div className="my-4">
      <div className="p-2 pb-2 pt-4 font-inter ">
        <h1 className="text-2xl font-black text-left">Unidades</h1>
      </div>
      <div className="flex-row gap-20 pt-5 grid grid-cols-4 ">
        {units.map((unit) => (
          <UnitCard
            key={unit.id}
            title={unit.name}
            unitId={unit.id}
            image={unit.images[0]}
          />
        ))}
      </div>
    </div>
  );
}
