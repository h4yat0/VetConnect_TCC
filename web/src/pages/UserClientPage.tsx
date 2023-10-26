import { useNavigate } from "react-router-dom";
import Cleave from "cleave.js/react";
import "../modules/cleave-phone.br.js";

import ButtonPrimary from "../components/buttons/ButtonPrimary.js";
import ButtonSecundary from "../components/buttons/ButtonSecundary.js";

import { useDispatch, useSelector } from "react-redux";
import {
  updateBirthDate,
  updateCpf,
  updateEmail,
  updateName,
  updatePhone,
  updateId,
  getBirthDate,
  getCpf,
  getEmail,
  getId,
  getName,
  getPassword,
  getPhone,
  updatePassword,
  getAccessToken,
  getStreetName,
  getStreetNumber,
  getBairro,
  getCity,
  getEstado,
  getComplemento,
  getCep,
  updatestreetName,
  updateBairro,
  updateCity,
  updateComplemento,
  updateEstado,
  updateStreetNumber,
  updateCep,
  getAnimals,
} from "../redux/client.js";

import api from "../api/axios.js";

import { useEffect, useState, Fragment } from "react";
import ButtonDanger from "../components/buttons/ButtonDanger.js";
import { Link, Navigate } from "react-router-dom";
import RegisterAnimalModal from "../components/AnimalModal.js";

function sanitizeString(str: string): string {
  return str.replace(/[.\-\s]/g, "");
}

export default function UserClientPage() {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [editDisabled, setEditDisabled] = useState(true);

  const handleSetEditEnabled = () => {
    setEditDisabled(!editDisabled);
  };
  const [isOpen, setIsOpen] = useState(false);

  const [name, setName] = useState("");
  const [cpf, setCpf] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [streetName, setStreetName] = useState("");
  const [streetNumber, setStreetNumber] = useState("");
  const [bairro, setBairro] = useState("");
  const [city, setCity] = useState("");
  const [estado, setEstado] = useState("");
  const [complemento, setComplemento] = useState("");
  const [cep, setCep] = useState("");

  const idStore = useSelector(getId);
  const nameStore = useSelector(getName);
  const cpfStore = useSelector(getCpf);
  const phoneStore = useSelector(getPhone);
  const emailStore = useSelector(getEmail);
  const streetNameStore = useSelector(getStreetName);
  const streetNumberStore = useSelector(getStreetNumber);
  const bairroStore = useSelector(getBairro);
  const cityStore = useSelector(getCity);
  const estadoStore = useSelector(getEstado);
  const complementoStore = useSelector(getComplemento);
  const cepStore = useSelector(getCep);
  const birthDateStore = useSelector(getBirthDate);
  const passwordStore = useSelector(getPassword);
  const accessToken = useSelector(getAccessToken);
  const animals = useSelector(getAnimals);

  useEffect(() => {
    setName(nameStore);
    setCpf(cpfStore);
    setBirthDate(birthDateStore);
    setPhone(phoneStore);
    setEmail(emailStore);
    setStreetName(nameStore);
    setStreetNumber(streetNumberStore);
    setBairro(bairroStore);
    setCity(cityStore);
    setEstado(estadoStore);
    setComplemento(complementoStore);
    setCep(cepStore);
  }, []);

  function isEmptyString(str: string) {
    return str.trim() === "";
  }

  const postEditedInformation = async () => {
    let reponse = await api
      .put(
        `api/cliente/v1/update/${idStore}`,
        {
          nome: isEmptyString(name) ? nameStore : name,
          dataNascimento: isEmptyString(birthDate) ? birthDateStore : birthDate,
          cpf: isEmptyString(sanitizeString(cpf)) ? cpfStore : sanitizeString(cpf),

          rua: isEmptyString(streetName) ? streetNameStore : streetName,
          bairro: isEmptyString(bairro) ? bairroStore : bairro,
          cidade: isEmptyString(city) ? cityStore : city,
          estado: isEmptyString(estado) ? estadoStore : estado,
          complemento: isEmptyString(complemento) ? complementoStore : complemento,
          numero: isEmptyString(streetNumber) ? streetNumberStore : streetNumber,
          cep: isEmptyString(cep) ? cepStore : cep,

          telefone: isEmptyString(sanitizeString(phone))
            ? phoneStore
            : sanitizeString(phone),

          email: isEmptyString(email) ? emailStore : email,
          // senha: isEmptyString(password) ? passwordStore : password,
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      )
      .then(function (response) {
        let data = response.data;

        dispatch(updateName(data.nome));
        dispatch(updateCpf(data.cpf));
        dispatch(updateEmail(data.email));
        dispatch(updateBirthDate(data.dataNascimento));

        dispatch(updatestreetName(data.rua));
        dispatch(updateBairro(data.bairro));
        dispatch(updateCity(data.cidade));
        dispatch(updateEstado(data.estado));
        dispatch(updateComplemento(data.complemento));
        dispatch(updateStreetNumber(data.numero));
        dispatch(updateCep(data.cep));

        dispatch(updatePhone(data.telefone));
        // dispatch(updatePassword(data.senha));

        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  const deleteAccount = async () => {
    let reponse = await api
      .delete(`api/cliente/v1/delete/${idStore}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then(function (response) {
        console.log(response);
        updateId(-1);
        window.location.href = "/";
        navigate("/");
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <>
      <RegisterAnimalModal
        type="register"
        animalsStore={animals}
        animalId={0}
        isOpen={isOpen}
        setIsOpen={setIsOpen}
      />
      <div className="w-full mb-10">
        <div className="flex justify-between items-center px-32 py-10">
          <span className="font-bold text-2xl">Perfil</span>

          <ButtonPrimary
            text="Cadastrar Animal"
            onClickFunction={() => setIsOpen(!isOpen)}
          />
        </div>
        <div className="mx-24  px-12 py-10 border-2 rounded-lg ">
          <div className="flex justify-between items-center mb-8">
            <div className="flex justify-between items-center gap-4">
              <div className="w-10 h-10 bg-slate-800 rounded-full"></div>
              <span className="font-bold text-xl">{nameStore}</span>
            </div>
            <ButtonSecundary
              text="Editar informações"
              onClickFunction={handleSetEditEnabled}
            />
          </div>

          <form className="space-y-6" action="#" method="POST">
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
                  required
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  disabled={editDisabled}
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
                  required
                  value={cpf}
                  onChange={(e) => setCpf(e.target.value)}
                  disabled={editDisabled}
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
                  required
                  value={birthDate}
                  onChange={(e) => setBirthDate(e.target.value)}
                  disabled={editDisabled}
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
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                  disabled={editDisabled}
                  options={{ phone: true, phoneRegionCode: "br" }}
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
                  disabled={editDisabled}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div>

            {/* <div>
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
                  disabled={editDisabled}
                  className="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-vetConnectPrimaryGreen sm:text-sm sm:leading-6"
                />
              </div>
            </div> */}

            {!editDisabled ? (
              <>
                <div>
                  <ButtonPrimary
                    text="Alterar dados"
                    width="w-full"
                    disabled={editDisabled}
                    onClickFunction={postEditedInformation}
                  />
                </div>
                <div>
                  <ButtonDanger
                    text="Deletar conta"
                    disabled={editDisabled}
                    onClickFunction={deleteAccount}
                  />
                </div>
              </>
            ) : (
              ""
            )}
          </form>
        </div>
      </div>
    </>
  );
}
