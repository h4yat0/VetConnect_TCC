import { configureStore, createSlice, PayloadAction } from "@reduxjs/toolkit";

interface ClientState {
	name: string;
	cpf: string;
	birthDate: string;
	address: string;
	phone: string;
	email: string;
	password: string;
}

const initialState: ClientState = {
	name: "",
	birthDate: "",
	cpf: "",
	address: "",
	phone: "",
	email: "",
	password: "",
};

const clientSlice = createSlice({
	name: "client",
	initialState,
	reducers: {
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
	},
});

const store = configureStore({
	reducer: {
		client: clientSlice.reducer,
	},
});

export const {
	updateName,
	updateBirthDate,
	updateCpf,
	updateAddress,
	updatePhone,
	updateEmail,
	updatePassword,
} = clientSlice.actions;

export default clientSlice.reducer;
