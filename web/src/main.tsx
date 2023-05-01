import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'

import './index.css'
import Navbar from './Components/shared/Navbar'
import Footer from './Components/shared/Footer'
import ContentHome from './Components/content/ContentHome'


ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <Navbar/>
    <ContentHome/>
    <Footer/>
  </React.StrictMode>,
)
