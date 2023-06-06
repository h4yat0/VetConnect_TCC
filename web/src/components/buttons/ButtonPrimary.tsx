import { MouseEventHandler } from "react";

interface buttonPrimaryProps {
	text: string;
	type?: "button" | "submit" | "reset";
	width?: "w-full";
	onClickFunction?: () => void;
}

export default function buttonPrimary(props: buttonPrimaryProps) {
	const { type = "button", text, width, onClickFunction } = props;

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
    `;

	return (
		<button type={type} className={buttonClassName} onClick={onClickFunction}>
			{text}
		</button>
	);
}
