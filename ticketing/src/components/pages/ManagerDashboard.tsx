import React from 'react'
import { useUserContext } from '../context/userContext';
import { useNavigate } from 'react-router-dom';


function ManagerDashboard() {
  const { username, password, id, setUsername, setPassword, setId, logout,role } = useUserContext();
  const navigate = useNavigate();

 

  const goToProcessTickets= (): void => {
    navigate('/TicketManager');
  };
  
  return (
    (
      <div className="mainContainer">
      <div className="titleContainer">
        <div><b>Welcome to the manager dashboard {username} {role}</b></div>
      </div>
    
      <div className="buttonContainer">
        <input className="button" type="button" value="Process Tickets"onClick={goToProcessTickets} />
      </div>
    </div>
    
  ))
  
}

export default ManagerDashboard