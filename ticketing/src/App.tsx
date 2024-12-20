import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Outlet, Route, Routes } from "react-router-dom";

import LoginPage from './components/pages/LoginPage';
import RegisterPage from './components/pages/RegisterPage';
import Dashboard from './components/pages/Dashboard';
import { UserContextProvider } from './components/context/userContext';
import CreateTicket from './components/pages/CreateTicket';
import ViewTickets from './components/pages/ViewTickets';
import NavBar from './components/NavBar/NavBar';
import TicketManager from './components/pages/TicketManager';
import RouteGuard from './components/routeGuard/routeGuard';


function App() {
  
  return (
    <BrowserRouter>
      <UserContextProvider>
        <div className="App">
          <NavBar />
          <Routes>
            <Route path="/" element={<LoginPage />} />
            <Route path="/Register" element={<RegisterPage />} />
            <Route
              path="/Dashboard"
              element={
                <RouteGuard>
                  <Dashboard />
                </RouteGuard>
              }
            />
            <Route
              path="/CreateTicket"
              element={
                <RouteGuard>
                  <CreateTicket />
                </RouteGuard>
              }
            />
            <Route
              path="/ViewTickets"
              element={
                <RouteGuard>
                  <ViewTickets />
                </RouteGuard>
              }
            />
            <Route
              path="/TicketManager"
              element={
                <RouteGuard>
                  <TicketManager />
                </RouteGuard>
              }
            />
            
             
            
          </Routes>
        </div>
      </UserContextProvider>
    </BrowserRouter>
  );
}

export default App;

