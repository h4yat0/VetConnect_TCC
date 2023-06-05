import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
	updateAddress,
	updateBirthDate,
	updateCpf,
	updateEmail,
	updateName,
	updatePhone,
	updateId,
} from "../redux/client";
import axios from "axios";
import ButtonPrimary from "../components/buttons/ButtonPrimary";
import vetConnectLogo from "../assets/svgs/vetConnectLogo.svg";

export default function SignIn() {
	const dispatch = useDispatch();
	const navigate = useNavigate();

	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");

	const api = axios.create({
		baseURL: `http://localhost:9191`,
	});

	const postSignIn = async () => {
		let reponse = await api
			.post("/cliente/login", {
				email: email,
				senha: password,
			})
			.then(function (response) {
				let data = response.data;
				dispatch(updateId(data.id));
				dispatch(updateName(data.nome));
				dispatch(updateCpf(data.cpf));
				dispatch(updateEmail(data.email));
				dispatch(updateBirthDate(data.dataNascimento));
				dispatch(updateAddress(data.endereco));
				dispatch(updatePhone(data.telefone));

				navigate("/");
			})
			.catch(function (error) {
				console.log(error);
			});
	};
	/*
	useEffect(() => {
		console.log("id:", id);
		console.log("name:", name);
		console.log("cpf:", cpf);
		console.log("birthDate:", birthDate);
		console.log("address:", address);
		console.log("phone:", phone);
		console.log("email:", emailstore);
		console.log("password:", passwordstore);
	}, [id, nome, cpf, birthDate, address, phone, emailstore, passwordstore]);
	*/
	return (
		<div
			className='min-h-screen flex flex-col flex-1 justify-center items-center'
			style={{
				backgroundImage:
					"url('https://images.unsplash.com/photo-1570347929626-2bbc8032d98b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1471&q=80')",
				backgroundRepeat: "no-repeat",
				backgroundSize: "cover",
			}}
		>
			<div className='font-inter px-32 py-8 bg-white rounded-lg'>
				<div className='sm:mx-auto sm:w-full sm:max-w-sm'>
					<img
						className='mx-auto h-24 w-auto'
						src={vetConnectLogo}
						alt='VetConnect logo'
					/>
					<h2 className='mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900'>
						Entre com a sua conta
					</h2>
				</div>

				<div className='mt-10 sm:mx-auto sm:w-full sm:max-w-sm'>
					<form className='space-y-6' action='#' method='POST'>
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
									className='block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6'
								/>
							</div>
						</div>

						<div>
							<ButtonPrimary
								text='Cadastrar'
								width='w-full'
								onClickFunction={postSignIn}
							/>
						</div>
					</form>

					<p className='mt-10 text-center text-sm text-gray-500'>
						Não tem uma conta?{" "}
						<a
							href='/signup'
							className='font-semibold leading-6 text-vetConnectPrimaryGreen hover:text-vetConnectSecundaryGreen'
						>
							Crie uma aqui
						</a>
					</p>
				</div>
			</div>
		</div>
	);
}
