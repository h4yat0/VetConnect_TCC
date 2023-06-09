import { createBrowserRouter } from "react-router-dom";

import Root from "./root";
import ErrorPage from "../pages/ErrorPage";

import Home from "../pages/Home";
import SignIn from "../pages/SignIn";
import SignUp from "../pages/SignUp";
import UnitPage from "../pages/UnitPage";
import UnitListPage from "../pages/UnitListPage";
import UserClientPage from "../pages/UserClientPage";

export const router = createBrowserRouter([
	{
		path: "/",
		element: <Root />,
		errorElement: <ErrorPage />,
		children: [
			{
				path: "/",
				element: <Home />,
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
				path: "user/client",
				element: <UserClientPage />,
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
]);
