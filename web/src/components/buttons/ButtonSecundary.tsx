interface buttonSecundaryProps {
	text: String;
}

export default function ButtonSecundary(props: buttonSecundaryProps) {
	return (
		<button
			className='mx-8 
            bg-gray-800 
            text-white 
            py-2 
            px-4 
            rounded-full 
            text-sm 
            font-bold
            cursor-pointer 
            transition-all 
            duration-200 
            ease-in-out 
            hover:bg-vetConnectSecundaryGreen 
            hover:text-vetConnectGray
            transform 
            hover:scale-105 
            focus:outline-none'
		>
			{props.text}
		</button>
	);
}
