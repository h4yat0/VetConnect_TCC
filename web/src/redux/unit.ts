import { configureStore, createSlice, PayloadAction } from "@reduxjs/toolkit";

interface UnitState {
  id: number;
  name: string;
  cnpj: string;
  cep: string;
  bairro: string;
  rua: string;
  numero: string;
  estado: string;
  cidade: string;
  complemento: string;
  contact: string;
  specialty: string;
  images: string[];
  services: Service[];
  openingTime: string;
  closingTime: string;
}

interface Service {
  id: number;
  name: string;
  price: number;
}

interface ApiUnit {
  id: number;
  nome: string;
  cnpj: string;
  cep: string;
  bairro: string;
  rua: string;
  numero: string;
  cidade: string;
  complemento: string;
  contato: string;
  especialidade: string;
  estado: string;
  imagem: string[];
  servicos: ApiService[];
  horarioAbertura: string;
  horarioFechamento: string;
}

interface ApiService {
  id: number;
  nome: string;
  preco: number;
}

function mapApiUnitsToUnits(apiUnits: ApiUnit[]): UnitState[] {
  return apiUnits.map((apiUnit) => mapApiUnitToUnit(apiUnit));
}

function mapApiUnitToUnit(apiUnit: ApiUnit): UnitState {
  return {
    id: apiUnit.id,
    name: apiUnit.nome,
    cnpj: apiUnit.cnpj,
    cep: apiUnit.cep,
    bairro: apiUnit.bairro,
    rua: apiUnit.rua,
    numero: apiUnit.numero,
    estado: apiUnit.estado,
    cidade: apiUnit.cidade,
    complemento: apiUnit.complemento,
    contact: apiUnit.contato,
    specialty: apiUnit.especialidade,
    images: apiUnit.imagem,
    services: mapApiServicesToServices(apiUnit.servicos),
    openingTime: apiUnit.horarioAbertura,
    closingTime: apiUnit.horarioFechamento,
  };
}

function mapApiServicesToServices(apiServices: ApiService[]): Service[] {
  return apiServices.map((apiService) => ({
    id: apiService.id,
    name: apiService.nome,
    price: apiService.preco,
  }));
}

const initialState: UnitState[] = [
//   {
//   id: -1,
//   name: "",
//   cnpj: "",
//   cep: "",
//   bairro: "",
//   rua: "",
//   numero: "",
//   estado: "",
//   cidade: "",
//   complemento: "",
//   contact: "",
//   specialty: "",
//   images: [""],
//   services: [{ id: -1, name: "", price: 0 }],
//   openingTime: "",
//   closingTime: "",
// }
];

const unitSlice = createSlice({
  name: "units",
  initialState,
  reducers: {
    updateUnits: (state, action: PayloadAction<ApiUnit[]>) => {
      let newState = mapApiUnitsToUnits(action.payload)
      return newState;
    },

    // updateId: (state, action: PayloadAction<number>) => {
    //   state.id = action.payload;
    // },
    // updateName: (state, action: PayloadAction<string>) => {
    //   state.name = action.payload;
    // },
    // updateCnpj: (state, action: PayloadAction<string>) => {
    //   state.cnpj = action.payload;
    // },
    // updateCep: (state, action: PayloadAction<string>) => {
    //   state.cep = action.payload;
    // },
    // updateBairro: (state, action: PayloadAction<string>) => {
    //   state.bairro = action.payload;
    // },
    // updateRua: (state, action: PayloadAction<string>) => {
    //   state.rua = action.payload;
    // },
    // updateNumero: (state, action: PayloadAction<string>) => {
    //   state.numero = action.payload;
    // },
    // updateEstado: (state, action: PayloadAction<string>) => {
    //   state.estado = action.payload;
    // },
    // updateCidade: (state, action: PayloadAction<string>) => {
    //   state.cidade = action.payload;
    // },
    // updateComplemento: (state, action: PayloadAction<string>) => {
    //   state.complemento = action.payload;
    // },
    // updateContact: (state, action: PayloadAction<string>) => {
    //   state.contact = action.payload;
    // },
    // updateImages: (state, action: PayloadAction<string[]>) => {
    //   state.image = action.payload;
    // },
    // updateServices: (state, action: PayloadAction<Service[]>) => {
    //   state.services = action.payload;
    // },
    // updateOpeningTime: (state, action: PayloadAction<string>) => {
    //   state.openingTime = action.payload;
    // },
    // updateClosingTime: (state, action: PayloadAction<string>) => {
    //   state.closingTime = action.payload;
    // },
  },
});

export const {
  updateUnits,
  // updateId,
  // updateName,
  // updateCnpj,
  // updateCep,
  // updateBairro,
  // updateRua,
  // updateNumero,
  // updateEstado,
  // updateCidade,
  // updateComplemento,
  // updateContact,
  // updateImages,
  // updateServices,
  // updateOpeningTime,
  // updateClosingTime
} = unitSlice.actions;

export const getUnits = (state: { units: UnitState[] }) => state.units;
export const getUnit = (state: { units: UnitState[] }, unitId: number) =>
  state.units.find((unit) => unit.id === unitId);
  
// export const getId = (state: { unit: UnitState }) => state.unit.id;
// export const getName = (state: { unit: UnitState }) => state.unit.name;
// export const getCnpj = (state: { unit: UnitState }) => state.unit.cnpj;
// export const getCep = (state: { unit: UnitState }) => state.unit.cep;
// export const getBairro = (state: { unit: UnitState }) => state.unit.bairro;
// export const getRua = (state: { client: UnitState }) => state.client.rua;
// export const getNumero = (state: { client: UnitState }) =>
//   state.client.numero;
// export const getEstado = (state: { client: UnitState }) => state.client.estado;
// export const getCidade= (state: { client: UnitState }) => state.client.cidade;
// export const getComplemento = (state: { client: UnitState }) =>
//   state.client.complemento;
// export const getContact = (state: { client: UnitState }) => state.client.contact;
// export const getImages = (state: { client: UnitState }) => state.client.images;
// export const getServices = (state: { client: UnitState }) => state.client.services;
// export const getOpeningTime = (state: { client: UnitState }) => state.client.openingTime;
// export const getClosingTime = (state: { client: UnitState }) => state.client.closingTime;

export default unitSlice.reducer;
