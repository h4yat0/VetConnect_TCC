import Cachorro from "../assets/imgs/Cachorro.jpg";
export default function Animals() {
  return (
    <div className="pt-10 h-screen font-inter">
      <div className="grid grid-rows-3 grid-flow-col gap-4">
        <div className="row-span-2 col-span-2...">
          <h1>
            <b>Rex - 6 Anos (Labrador)</b>
          </h1>
		
			</div>
        </div>
        <div className="col-span-2 ...">
		

        <div className="col-span-3 ...">
          <div className="grid grid-rows-2 grid-flow-col gap-4">
            <div className="col-span-2..."> 
			<div className="bg-black w-4/6 h-50 rounded-xl"> 
			<img
          src={Cachorro}
          alt="tailwind logo"
          className="rounded-xl w-4/6 h-50"
		  />
		  </div>
		</div>
            <div className="col-span-2 ...">02</div>
            <div className="col-span-2 ...">03</div>
            <div className="col-span-2 ...">04</div>
          </div>
        </div>
      </div>

      <div>
        <h1>
          <b>Histórioco de Serviços</b>
        </h1>
        <div>
          <div className="flex flex-row">
            <div className="basis-1/2">Tosa Unidade Centro de Jundiaí</div>
            <div className="basis-1/6">21 Março</div>
          </div>
		  <div className="flex flex-row">
            <div className="basis-1/2">Tosa Unidade Centro de Jundiaí</div>
            <div className="basis-1/6">21 Março</div>
          </div>
		  <div className="flex flex-row">
            <div className="basis-1/2">Tosa Unidade Centro de Jundiaí</div>
            <div className="basis-1/6">21 Março</div>
          </div>
		  <div className="flex flex-row">
            <div className="basis-1/10">Tosa Unidade Centro de Jundiaí</div>
            <div className="basis-1/2 text-end">21 Março</div>
          </div>
        </div>
      </div>
    </div>
  );
}
