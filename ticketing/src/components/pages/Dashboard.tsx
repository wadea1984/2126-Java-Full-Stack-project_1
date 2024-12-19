import React from 'react'
import { useUserContext } from '../context/userContext';
import { useNavigate } from 'react-router-dom';


function Dashboard() {
  const { username, password, id, setUsername, setPassword, setId, logout } = useUserContext();
  const navigate = useNavigate();

 

  const goToViewTickets= (): void => {
    navigate('/viewTickets');
  };
  const goToCreateTickets = (): void => {
   
    navigate('/createTicket');
  };
  return (
    (
      <div className="mainContainer">
      <div className="titleContainer">
        <div><b>Welcome to the Dashboard {id}  {username} {password}</b></div>
      </div>
    
      <div className="buttonContainer">
        <input className="button" type="button" value="Create a Ticket" onClick={goToCreateTickets}/>
        <input className="button" type="button" value="View Tickets"onClick={goToViewTickets} />
      </div>
    </div>
    
  ))
  
}

export default Dashboard