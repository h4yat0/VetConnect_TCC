import ButtonPrimary from "../components/buttons/ButtonPrimary";

export default function ProntuarioCadastro() {
  return (
    <>
    <div className="my-4 mx-4 rounded-md px-1.5 py-1.5 border-2">
      <div className="py-1.5 my-2 rounded-md border-2 bg-vetConnectSecundaryGreen">
        <h1 className="font-bold m-2">Cadastro de Prontuario</h1>
      </div>
      <div>
      
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
      onChange={()=>{}}
      className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
        
        <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Enfermidades
  </label>
  <textarea name="postContent" rows={4} cols={40}
      className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

<label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Alergias
  </label>
  <textarea name="postContent" rows={4} cols={40}
      className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

<label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Medicamentos
  </label>
  <textarea name="postContent" rows={4} cols={40}
      className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
        
        
        

        <div>
        <label
    htmlFor="animalName"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Nome do Médico Veterinário
  </label>
      <input
      id="animalName"
      name="animalName"
      type="text"
      required
      value={""}
      onChange={()=>{}}
      className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

             
            <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Sintomas
  </label>
  <textarea name="postContent" rows={4} cols={40}
      className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

            
            <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Diagnostico
  </label>
  <textarea name="postContent" rows={4} cols={40}
      className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
            


            <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    exames solicitados
  </label>
  <textarea name="postContent" rows={4} cols={40}
      className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

            <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Prescrição
  </label>
            <textarea name="postContent" rows={4} cols={40}
      className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

            <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Observações
  </label>
            <textarea name="postContent" rows={4} cols={40}
      className="mb-4 block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

    <div className="my-4">
             <ButtonPrimary text="Cadastrar" type="submit" />
             </div>           
        </div>
        </div>
      </div>
    </>

  );
}
