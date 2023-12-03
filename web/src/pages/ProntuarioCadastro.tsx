import ItemProntuario from "../components/ItemProntuario";
import ButtonPrimary from "../components/buttons/ButtonPrimary";

export default function ProntuarioCadastro() {
  return (
    <>
      <form className="space-y-6" onSubmit={() => {}} method="POST">
        <div className="mx-4 rounded-md ">
          <h1 className="font-bold text-lg ">Cadastro de Prontuário</h1>
        </div>

        <div className="my-4  rounded-md  ">
          <div className="my-4 mx-4">
            <div className="mx-8 mb-2   py-1.5  rounded-md  bg-vetConnectSecundaryGreen">
              <h1 className="px-1.5 font-bold">Informações do Prontuário</h1>
            </div>

            <div className="mx-8 mb-4 rounded-md border-2">
              <div className="my-4 mx-4 rounded-md px-1.5 py-1.5">
                <label
                  htmlFor="animalName"
                  className="block text-sm font-medium leading-6 text-gray-900"
                >
                  Nome animal
                </label>
                <input
                  id="animalName"
                  name="animalName"
                  type="text"
                  required
                  value={""}
                  onChange={() => {}}
                  className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />

                <label
                  htmlFor="enfermidades"
                  className="block text-sm font-medium leading-6 text-gray-900"
                >
                  Enfermidades
                </label>
                <textarea
                  name="enfermidades"
                  rows={4}
                  cols={40}
                  className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />

                <label
                  htmlFor="alergias"
                  className="block text-sm font-medium leading-6 text-gray-900"
                >
                  Alergias
                </label>
                <textarea
                  name="alergias"
                  rows={4}
                  cols={40}
                  className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />

                <label
                  htmlFor="medicamentos"
                  className="block text-sm font-medium leading-6 text-gray-900"
                >
                  Medicamentos
                </label>
                <textarea
                  name="medicamentos"
                  rows={4}
                  cols={40}
                  className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>
            <div className="mx-8 mb-2 py-1.5  rounded-md border-2 bg-vetConnectSecundaryGreen">
              <h1 className="px-1.5 font-bold">Itens do Prontuário</h1>
            </div>
            <ItemProntuario />

            <div className="my-4 mx-8 mb-2 flex justify-end ">
              <div className="my-4 mx-4">
                <ButtonPrimary text="Cadastrar" type="submit" />
              </div>
            </div>
          </div>
        </div>
      </form>
    </>
  );
}
