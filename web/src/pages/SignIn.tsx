import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import api from "../api/axios";

import ButtonPrimary from "../components/buttons/ButtonPrimary";
import vetConnectLogo from "../assets/svgs/vetConnectLogo.svg";
import Alert from "../components/shared/Alert";

import {
  updateAddress,
  updateBirthDate,
  updateCpf,
  updateEmail,
  updateName,
  updatePhone,
  updateId,
  updatePassword,
  updateAccessToken,
  updateRoles,
} from "../redux/client";

const LOGIN_URL = "cliente/v1/login";

export default function SignIn() {
  const userRef = useRef<HTMLInputElement | null>(null);
  const errRef = useRef<HTMLInputElement | null>(null);

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.from?.pathname || "/";

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    if (userRef.current) userRef.current.focus();
  }, []);

  useEffect(() => {
    setErrorMessage("");
  }, [email, password]);

  useEffect(() => {}, [errorMessage]);

  const postSignIn = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    let reponse = await api
      .post(LOGIN_URL, {
        email: email,
        senha: password,
      })
      .then(function (response) {
        let data = response.data;

        const accessToken = data?.accessToken;
        const roles = data?.roles; // PRECISA SER REVISTO

        dispatch(updateId(data.id));
        dispatch(updateName(data.nome));
        dispatch(updateCpf(data.cpf));
        dispatch(updateEmail(data.email));
        dispatch(updateBirthDate(data.dataNascimento));
        dispatch(updateAddress(data.endereco));
        dispatch(updatePhone(data.telefone));
        dispatch(updatePassword(data.senha));
        dispatch(updateAccessToken(data.accessToken));
        dispatch(updateRoles([0]));

        navigate(from, { replace: true });
      })
      .catch(function (error) {
        console.log(error);
        if (!error?.message) {
          setErrorMessage("No server response");
        } else {
          setErrorMessage("Email ou senha incorreto(s)");
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
      <div className="font-inter px-14 py-8 bg-white rounded-lg">
        <div className="sm:mx-auto sm:w-full sm:max-w-sm">
          <img
            className="mx-auto h-24 w-auto"
            src={vetConnectLogo}
            alt="VetConnect logo"
          />
          <h2 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">
            Entre com a sua conta
          </h2>
        </div>

        <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-lg lg:w-96 lg:mt-4">
          {errorMessage ? (
            <Alert title={errorMessage} description="" type="danger" />
          ) : (
            ""
          )}
          <form className="space-y-6" onSubmit={postSignIn} method="POST">
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
                  ref={userRef}
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
                  autoComplete="off"
                  required
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            <div>
              <ButtonPrimary text="Entrar" width="w-full" type="submit" />
            </div>
          </form>

          <p className="mt-10 text-center text-sm text-gray-500">
            Não tem uma conta?{" "}
            <a
              href="/signup"
              className="font-semibold leading-6 text-vetConnectPrimaryGreen hover:text-vetConnectSecundaryGreen"
            >
              Crie uma aqui
            </a>
          </p>
        </div>
      </div>
    </div>
  );
}
