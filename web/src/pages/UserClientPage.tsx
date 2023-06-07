import { useNavigate } from "react-router-dom";
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
import { useEffect, useState } from "react";
import ButtonDanger from "../components/buttons/ButtonDanger.js";
import { Link, Navigate } from "react-router-dom";

function sanitizeString(str: string): string {
	return str.replace(/[.\-\s]/g, "");
}

export default function UserClientPage() {
	const navigate = useNavigate();

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
	const nameStore = useSelector(getName);
	const cpfStore = useSelector(getCpf);
	const phoneStore = useSelector(getPhone);
	const emailStore = useSelector(getEmail);
	const addressStore = useSelector(getAddress);
	const birthDateStore = useSelector(getBirthDate);
	const passwordStore = useSelector(getPassword);

	const api = axios.create({
		baseURL: `http://localhost:9191`,
	});

	function isEmptyString(str: string) {
		return str.trim() === "";
	}

	const postEditedInformation = async () => {
		let reponse = await api
			.put("cliente/alterar", {
				id: idStore,
				nome: isEmptyString(name) ? nameStore : name,
				dataNascimento: isEmptyString(birthDate) ? birthDateStore : birthDate,
				cpf: isEmptyString(sanitizeString(cpf))
					? cpfStore
					: sanitizeString(cpf),
				endereco: isEmptyString(address) ? addressStore : address,
				telefone: isEmptyString(sanitizeString(phone))
					? phoneStore
					: sanitizeString(phone),
				email: isEmptyString(email) ? emailStore : email,
				senha: isEmptyString(password) ? passwordStore : password,
			})
			.then(function (response) {
				console.log(response);
				navigate("/");
			})
			.catch(function (error) {
				console.log(error);
			});
	};

	const deleteAccount = async () => {
		let reponse = await api
			.delete(`/cliente/delete/${idStore}`)
			.then(function (response) {
				console.log(response);
				navigate("/");
			})
			.catch(function (error) {
				console.log(error);
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
								required
								value={name}
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
								required
								value={cpf}
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
								required
								value={birthDate}
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
								required
								value={address}
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
								required
								value={phone}
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
								required
								value={email}
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
								value={password}
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
					</div>
					<div>
						<ButtonDanger
							text='Deletar conta'
							disabled={editDisabled}
							onClickFunction={deleteAccount}
						/>
					</div>
				</form>
			</div>
		</div>
	);
}
function naviagate(arg0: string) {
	throw new Error("Function not implemented.");
}
