import {
	XCircleIcon,
	ExclamationTriangleIcon,
	CheckCircleIcon,
} from "@heroicons/react/24/outline";

interface AlertMessageProps {
	title: string;
	description: string;
	type: "attention" | "danger" | "success";
}

export default function AlertMessage(props: AlertMessageProps) {
	const { title, description, type } = props;

	const getAlertClassName = (type: AlertMessageProps["type"]): string => {
		switch (type) {
			case "attention":
				return "bg-yellow-100";
			case "danger":
				return "bg-red-100";
			case "success":
				return "bg-green-200";
			default:
				return "";
		}
	};

	const getIcon = (type: AlertMessageProps["type"]): JSX.Element => {
		switch (type) {
			case "attention":
				return <ExclamationTriangleIcon className='h-5 w-5 text-yellow-600' />;
			case "danger":
				return <XCircleIcon className='h-5 w-5 text-red-600' />;
			case "success":
				return <CheckCircleIcon className='h-5 w-5 text-green-600' />;
			default:
				return <></>; // Return an empty fragment if the type is not recognized
		}
	};

	const alertClassName = getAlertClassName(type);
	const icon = getIcon(type);

	return (
		<div
			className={`flex flex-row w-ful  p-4 rounded-md justify-center ${alertClassName}`}
		>
			<div className='pr-2 pt-0.5'>{icon}</div>
			<div>
				<p className='font-medium text-opacity-5' aria-live='assertive'>
					{title}
				</p>
				<p>{description}</p>
			</div>
		</div>
	);
}
