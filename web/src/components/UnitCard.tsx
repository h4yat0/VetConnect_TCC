interface UnitCardProps {
	title: string;
	imgPath: string;
	clinicId: number;
}
export default function UnitCard(props: UnitCardProps) {
	const { title, imgPath, clinicId } = props;

	return (
		<div className='font-inter'>
			<a href={`unidades/${clinicId}`}>
				<img
					src={imgPath}
					alt=''
					className='h-36 w-[365px] shadow-md rounded-lg object-cover'
				/>
				<h3 className='pt-1 font-semibold text-lg'> {title}</h3>
			</a>
		</div>
	);
}
