export default function UnitListPage() {
	
	const clinicas = {
		clinicas: [
			{
				id: 1,
				nome: "Clínica PetVet - Centro",
				imgPath: "ClínicaPetVet-Centro.jpg",
			},
			{
				id: 2,
				nome: "Clínica PetVet - Zona Oeste",
				imgPath: "Clínica PetVet - Zona Oeste.jpg",
			},
			{
				id: 3,
				nome: "Clínica PetVet - Zona Leste",
				imgPath: "Clínica PetVet - Zona Leste.jpg",
			},
			{
				id: 4,
				nome: "Clínica PetVet - Zona Norte",
				imgPath: "Clínica PetVet - Zona Norte.jpg",
			},
		],
	};

	return (
		<div className="my-4">
			<div className='p-2 pb-2 pt-4 font-inter '>
					<h1 className='text-2xl font-black text-left'>Unidades</h1>
			</div>

			<div className='flex flex-row gap-20 pt-5 '>
					{clinicas.clinicas.map((clinicas) => (
						<div className='font-inter m-2'>
						<a href={`unidades/${clinicas.id}`}>
							<img
								src={"src/assets/imgs/" + clinicas.imgPath}
								alt=''
								className='h-48 w-[365px] shadow-md rounded-lg object-cover'
							/>
							<h3 className='pt-1 font-semibold text-lg'> {clinicas.nome}</h3>
						</a>
					</div>
					))}
			</div>

			<div className='flex flex-row gap-20 pt-5 '>
					{clinicas.clinicas.map((clinicas) => (
						<div className='font-inter m-2'>
						<a href={`unidades/${clinicas.id}`}>
							<img
								src={"src/assets/imgs/" + clinicas.imgPath}
								alt=''
								className='h-48 w-[365px] shadow-md rounded-lg object-cover'
							/>
							<h3 className='pt-1 font-semibold text-lg'> {clinicas.nome}</h3>
						</a>
					</div>
					))}
			</div>

		</div>
	);
}
