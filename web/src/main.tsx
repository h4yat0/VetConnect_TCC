import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'

import './index.css'
import Navbar from './Components/Navbar'
import Footer from './Components/Footer'


ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <Navbar/>
    <Footer/>
  </React.StrictMode>,
)
