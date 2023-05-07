import { Outlet } from "react-router-dom";
import Navbar from "../components/shared/Navbar";
import Footer from "../components/shared/Footer";

import "../styles/global.css";

export default function Root() {
	return (
		<div className='min-h-screen'>
			<Navbar />
			<Outlet />
			<Footer />
		</div>
	);
}
