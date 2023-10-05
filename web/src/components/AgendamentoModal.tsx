export default function AgendamentoModal() {
  
    return (
      <div className="w-[1634px] h-[1088px] bg-black bg-opacity-20 flex-col justify-center items-center gap-2.5 inline-flex">
        <div className="h-[390px] px-[21px] py-[19px] bg-white rounded-[10px] border-2 border-black flex-col justify-start items-start gap-[33px] flex">
          <div className="justify-start items-center gap-[43px] inline-flex">
            <div className="h-[43px] p-2 bg-stone-900 rounded border border-emerald-400 justify-center items-center gap-2.5 flex">
              <div className="text-emerald-400 text-[22px] font-extrabold font-['Inter']">
                Selecione o funcionário
              </div>
            </div>
            <div className="text-black text-base font-extrabold font-['Inter']">
              22 mar - 15:00
            </div>
          </div>
          <div className="justify-start items-start gap-[9px] inline-flex">
            <div className="w-[468px] pl-5 pr-[15px] py-5 rounded border border-black flex-col justify-start items-start gap-3 inline-flex">
              <div className="text-black text-xl font-extrabold font-['Inter']">
                Serviço: Tosa
              </div>
            </div>
            <div className="pl-5 pr-[15px] py-5 rounded border border-black flex-col justify-start items-start gap-3 inline-flex">
              <div className="text-black text-xl font-extrabold font-['Inter']">
                Valor: R$ 20,00{" "}
              </div>
            </div>
          </div>
          <div className="self-stretch h-[103px] flex-col justify-start items-start gap-[25px] flex">
            <div className="text-black text-xl font-extrabold font-['Inter']">
              Oberservações
            </div>
            <div className="self-stretch px-1 py-2 rounded border border-black justify-start items-start gap-2.5 inline-flex">
              <div className="w-[516px] text-black text-base font-normal font-['Inter']">
                Escreva a observação aqui....
                <br />
              </div>
            </div>
          </div>
          <div className="self-stretch justify-start items-start gap-5 inline-flex">
            <div className="grow shrink basis-0 h-[43px] p-2 bg-emerald-400 rounded justify-center items-center gap-2.5 flex">
              <div className="text-stone-900 text-[22px] font-extrabold font-['Inter']">
                Começar Atendimento
              </div>
            </div>
            <div className="h-[43px] p-2 bg-red-500 rounded justify-center items-center gap-2.5 flex">
              <div className="text-white text-[22px] font-extrabold font-['Inter']">
                Excluir{" "}
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  
}



