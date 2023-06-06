interface ServiceCardProps {
	title: string;
	iconName: string;
	serviceId: number;
}

export default function ServiceCard(props: ServiceCardProps) {
	const { title, iconName, serviceId } = props;

	return (
	
		
		<div className=" bg-gray-100 grid items-center justify-center">
		  <div className="p-4 bg-white flex  space-x-2 rounded-lg shadow-md hover:scale-105 transition transform duration-500 cursor-pointer">
			<div>
			  <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 text-indigo-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
				<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z" />
			  </svg>
			</div>
			<div>
			  <h1 className="text-xl font-bold text-gray-700 mb-2">{title}</h1>
				</div>
		  </div>
		</div>
	);
}