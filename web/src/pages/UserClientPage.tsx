import Cleave from "cleave.js/react";
import "../modules/cleave-phone.br.js";

import ButtonPrimary from "../components/buttons/ButtonPrimary.js";
import ButtonSecundary from "../components/buttons/ButtonSecundary.js";

import { useDispatch, useSelector } from "react-redux";
import {
	updateAddress,
	updateBirthDate,
	updateCpf,
	updateEmail,
	updateName,
	updatePhone,
	updateId,
	getAddress,
	getBirthDate,
	getCpf,
	getEmail,
	getId,
	getName,
	getPassword,
	getPhone,
} from "../redux/client.js";
import axios from "axios";
import { useState } from "react";
import ButtonDanger from "../components/buttons/ButtonDanger.js";

function sanitizeString(str: string): string {
	return str.replace(/[.\-\s]/g, "");
}

export default function UserClientPage() {
	const [editDisabled, setEditDisabled] = useState(true);

	const handleSetEditEnabled = () => {
		setEditDisabled(!editDisabled);
	};

	const [name, setName] = useState("");
	const [cpf, setCpf] = useState("");
	const [birthDate, setBirthDate] = useState("");
	const [address, setAddress] = useState("");
	const [phone, setPhone] = useState("");
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");

	const idStore = useSelector(getId);
	const cpfStore = useSelector(getCpf);
	const nameStore = useSelector(getName);
	const phoneStore = useSelector(getPhone);
	const emailStore = useSelector(getEmail);
	const addressStore = useSelector(getAddress);
	const birthDateStore = useSelector(getBirthDate);

	const api = axios.create({
		baseURL: `http://localhost:9191`,
	});

	const postEditedInformation = async () => {
		let reponse = await api
			.put("cliente/alterar", {
				id: idStore,
				nome: name,
				dataNascimento: birthDate,
				cpf: sanitizeString(cpf),
				endereco: address,
				telefone: sanitizeString(phone),
				email: email,
				senha: password,
			})
			.then(function (response) {
				console.log(response);
			})
			.catch(function (error) {
				console.log(error);

				console.log("id:", idStore);
				console.log("nome:", name);
				console.log("dataNascimento:", birthDate);
				console.log("cpf:", cpf);
				console.log("endereco:", address);
				console.log("telefone:", phone);
				console.log("email:", email);
				console.log("senha:", password);
			});
	};

	return (
		<div className='w-full mb-10'>
			<div className='flex justify-between items-center px-32 py-10'>
				<span className='font-bold text-2xl'>Perfil</span>
				<ButtonPrimary text='Cadastrar Animal' />
			</div>
			<div className='mx-24  px-12 py-10 border-2 rounded-lg '>
				<div className='flex justify-between items-center mb-8'>
					<div className='flex justify-between items-center gap-4'>
						<div className='w-10 h-10 bg-slate-800 rounded-full'></div>
						<span className='font-bold text-xl'>{nameStore}</span>
					</div>
					<ButtonSecundary
						text='Editar informações'
						onClickFunction={handleSetEditEnabled}
					/>
				</div>

				<form className='space-y-6' action='#' method='POST'>
					<div>
						<label
							htmlFor='name'
							className='block text-sm font-medium leading-6 text-gray-900'
						>
							Nome Completo
						</label>
						<div className='mt-2'>
							<input
								id='name'
								name='name'
								type='text'
								defaultValue={nameStore}
								required
								onChange={(e) => setName(e.target.value)}
								disabled={editDisabled}
								className='block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6'
							/>
						</div>
					</div>

					<div>
						<label
							htmlFor='cpf'
							className='block text-sm font-medium leading-6 text-gray-900'
						>
							CPF
						</label>
						<div className='mt-2'>
							<Cleave
								id='cpf'
								name='cpf'
								type='text'
								options={{
									delimiters: [".", ".", "-"],
									blocks: [3, 3, 3, 2],
								}}
								value={cpfStore}
								required
								onChange={(e) => setCpf(e.target.value)}
								disabled={editDisabled}
								className='block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6'
							/>
						</div>
					</div>

					<div>
						<label
							htmlFor='birthDate'
							className='block text-sm font-medium leading-6 text-gray-900'
						>
							Data de Nascimento
						</label>
						<div className='mt-2'>
							<input
								id='birthDate'
								name='birthDate'
								type='date'
								defaultValue={birthDateStore}
								required
								onChange={(e) => setBirthDate(e.target.value)}
								disabled={editDisabled}
								className='block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6'
							/>
						</div>
					</div>

					<div>
						<label
							htmlFor='address'
							className='block text-sm font-medium leading-6 text-gray-900'
						>
							Endereço
						</label>
						<div className='mt-2'>
							<input
								id='address'
								name='address'
								type='text'
								defaultValue={addressStore}
								required
								onChange={(e) => setAddress(e.target.value)}
								disabled={editDisabled}
								className='block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6'
							/>
						</div>
					</div>

					<div>
						<label
							htmlFor='phone'
							className='block text-sm font-medium leading-6 text-gray-900'
						>
							Número de Telefone
						</label>
						<div className='mt-2'>
							<Cleave
								id='phone'
								name='phone'
								type='text'
								value={phoneStore}
								required
								onChange={(e) => setPhone(e.target.value)}
								disabled={editDisabled}
								options={{ phone: true, phoneRegionCode: "br" }}
								className='block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6'
							/>
						</div>
					</div>

					<div>
						<label
							htmlFor='email'
							className='block text-sm font-medium leading-6 text-gray-900'
						>
							Email
						</label>
						<div className='mt-2'>
							<input
								id='email'
								name='email'
								type='email'
								autoComplete='email'
								defaultValue={emailStore}
								required
								onChange={(e) => setEmail(e.target.value)}
								disabled={editDisabled}
								className='block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6'
							/>
						</div>
					</div>

					<div>
						<div className='flex items-center justify-between'>
							<label
								htmlFor='password'
								className='block text-sm font-medium leading-6 text-gray-900'
							>
								Senha
							</label>
						</div>
						<div className='mt-2'>
							<input
								id='password'
								name='password'
								type='password'
								autoComplete='current-password'
								required
								onChange={(e) => setPassword(e.target.value)}
								disabled={editDisabled}
								className='block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6'
							/>
						</div>
					</div>

					<div>
						<ButtonPrimary
							text='Alterar dados'
							width='w-full'
							disabled={editDisabled}
							onClickFunction={postEditedInformation}
						/>
						<ButtonDanger text='Deletar conta' />
					</div>
				</form>
			</div>
		</div>
	);
}
