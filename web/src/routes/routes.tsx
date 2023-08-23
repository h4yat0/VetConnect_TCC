import { createBrowserRouter } from "react-router-dom";

import Root from "./root";
import ErrorPage from "../pages/ErrorPage";

import Home from "../pages/Home";
import SignIn from "../pages/SignIn";
import SignUp from "../pages/SignUp";
import UnitPage from "../pages/UnitPage";
import UnitListPage from "../pages/UnitListPage";
import UserClientPage from "../pages/UserClientPage";
import Animals from "../pages/Animals";
import Unauthorized from "../pages/Unauthorized";
import Prontuario from "../pages/Prontuario";
import RequireAuthorization from "../components/security/RequireAuthorization";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <ErrorPage />,
    children: [
      {
        element: <RequireAuthorization allowedRoles={[0]} />,
        children: [
          {
            path: "user/client",
            element: <UserClientPage />,
          },
          {
            path: "animal",
            element: <Animals />,
          },
          {
            path: "animal",
            element: <Prontuario />,
          },
        ],
      },
      {
        path: "unidades",
        element: <UnitListPage />,
      },
      {
        path: "unidades/:id",
        element: <UnitPage />,
      },
      {
        path: "/",
        element: <Home />,
      },
    ],
  },
  {
    path: "/signin",
    element: <SignIn />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/signup",
    element: <SignUp />,
    errorElement: <ErrorPage />,
  },
  {
    path: "/unauthorized",
    element: <Unauthorized />,
  },
]);
