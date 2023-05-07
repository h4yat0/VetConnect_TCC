import { createBrowserRouter } from "react-router-dom";

import Root from "./root";
import ErrorPage from "../pages/ErrorPage";

import Home from "../pages/Home";
import SignIn from "../pages/SignIn";
import SignUp from "../pages/SignUp";

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
		],
	},
	{
		path: "/signIn",
		element: <SignIn />,
		errorElement: <ErrorPage />,
	},
	{
		path: "/signup",
		element: <SignUp />,
		errorElement: <ErrorPage />,
	},
]);
