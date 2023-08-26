import Cachorro from "../assets/imgs/Cachorro.jpg";
export default function Animals() {
  return (
    <div className="pt-4 font-inter rounded-xl px-10">
      <h1 className="font-bold my-2">Rex - Labrador (12 Anos)</h1>

      <div className="grid gap-4 grid-cols-3  " style={{ maxHeight: 400 }}>
        <div className="col-span-2">
          <img
            src={Cachorro}
            alt="tailwind logo"
            className="rounded-xl w-full h-2/3 object-cover"
          />
        </div>
        <div className="flex justify-center  bg-vetConnectSecundaryGreen h-2/3 rounded-xl">
          <div className="flex items-center justify-center font-bold">
            Prontuário
          </div>
        </div>
      </div>

      {/* Histórico de Serviços */}
      <div>
        <h1 className="font-bold m-2">Histórico de Serviços</h1>
        <div className=" rounded-xl px-4 ">
          <div className="grid gap-4 grid-cols-3 h-2/3">
            <div className=" col-span-2">Tosa - Unidade Centro Jundiaí</div>
            <div className="flex justify-center  h-2/3">21 Jan</div>
          </div>

          <div className="grid gap-4 grid-cols-3 h-2/3">
            <div className=" col-span-2">Banho - Unidade Centro Jundiaí</div>
            <div className="flex justify-center  h-2/3">21 Jan</div>
          </div>

          <div className="grid gap-4 grid-cols-3 h-2/3">
            <div className=" col-span-2">Castração - Unidade Retiro</div>
            <div className="flex justify-center  h-2/3">30 Jan</div>
          </div>
        </div>
      </div>
    </div>
  );
}
