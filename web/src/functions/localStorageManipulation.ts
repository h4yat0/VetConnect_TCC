import { useDispatch, useSelector } from "react-redux";
import {
	updateAddress,
	updateBirthDate,
	updateCpf,
	updateEmail,
	updateName,
	updatePhone,
	updateId,
} from "../redux/client.js";

interface saveToCacheInterface {
	id: number;
	name: string;
	cpf: string;
	birthDate: string;
	address: string;
	phone: string;
	email: string;
	password: string;
}

export function saveDataToLocalStorage(data: saveToCacheInterface) {
	localStorage.setItem("id", data.id.toString());
	localStorage.setItem("name", data.name);
	localStorage.setItem("cpf", data.cpf);
	localStorage.setItem("birthDate", data.birthDate);
	localStorage.setItem("address", data.address);
	localStorage.setItem("phone", data.phone);
	localStorage.setItem("email", data.email);
	localStorage.setItem("password", data.password);
}

export function useSaveLocalStorageOnClientStore() {
	const dispatch = useDispatch();
	let validateIdInLocalStorage = () => {
		const id = localStorage.getItem("id");
		return id !== null && parseInt(id) !== -1;
	};
	if (validateIdInLocalStorage()) {
		const idString = localStorage.getItem("id");
		const id = idString !== null ? parseInt(idString) : -1;
		const name = localStorage.getItem("name") ?? "";
		const cpf = localStorage.getItem("cpf") ?? "";
		const birthDate = localStorage.getItem("birthDate") ?? "";
		const address = localStorage.getItem("address") ?? "";
		const phone = localStorage.getItem("phone") ?? "";
		const email = localStorage.getItem("email") ?? "";

		dispatch(updateId(id));
		dispatch(updateName(name));
		dispatch(updateCpf(cpf));
		dispatch(updateEmail(email));
		dispatch(updateBirthDate(birthDate));
		dispatch(updateAddress(address));
		dispatch(updatePhone(phone));
	}
}
