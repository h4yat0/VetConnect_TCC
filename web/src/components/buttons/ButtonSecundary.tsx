interface buttonSecundaryProps {
	text: string;
	onClickFunction?: () => void;
}

export default function ButtonSecundary(props: buttonSecundaryProps) {
	const { text, onClickFunction } = props;

	return (
		<button
			className='mx-8 
            bg-gray-800 
            text-white 
            py-2 
            px-4 
            rounded-sm 
            text-sm 
            font-bold
            cursor-pointer 
            transition-all 
            duration-200 
            ease-in-out 
            hover:bg-gray-700 
            transform 
            hover:scale-105 
            focus:outline-none
            disabled:opacity-75
            disabled:hover:static
            '
			onClick={onClickFunction}
		>
			{text}
		</button>
	);
}
