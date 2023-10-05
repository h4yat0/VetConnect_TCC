import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import Cleave from "cleave.js/react";
import "../modules/cleave-phone.br.js";
import Alert from "../components/shared/Alert";
import ButtonPrimary from "../components/buttons/ButtonPrimary";
import vetConnectLogo from "../assets/svgs/vetConnectLogo.svg";
import AgendamentoModal from "../components/AgendamentoModal";

function sanitizeString(str: string): string {
	return str.replace(/[.\-\s]/g, "");
}

const SIGNUP_URL = "api/cliente/v1/register";

export default function SignUp() {
	const errRef = useRef<HTMLInputElement | null>(null);

	const [name, setName] = useState("");
	const [cpf, setCpf] = useState("");
	const [birthDate, setBirthDate] = useState("");
	const [phone, setPhone] = useState("");
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [errorMessage, setErrorMessage] = useState("");
	const [streetName, setStreetName] = useState("");
	const [streetNumber, setStreetNumber] = useState("");
	const [bairro, setBairro] = useState("");
	const [city, setCity] = useState("");
	const [estado, setEstado] = useState("");
	const [complemento, setComplemento] = useState("");
	const [cep, setCep] = useState("");

	useEffect(() => {}, [errorMessage]);

	const navigate = useNavigate();

	const postSignUp = async (e: React.FormEvent<HTMLFormElement>) => {
		e.preventDefault();
		let reponse = await api
      .post(SIGNUP_URL, {
        nome: name,
        dataNascimento: birthDate,
        cpf: sanitizeString(cpf),
        telefone: sanitizeString(phone),
        email: email,
        senha: password,
        rua: streetName,
        bairro: bairro,
        cidade: city,
        estado: estado,
        complemento: complemento,
        numero: streetNumber,
        cep: sanitizeString(cep),
      })
      .then(function (response) {
        console.log(response);
        navigate("/");
      })
      .catch(function (error) {
        console.log(error);
        if (!error?.message) {
          setErrorMessage("No server response");
        } else {
          setErrorMessage(error.message);
        }
        errRef.current?.focus();
      });
	};

	return (
    <div
      className="min-h-screen flex flex-col flex-1 justify-center items-center"
      style={{
        backgroundImage:
          "url('https://images.unsplash.com/photo-1570347929626-2bbc8032d98b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1471&q=80')",
        backgroundRepeat: "no-repeat",
        backgroundSize: "cover",
      }}
    >
      <div className="font-inter px-32 py-8 my-20 bg-white rounded-lg">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
          <img
            className="mx-auto h-24 w-auto"
            src={vetConnectLogo}
            alt="VetConnect logo"
          />
          <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
            Faça seu cadastro
          </h2>
        </div>

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
          {errorMessage ? (
            <Alert title={errorMessage} description="" type="danger" />
          ) : (
            ""
          )}
          <form className="space-y-6" onSubmit={postSignUp} method="POST">
            <div>
              <label
                htmlFor="name"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Nome Completo
              </label>
              <div className="mt-2">
                <input
                  id="name"
                  name="name"
                  type="text"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  required
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <label
                htmlFor="cpf"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                CPF
              </label>
              <div className="mt-2">
                <Cleave
                  id="cpf"
                  name="cpf"
                  type="text"
                  options={{
                    delimiters: [".", ".", "-"],
                    blocks: [3, 3, 3, 2],
                  }}
                  value={cpf}
                  onChange={(e) => setCpf(e.target.value)}
                  required
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <label
                htmlFor="birthDate"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Data de Nascimento
              </label>
              <div className="mt-2">
                <input
                  id="birthDate"
                  name="birthDate"
                  type="date"
                  value={birthDate}
                  onChange={(e) => setBirthDate(e.target.value)}
                  required
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <label
                htmlFor="streetName"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Rua
              </label>
              <div className="mt-2">
                <input
                  id="streetName"
                  name="streetName"
                  type="text"
                  required
                  value={streetName}
                  onChange={(e) => setStreetName(e.target.value)}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>
            <div>
              <label
                htmlFor="streetNumber"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Número
              </label>
              <div className="mt-2">
                <input
                  id="streetNumber"
                  name="streetNumber"
                  type="text"
                  required
                  value={streetNumber}
                  onChange={(e) => setStreetNumber(e.target.value)}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>
            <div>
              <label
                htmlFor="bairro"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Bairro
              </label>
              <div className="mt-2">
                <input
                  id="bairro"
                  name="bairro"
                  type="text"
                  required
                  value={bairro}
                  onChange={(e) => setBairro(e.target.value)}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>
            <div>
              <label
                htmlFor="city"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Cidade
              </label>
              <div className="mt-2">
                <input
                  id="bairro"
                  name="city"
                  type="text"
                  required
                  value={city}
                  onChange={(e) => setCity(e.target.value)}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>
            <div>
              <label
                htmlFor="estado"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Estado
              </label>
              <div className="mt-2">
                <input
                  id="estado"
                  name="estado"
                  type="text"
                  required
                  value={estado}
                  onChange={(e) => setEstado(e.target.value)}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>
            <div>
              <label
                htmlFor="complemento"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Complemento
              </label>
              <div className="mt-2">
                <input
                  id="complemento"
                  name="complemento"
                  type="text"
                  value={complemento}
                  onChange={(e) => setComplemento(e.target.value)}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>
            <div>
              <label
                htmlFor="cep"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                CEP
              </label>
              <div className="mt-2">
                <Cleave
                  id="cep"
                  name="cep"
                  type="text"
                  options={{
                    delimiters: ["-"],
                    blocks: [5, 3],
                  }}
                  value={cep}
                  onChange={(e) => setCep(e.target.value)}
                  required
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <label
                htmlFor="phone"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Número de Telefone
              </label>
              <div className="mt-2">
                <Cleave
                  id="phone"
                  name="phone"
                  type="text"
                  required
                  options={{ phone: true, phoneRegionCode: "br" }}
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <label
                htmlFor="email"
                className="block text-sm font-medium leading-6 text-gray-900"
              >
                Email
              </label>
              <div className="mt-2">
                <input
                  id="email"
                  name="email"
                  type="email"
                  autoComplete="email"
                  required
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <div className="flex items-center justify-between">
                <label
                  htmlFor="password"
                  className="block text-sm font-medium leading-6 text-gray-900"
                >
                  Senha
                </label>
              </div>
              <div className="mt-2">
                <input
                  id="password"
                  name="password"
                  type="password"
                  autoComplete="current-password"
                  required
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <ButtonPrimary text="Cadastrar" width="w-full" type="submit" />
            </div>
          </form>

          <p className="mt-10 text-center text-sm text-gray-500">
            Já possui uma conta?{" "}
            <a
              href="/signin"
              className="font-semibold leading-6 text-vetConnectPrimaryGreen hover:text-vetConnectSecundaryGreen"
            >
              Entre aqui
            </a>
          </p>
        </div>
      </div>
    </div>
  );
}
