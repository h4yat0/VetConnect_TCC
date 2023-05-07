import "../../styles/components/navbar.css";
import ButtonPrimary from "../buttons/ButtonSecundary";

import vetConnectLogo from "../../assets/svgs/vetConnectLogo.svg";

export default function Navbar() {
	return (
		<header>
			<div className='navbar'>
				<img src={vetConnectLogo} className='svgLogo' alt='SVG image' />
				<div className='logo'>
					<a href='#'>VetConnect</a>
				</div>
				<ul className='itens'>
					<li>
						<a href='#'>Home</a>
					</li>
					<li>
						<a href='#'>Sobre</a>
					</li>
					<li>
						<a href='#'>Serviços</a>
					</li>
					<li>
						<a href='#'>Contato</a>
					</li>
					<li>
						<a href='#'>Agendamento</a>
					</li>
				</ul>
				<a href='signup'>
					<ButtonPrimary text='Seja Cliente' />
				</a>
				<div className='toogle_btn'>
					<input type='checkbox' id='menu_checkbox' />
					<label htmlFor='menu_checkbox'>
						<div></div>
						<div></div>
						<div></div>
					</label>
					<div className='dropdown_menu'>
						<ul className=''>
							<li>
								<a href='#'>Home</a>
							</li>
							<li>
								<a href='#'>Sobre</a>
							</li>
							<li>
								<a href='#'>Serviços</a>
							</li>
							<li>
								<a href='#'>Contato</a>
							</li>
							<li>
								<a href='#'>Agendamento</a>
							</li>
							<li>
								<a href='#' className=''>
									Seja Cliente
								</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</header>
	);
}
