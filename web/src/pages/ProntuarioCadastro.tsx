export default function ProntuarioCadastro() {
  return (
    <>
      <div className="rounded-md border-0 py-1.5">
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
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
        

        Enfermidades
        Alergias
        Medicamentos

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
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

             
            <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Sintomas
  </label>
  <textarea name="postContent" rows={4} cols={40}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

            
            <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Diagnostico
  </label>
  <textarea name="postContent" rows={4} cols={40}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
            


            <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    exames solicitados
  </label>
  <textarea name="postContent" rows={4} cols={40}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

            <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Prescrição
  </label>
            <textarea name="postContent" rows={4} cols={40}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />

            <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Observações
  </label>
            <textarea name="postContent" rows={4} cols={40}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
            
        </div>

      </div>
    </>
    /* <form className="space-y-6" onSubmit={postAnimal} method="POST">
<div>
  <label
    htmlFor="animalName"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Nome
  </label>
  <div className="mt-2">
    <input
      id="animalName"
      name="animalName"
      type="text"
      required
      value={name}
      onChange={(e) => setName(e.target.value)}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
  </div>
</div>

<div>
  <label
    htmlFor="animalBirthDate"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Data de Nascimento
  </label>
  <div className="mt-2">
    <input
      id="animalBirthDate"
      name="animalBirthDate"
      type="date"
      required
      value={birthDate}
      onChange={(e) => setBirthDate(e.target.value)}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
  </div>
</div>

<div>
  <label
    htmlFor="specie"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Espécie
  </label>
  <div className="mt-2">
    <input
      id="specie"
      name="specie"
      type="text"
      required
      value={specie}
      onChange={(e) => setSpecie(e.target.value)}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
  </div>
</div>

<div>
  <label
    htmlFor="race"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Raça
  </label>
  <div className="mt-2">
    <input
      id="race"
      name="race"
      type="text"
      autoComplete="race"
      required
      value={race}
      onChange={(e) => setRace(e.target.value)}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
  </div>
</div>

<div>
  <label
    htmlFor="color"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Cor
  </label>
  <div className="mt-2">
    <input
      id="color"
      name="color"
      type="text"
      required
      value={color}
      onChange={(e) => setColor(e.target.value)}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
  </div>
</div>

<div>
  <label
    htmlFor="weigth"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Peso (kg)
  </label>
  <div className="mt-2">
    <input
      id="weigth"
      name="weigth"
      type="text"
      autoComplete="weigth"
      required
      value={weigth}
      onChange={(e) => setWeigth(e.target.value)}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
  </div>
</div>

<div>
  <label
    htmlFor="size"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Tamanho (cm)
  </label>
  <div className="mt-2">
    <input
      id="size"
      name="size"
      type="text"
      autoComplete="size"
      required
      value={size}
      onChange={(e) => setSize(e.target.value)}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
  </div>
</div>

<div>
  <label
    htmlFor="sex"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Sexo
  </label>
  <div className="mt-2">
    <input
      id="sex"
      name="sex"
      type="text"
      required
      value={sex}
      onChange={(e) => setSex(e.target.value)}
      maxLength={1}
      className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
    />
  </div>
</div>

<div>
  <label
    htmlFor="img"
    className="block text-sm font-medium leading-6 text-gray-900"
  >
    Faça o upload de uma imagem do seu animalzinho
  </label>
  <div className="mt-2">
    <input
      id="img"
      name="img"
      type="file"
      onChange={(e) => handleImgChange(e)}
      maxLength={1}
      className="block w-full border border-gray-300 rounded-md file:rounded file:bg-vetConnectGray file:text-white file:p-2 ring-0 text-gray-900 shadow-sm placeholder:text-gray-400 sm:text-sm sm:leading-6"
    />

    {img && <img src={img} alt="Imagem selecionada" className="mt-1 rounded"/>}
  </div>
</div>

{type == "update" ? (
  <>
    <div>
      <ButtonPrimary
        text="Alterar dados do animal"
        width="w-full"
        onClickFunction={postEditedInformation}
      />
    </div>
    <div>
      <ButtonDanger
        text="Excluir animal"
        onClickFunction={deleteAnimal}
        width="w-full"
      />
    </div>
  </>
) : (
  <ButtonPrimary
    text="Cadastrar"
    width="w-full"
    type="submit"
  />
)}
</form> */
  );
}
