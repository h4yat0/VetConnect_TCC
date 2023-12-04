import { configureStore, createSlice, PayloadAction } from "@reduxjs/toolkit";

interface FuncionarioState {
  id: number;
  name: string;
  accessToken: string;
  refreshToken: string;
  roles: string[];
  schedules: Schedules[];
}


interface ApiSchedules {
  id: number;
  cliente: string;
  animal: string;
  unidade: string;
  servico: string;
  dataAgendada: string;
  horaAgendada: string;
  valorAgendado: number;
  status: number;
  observacao: string;
}

interface Schedules {
  id: number;
  client: string;
  animal: string;
  unit: string;
  service: string;
  scheduledDate: string;
  scheduledTime: string;
  scheduledValue: number;
  status: number;
  observation: string;
}

function mapApiSchedulesToSchedules(apiServices: ApiSchedules[]): Schedules[] {
  return apiServices.map((apiService) => ({
    id: apiService.id,
    client: apiService.cliente,
    animal: apiService.animal,
    unit: apiService.unidade,
    service: apiService.servico,
    scheduledDate: apiService.dataAgendada,
    scheduledTime: apiService.horaAgendada,
    scheduledValue: apiService.valorAgendado,
    status: apiService.status,
    observation: apiService.observacao,
  }));
}

const initialState: FuncionarioState = {
  id: -1,
  name: "",
  accessToken: "",
  refreshToken: "",
  roles: [],
  schedules: [],
};

const funcionarioSlice = createSlice({
  name: "funcionario",
  initialState,
  reducers: {
    updateAccessToken: (state, action: PayloadAction<string>) => {
      state.accessToken = action.payload;
    },
    updateRefreshToken: (state, action: PayloadAction<string>) => {
      state.refreshToken = action.payload;
    },
    updateId: (state, action: PayloadAction<number>) => {
      state.id = action.payload;
    },
    updateName: (state, action: PayloadAction<string>) => {
      state.name = action.payload;
    },
    updateRoles: (state, action: PayloadAction<string[]>) => {
      state.roles = action.payload;
    },
    updateSchedules: (state, action: PayloadAction<ApiSchedules[]>) => {
      state.schedules = mapApiSchedulesToSchedules(action.payload);
    },
  },
});

export const {
  updateId,
  updateName,
  updateAccessToken,
  updateRefreshToken,
  updateRoles,
  updateSchedules,
} = funcionarioSlice.actions;

export const getId = (state: { client: FuncionarioState }) => state.client.id;
export const getName = (state: { client: FuncionarioState }) => state.client.name;
export const getAccessToken = (state: { client: FuncionarioState }) =>
  state.client.accessToken;
export const getRefreshToken = (state: { client: FuncionarioState }) =>
  state.client.refreshToken;
export const getRoles = (state: { client: FuncionarioState }) => state.client.roles;
export const getSchedules = (state: { client: FuncionarioState }) => state.client.schedules;

export default funcionarioSlice.reducer;
