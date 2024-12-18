import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";

import LoginPage from './components/pages/LoginPage';
import RegisterPage from './components/pages/RegisterPage';
import Dashboard from './components/pages/Dashboard';
import { UserContextProvider } from './components/context/userContext';

function App() {
  return (
    <BrowserRouter>
    <UserContextProvider>
    
      <div className="App">
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/Register" element={<RegisterPage />} />
          <Route path="/Dashboard" element={<Dashboard />} />
        </Routes>
      </div>
   
    </UserContextProvider>
    </BrowserRouter>
  );
}

export default App;
