interface ServiceCardProps {
	title: string;
	iconName: string;
	serviceId: number;
}

export default function ServiceCard(props: ServiceCardProps) {
	const { title, iconName, serviceId } = props;

	return (
		<div className='font-inter'>
			<a href={`servicos/${serviceId}`}>
				<img
					src={iconName}
					alt=''
					className='h-36 w-[365px] shadow-md rounded-lg object-cover'
				/>
				<h3 className='pt-1 font-semibold text-lg'> {title}</h3>
			</a>
            
		</div>
	);
}