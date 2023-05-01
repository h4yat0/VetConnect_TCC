import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'

import './index.css'
import Navbar from './components/shared/Navbar'
import Footer from './components/shared/Footer'
import ContentHome from './components/content/ContentHome'


ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <Navbar/>
    <ContentHome/>
    <Footer/>
  </React.StrictMode>,
)
