interface AgendamentoProps {
	title: string;
	imgPath: string;
	clinicId: number;
}
export default function AgendamentoCard(props: AgendamentoProps) {
	const { title, clinicId } = props;

	return (
		<div className='font-inter'>
			<a href={`unidades/${clinicId}`}>
				
				<h3 className='pt-1 font-semibold text-lg'> {title}</h3>
			</a>
		</div>
	);
}
