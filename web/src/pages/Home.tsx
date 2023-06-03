import UnitCard from "../components/UnitCard";

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

export default function Home() {
	return (
		<div className='p-10 font-inter '>
			<div className='py-5'>
				<h1 className='text-2xl font-black '>Clinicas</h1>
				<div className='flex flex-row gap-20 pt-5 '>
					{clinicas.clinicas.map((clinica) => (
						<UnitCard
							key={clinica.id}
							title={clinica.nome}
							clinicId={clinica.id}
							imgPath={"src/assets/imgs/" + clinica.imgPath}
						/>
					))}
				</div>
			</div>
		</div>
	);
}
