interface buttonPrimaryProps {
	text: string;
	type?: "button" | "submit" | "reset";
	width?: "w-full";
	onClickFunction?: () => void;
	disabled?: boolean;
}

export default function buttonPrimary(props: buttonPrimaryProps) {
	const { type = "button", text, width, onClickFunction, disabled } = props;

	const buttonClassName = `
        ${width}
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
        focus:outline-none
        disabled:opacity-75
        disabled:pointer-events-none
    `;

	return (
		<button
			type={type}
			className={buttonClassName}
			onClick={onClickFunction}
			disabled={disabled}
		>
			{text}
		</button>
	);
}
