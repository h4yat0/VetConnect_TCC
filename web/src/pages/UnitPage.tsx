import { useSelector } from "react-redux";
import { getUnit, getUnits } from "../redux/unit";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import Cleave from "cleave.js/react";

export default function UnitPage() {
  const location = useLocation();
  const navigate = useNavigate();

  const unitId: number = parseInt(location.pathname.replace("/unidades/", ""));

  let units = useSelector(getUnits);
  units = [...units];

  useEffect(() => {
    if (units.length == 0) {
      navigate("/", { replace: true });
    }
  }, []);

  let unit = units.find((unit) => unit.id === unitId);

  if (unit === undefined) {
    unit = {
      id: -1,
      name: "",
      cnpj: "",
      cep: "",
      bairro: "",
      rua: "",
      numero: "",
      estado: "",
      cidade: "",
      complemento: "",
      contact: "",
      specialty: "",
      images: [""],
      services: [{ id: -1, name: "", price: 0 }],
      openingTime: "",
      closingTime: "",
    };
  }

  return (
    <div className="px-44 pt-10 h-screen font-inter">
      <div className="grid grid-cols-2 gap-7">
        <img
          className="w-full h-full object-cover rounded-lg"
          src={`data:image/webp;base64,${unit.images[0]}`}
          alt="Imagem da Unidade"
        />
        <div className="p-4 rounded-lg border-vetConnectSecundaryGreen">
          <h1 className="text-3xl font-bold">{unit.name}</h1>
          <div className="pt-4 gap-y-6 flex flex-col">
            <div className="text-lg">
              <p>
                <b>Horário de Funcionamento:</b> {`${unit.openingTime}`} -{" "}
                {`${unit.closingTime}`}
              </p>
              <p>
                <b>Contato:</b>

                <Cleave
                  type="text"
                  options={{ phone: true, phoneRegionCode: "br"}}
                  value={unit.contact}
				  disabled
                  className="appearance-none border-none"
                />
              </p>
            </div>
            <div className="flex flex-col p-2 rounded-lg border-2">
              <p className="mb-2">
                <b>Especialidades:</b>
              </p>
              <div className="flex flex-wrap gap-2">
                <span className="p-1 rounded-md border-2 border-gray-400">
                  Dermatologia
                </span>
                <span className="p-1 rounded-md border-2 border-gray-400">
                  Oftalmologia
                </span>
                <span className="p-1 rounded-md border-2 border-gray-400">
                  Cardiologia
                </span>
                <span className="p-1 rounded-md border-2 border-gray-400">
                  Cardiologia
                </span>
                <span className="p-1 rounded-md border-2 border-gray-400">
                  Cardiologia
                </span>
                <span className="p-1 rounded-md border-2 border-gray-400">
                  Cardiologia
                </span>{" "}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="pt-12">
        <h1 className="text-2xl font-bold">Serviços</h1>
        <div className="grid grid-cols-3 gap-5 mt-3">
          <button className="py-3 border-2 rounded-md">Banho</button>
          <button className="py-3 border-2 rounded-md">Tosa</button>
          <button className="py-3 border-2 rounded-md">Vacinação</button>
        </div>
      </div>
    </div>
  );
}
