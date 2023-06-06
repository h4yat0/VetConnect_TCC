import { configureStore } from "@reduxjs/toolkit";
import clientReducer from "./client";

const store = configureStore({
	reducer: {
		client: clientReducer,
	},
});

export default store;

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
