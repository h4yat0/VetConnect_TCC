import { configureStore, createSlice, PayloadAction } from "@reduxjs/toolkit";

interface ClientState {
  id: number;
  name: string;
  cpf: string;
  birthDate: string;
  phone: string;
  email: string;
  password: string;
  accessToken: string;
  roles: string[];
  animals: Animal[];
  streetName: string;
  bairro: string;
  city: string;
  estado: string;
  complemento: string;
  streetNumber: string;
  cep: string;
}

interface Animal {
  id: number,
  clientId: number;
  name: string;
  color: string;
  race: string;
  birthDate: string;
  weigth: string;
  size: string;
  specie: string;
  sex: string;
}

const initialState: ClientState = {
  id: -1,
  name: "",
  birthDate: "",
  cpf: "",
  streetName: "",
  bairro: "",
  city: "",
  estado: "",
  complemento: "",
  streetNumber: "",
  cep: "",
  phone: "",
  email: "",
  password: "",
  accessToken: "",
  roles: [],
  animals: [
    {
      id: -1,
      clientId: -1,
      name: "",
      color: "",
      race: "",
      birthDate: "",
      weigth: "",
      size: "",
      specie: "",
      sex: "",
    },
  ],
};

const clientSlice = createSlice({
  name: "client",
  initialState,
  reducers: {
    updateAccessToken: (state, action: PayloadAction<string>) => {
      state.accessToken = action.payload;
    },
    updateId: (state, action: PayloadAction<number>) => {
      state.id = action.payload;
    },
    updateName: (state, action: PayloadAction<string>) => {
      state.name = action.payload;
    },
    updateBirthDate: (state, action: PayloadAction<string>) => {
      state.birthDate = action.payload;
    },
    updateCpf: (state, action: PayloadAction<string>) => {
      state.cpf = action.payload;
    },

    updatestreetName: (state, action: PayloadAction<string>) => {
      state.streetName = action.payload;
    },
    updateBairro: (state, action: PayloadAction<string>) => {
      state.bairro = action.payload;
    },
    updateCity: (state, action: PayloadAction<string>) => {
      state.city = action.payload;
    },
    updateEstado: (state, action: PayloadAction<string>) => {
      state.estado = action.payload;
    },
    updateComplemento: (state, action: PayloadAction<string>) => {
      state.complemento = action.payload;
    },
    updateStreetNumber: (state, action: PayloadAction<string>) => {
      state.streetNumber = action.payload;
    },
    updateCep: (state, action: PayloadAction<string>) => {
      state.cep = action.payload;
    },
    updatePhone: (state, action: PayloadAction<string>) => {
      state.phone = action.payload;
    },
    updateEmail: (state, action: PayloadAction<string>) => {
      state.email = action.payload;
    },
    updatePassword: (state, action: PayloadAction<string>) => {
      state.password = action.payload;
    },
    updateRoles: (state, action: PayloadAction<string[]>) => {
      state.roles = action.payload;
    },
    updateAnimals: (state, action: PayloadAction<Animal[]>) => {
      state.animals = action.payload;
    },
  },
});

export const {
  updateId,
  updateName,
  updateBirthDate,
  updateCpf,
  updatestreetName,
  updateBairro,
  updateCity,
  updateEstado,
  updateComplemento,
  updateStreetNumber,
  updateCep,
  updatePhone,
  updateEmail,
  updatePassword,
  updateAccessToken,
  updateRoles,
  updateAnimals,
} = clientSlice.actions;

export const getId = (state: { client: ClientState }) => state.client.id;
export const getName = (state: { client: ClientState }) => state.client.name;
export const getPhone = (state: { client: ClientState }) => state.client.phone;
export const getEmail = (state: { client: ClientState }) => state.client.email;
export const getCpf = (state: { client: ClientState }) => state.client.cpf;
export const getBirthDate = (state: { client: ClientState }) =>
  state.client.birthDate;

export const getStreetName = (state: { client: ClientState }) =>
  state.client.streetName;
export const getBairro = (state: { client: ClientState }) => state.client.bairro;
export const getCity = (state: { client: ClientState }) => state.client.city;
export const getEstado = (state: { client: ClientState }) => state.client.estado;
export const getComplemento = (state: { client: ClientState }) =>
  state.client.complemento;
export const getStreetNumber = (state: { client: ClientState }) =>
  state.client.streetNumber;
export const getCep = (state: { client: ClientState }) => state.client.cep;

export const getPassword = (state: { client: ClientState }) => state.client.password;
export const getAccessToken = (state: { client: ClientState }) =>
  state.client.accessToken;
export const getRoles = (state: { client: ClientState }) => state.client.roles;
export const getAnimals = (state: { client: ClientState }) => state.client.animals;

export default clientSlice.reducer;
