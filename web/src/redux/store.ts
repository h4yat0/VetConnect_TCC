import { configureStore } from "@reduxjs/toolkit";
import clientReducer from "./client";
import unitsReducer from "./unit"
import funcionarioReducer from './funcionario'

const store = configureStore({
  reducer: {
    client: clientReducer,
    units: unitsReducer,
    funcionario: funcionarioReducer,
  },
});

export default store;

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
