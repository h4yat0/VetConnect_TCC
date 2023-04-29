function Navbar() {
  return (
   <header>
      <div className="navbar">
        <div className="logo"><a href="#">VetConnect</a></div>
        <ul className="itens">
          <li><a href="#">Home</a></li>
          <li><a href="#">Sobre</a></li>
          <li><a href="#">Serviços</a></li>
          <li><a href="#">Contato</a></li>
          <li><a href="#">Agendamento</a></li>
        </ul>
        <a href="#" className="navbar_btn">Seja Cliente</a>
        <div className="toogle_btn">
          
        </div> 
      </div>
   </header>
  );
}