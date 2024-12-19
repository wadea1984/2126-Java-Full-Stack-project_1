import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";

import LoginPage from './components/pages/LoginPage';
import RegisterPage from './components/pages/RegisterPage';
import Dashboard from './components/pages/Dashboard';
import { UserContextProvider } from './components/context/userContext';
import CreateTicket from './components/pages/CreateTicket';
import ViewTickets from './components/pages/ViewTickets';
import NavBar from './components/NavBar/NavBar';



function App() {
  return (
    <BrowserRouter>
    <UserContextProvider>
      
      <div className="App">
      <NavBar/>
        <Routes>
         
          <Route path="/" element={<LoginPage />} />
          <Route path="/Register" element={<RegisterPage />} />
          
          <Route path="/Dashboard" element={<Dashboard />} />
          <Route path="CreateTicket" element={<CreateTicket/>}/>
          <Route path="ViewTickets" element={<ViewTickets/>}/>
          
        </Routes>
      </div>
    
    </UserContextProvider>
    </BrowserRouter>
  );
}

export default App;
