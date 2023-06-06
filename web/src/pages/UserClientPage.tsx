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

function formatCPF(cpf: string) {
	const cpfRegex = /^(\d{3})(\d{3})(\d{3})(\d{2})$/;
	return cpf.replace(cpfRegex, "$1.$2.$3-$4");
}

export default function UserClientPage() {
	const id = useSelector(getId);
	const cpf = useSelector(getCpf);
	const name = useSelector(getName);
	const phone = useSelector(getPhone);
	const email = useSelector(getEmail);
	const address = useSelector(getAddress);
	const password = useSelector(getPassword);
	const birthDate = useSelector(getBirthDate);

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
						<span className='font-bold text-xl'>{name}</span>
					</div>
					<ButtonSecundary text='Editar informações' />
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
								defaultValue={name}
								required
								disabled
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
								value={cpf}
								required
								disabled
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
								defaultValue={birthDate}
								required
								disabled
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
								defaultValue={address}
								required
								disabled
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
								value={phone}
								required
								disabled
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
								defaultValue={email}
								required
								disabled
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
								disabled
								className='block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6'
							/>
						</div>
					</div>

					<div>
						<button
							type='submit'
							className='flex w-full justify-center rounded-md bg-vetConnectPrimaryGreen px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-vetConnectSecundaryGreen focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-vetConnectPrimaryGreen'
						>
							Cadastrar
						</button>
					</div>
				</form>
			</div>
		</div>
	);
}
