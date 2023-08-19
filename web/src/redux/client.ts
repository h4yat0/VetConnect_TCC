import { configureStore, createSlice, PayloadAction } from "@reduxjs/toolkit";

interface ClientState {
  id: number;
  name: string;
  cpf: string;
  birthDate: string;
  address: string;
  phone: string;
  email: string;
  password: string;
  accessToken: string;
  roles: number[];
}

const initialState: ClientState = {
  id: -1,
  name: "",
  birthDate: "",
  cpf: "",
  address: "",
  phone: "",
  email: "",
  password: "",
  accessToken: "",
  roles: [],
};

const clientSlice = createSlice({
  name: "client",
  initialState,
  reducers: {
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
    updateAddress: (state, action: PayloadAction<string>) => {
      state.address = action.payload;
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
    updateAccessToken: (state, action: PayloadAction<string>) => {
      state.accessToken = action.payload;
    },
    updateRoles: (state, action: PayloadAction<number[]>) => {
      state.roles = action.payload;
    },
  },
});

export const {
  updateId,
  updateName,
  updateBirthDate,
  updateCpf,
  updateAddress,
  updatePhone,
  updateEmail,
  updatePassword,
  updateAccessToken,
  updateRoles,
} = clientSlice.actions;

export const getId = (state: { client: ClientState }) => state.client.id;
export const getName = (state: { client: ClientState }) => state.client.name;
export const getPhone = (state: { client: ClientState }) => state.client.phone;
export const getEmail = (state: { client: ClientState }) => state.client.email;
export const getCpf = (state: { client: ClientState }) => state.client.cpf;
export const getBirthDate = (state: { client: ClientState }) =>
  state.client.birthDate;
export const getAddress = (state: { client: ClientState }) => state.client.address;
export const getPassword = (state: { client: ClientState }) => state.client.password;
export const getAccessToken = (state: { client: ClientState }) =>
  state.client.accessToken;
export const getRoles = (state: { client: ClientState }) => state.client.roles;

export default clientSlice.reducer;