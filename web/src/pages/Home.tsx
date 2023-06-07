import UnitCard from "../components/UnitCard";
import ServiceCard from "../components/ServiceCard";
import PromoCard from "../components/PromoCard";
import { useEffect } from "react";
import { useSaveLocalStorageOnClientStore } from "../functions/localStorageManipulation";

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

const servicos = {
	servicos: [
		{
			id: 1,
			nome: "Banho",
			iconName: "ClínicaPetVet-Centro.jpg",
		},
		{
			id: 2,
			nome: "Tosa",
			iconName: "Clínica PetVet - Zona Oeste.jpg",
		},
		{
			id: 3,
			nome: "Castração",
			iconName: "Clínica PetVet - Zona Leste.jpg",
		},
		{
			id: 4,
			nome: "Vacinação",
			iconName: "Clínica PetVet - Zona Norte.jpg",
		},
	
	],
};

export default function Home() {
	return (
	<div>
		<PromoCard></PromoCard>
		<div className='p-10 font-inter '>
				

			<div className='py-8'>
				<h1 className='text-2xl font-black '>Últimos serviços utilizados</h1>
				<div className='flex flex-row gap-20 pt-5 '>
					{servicos.servicos.map((servicos) => (
						<ServiceCard
							key={servicos.id}
							title={servicos.nome}
							serviceId={servicos.id}
							iconName={"src/assets/imgs/" + servicos.iconName}
						/>
					))}
				</div>
			</div>

			<div className='py-8'>
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
	</div>
	);
}
