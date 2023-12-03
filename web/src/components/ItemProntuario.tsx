import { Link } from "react-router-dom";

export default function ItemProntuario() {

  return (
    <>
      
        <div className="mx-8 rounded-md border-2">
        
        
        <div className="my-4 mx-4 rounded-md px-1.5 py-1.5">
        <label
          htmlFor="nomeVeterinario"
          className="block text-sm font-medium leading-6 text-gray-900"
          >
          Nome do Médico Veterinário
        </label>
        <input
          id="nomeVeterinario"
          name="nomeVeterinario"
          type="text"
          required
          value={""}
          onChange={() => {}}
          className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
          />

        <label
          htmlFor="sintomas"
          className="block text-sm font-medium leading-6 text-gray-900"
        >
          Sintomas
        </label>
        <textarea
          name="sintomas"
          rows={4}
          cols={40}
          className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
          />

        <label
          htmlFor="diagnostico"
          className="block text-sm font-medium leading-6 text-gray-900"
          >
          Diagnostico
        </label>
        <textarea
          name="diagnostico"
          rows={4}
          cols={40}
          className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
          />

        <label
          htmlFor="examesSolicitados"
          className="block text-sm font-medium leading-6 text-gray-900"
          >
          Exames solicitados
        </label>
        <textarea
          name="examesSolicitados"
          rows={4}
          cols={40}
          className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
          />

        <label
          htmlFor="prescricao"
          className="block text-sm font-medium leading-6 text-gray-900"
          >
          Prescrição
        </label>
        <textarea
          name="prescricao"
          rows={4}
          cols={40}
          className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
        />

        <label
          htmlFor="observacoes"
          className="block text-sm font-medium leading-6 text-gray-900"
          >
          Observações
        </label>
        <textarea
          name="observacoes"
          rows={4}
          cols={40}
          className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
          />
      </div>
</div>
    </>
  );
}
