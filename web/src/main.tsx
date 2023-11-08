import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import { router } from "./routes/routes";
import store from "./redux/store";
import { Provider } from "react-redux";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider, ptBR } from "@mui/x-date-pickers";
import "dayjs/locale/pt-br";

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <Provider store={store}>
      <LocalizationProvider
        dateAdapter={AdapterDayjs}
        localeText={ptBR.components.MuiLocalizationProvider.defaultProps.localeText}
        adapterLocale="pt-br"
      >
        <RouterProvider router={router} />
      </LocalizationProvider>
    </Provider>
  </React.StrictMode>
);
