import { Outlet } from "react-router-dom";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

import "../styles/global.css";

export default function Root() {
	return (
		<div>
			<Navbar />
			<div className='min-h-screen mx-auto flex flex-col max-w-7xl px-6 lg:px-8'>
				<Outlet />
			</div>
		</div>
	);
}
