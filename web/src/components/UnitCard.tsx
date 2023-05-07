interface UnitProps {
	title: string;
	imgPath: string;
}
export default function UnitCard(props: UnitProps) {
	const { title, imgPath } = props;

	return (
		<div className='font-inter'>
			<img
				src={imgPath}
				alt=''
				className='h-36 w-[365px] shadow-md rounded-lg object-cover'
			/>
			<h3 className='pt-1 font-semibold text-lg'> {title}</h3>
		</div>
	);
}
