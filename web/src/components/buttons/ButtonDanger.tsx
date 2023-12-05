interface buttonDangerProps {
	text: string;
	onClickFunction?: () => void;
  width?: string;
	disabled?: boolean;
}

export default function ButtonDanger(props: buttonDangerProps) {
	const { text, onClickFunction, width, disabled } = props;
    const buttonClassName = `
            ${width}
            bg-red-600 
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
            disabled:pointer-events-none
            `
	return (
    <button
      className={buttonClassName}
      onClick={onClickFunction}
      disabled={disabled}
    >
      {text}
    </button>
  );
}
