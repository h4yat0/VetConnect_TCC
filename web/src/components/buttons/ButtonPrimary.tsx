interface buttonPrimaryProps {
	text: String;
}

export default function buttonPrimary(props: buttonPrimaryProps) {
	return (
		<button className='rounded-md bg-vetConnectPrimaryGreen px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-vetConnectSecundaryGreen focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-vetConnectSecundaryGreen'>
			{props.text}
		</button>
	);
}
