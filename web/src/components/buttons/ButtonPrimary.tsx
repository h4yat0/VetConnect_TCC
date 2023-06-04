interface buttonPrimaryProps {
	text: string;
}

export default function buttonPrimary(props: buttonPrimaryProps) {
	return (
		<button
			className='mx-8 
			bg-vetConnectPrimaryGreen
            text-gray-800
            py-2 
            px-4 
            rounded-sm 
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
