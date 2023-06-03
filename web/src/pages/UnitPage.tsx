interface UnitPageProps {
	unitId: number;
	unitName: string;
	businessHours: string;
	contact: string;
}

export default function UnitPage() {
	return (
		<div className='px-44 pt-10 h-screen font-inter'>
			<div className='grid grid-cols-2 gap-7'>
				<img
					className='w-full h-full object-cover rounded-lg'
					src='https://images.unsplash.com/photo-1570347929626-2bbc8032d98b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1471&q=80'
					alt='Gato em uma janela'
				/>
				<div className='p-4 rounded-lg border-vetConnectSecundaryGreen'>
					<h1 className='text-3xl font-bold'>Nome Unidade</h1>
					<div className='pt-4 gap-y-6 flex flex-col'>
						<div className='text-lg'>
							<p>
								<b>Horário de Funcionamento:</b> 10:00 - 18:30
							</p>
							<p>
								<b>Contato:</b> (11)11111-1111
							</p>
						</div>
						<div className='flex flex-col p-2 rounded-lg border-2'>
							<p className='mb-2'>
								<b>Especialidades:</b>
							</p>
							<div className='flex flex-wrap gap-2'>
								<span className='p-1 rounded-md border-2 border-gray-400'>
									Dermatologia
								</span>
								<span className='p-1 rounded-md border-2 border-gray-400'>
									Oftalmologia
								</span>
								<span className='p-1 rounded-md border-2 border-gray-400'>
									Cardiologia
								</span>
								<span className='p-1 rounded-md border-2 border-gray-400'>
									Cardiologia
								</span>
								<span className='p-1 rounded-md border-2 border-gray-400'>
									Cardiologia
								</span>
								<span className='p-1 rounded-md border-2 border-gray-400'>
									Cardiologia
								</span>{" "}
							</div>
						</div>
					</div>
				</div>
			</div>
			<div className='pt-12'>
				<h1 className='text-2xl font-bold'>Serviços</h1>
				<div className='grid grid-cols-3 gap-5 mt-3'>
					<button className='py-3 border-2 rounded-md'>Banho</button>
					<button className='py-3 border-2 rounded-md'>Tosa</button>
					<button className='py-3 border-2 rounded-md'>Vacinação</button>
				</div>
			</div>
		</div>
	);
}
