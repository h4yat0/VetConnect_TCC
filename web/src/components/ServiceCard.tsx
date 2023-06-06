import { TbVaccine } from 'react-icons/tb';
import { MdPets } from 'react-icons/md';
import {FaShower} from 'react-icons/fa';
import {IoCutSharp} from 'react-icons/io5';

interface ServiceCardProps {
	title: string;
	iconName: string;
	serviceId: number;
	
}

function iconSelect(title:string): any{
	if(title == "Banho") return <FaShower className="h-10 w-10"/>;
	if(title == "Tosa") return <IoCutSharp className="h-10 w-10"/>;
	if(title == "Castração") return <MdPets className="h-10 w-10"/>;
	if(title == "Vacinação") return <TbVaccine className="h-10 w-10"/>;
	return <MdPets className="h-10 w-10"/>;
}
	
export default function ServiceCard(props: ServiceCardProps) {
	const { title, iconName, serviceId } = props;

	

	return (
		<div className="w-[365px] bg-gray-100 grid items-center justify-center">
		  <div className="w-[365px] p-4 bg-white flex  space-x-4 rounded-lg shadow-md hover:scale-105 transition transform duration-500 cursor-pointer">
			<div className="mt-4 ml-2">
			
			{iconSelect(title)} 
			
			</div>
			<div className="w-[365px]">
			<div className="absolute left-1/4 w-0.5 h-3/4 bg-gray-600"></div>
			<h1 className="mt-4 ml-2 p-2 text-center text-xl font-bold text-gray-700 mb-4">{title}</h1>
				</div>
		  </div>
		</div>
	);
}