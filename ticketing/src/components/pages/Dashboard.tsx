import React from 'react'
import { useUserContext } from '../context/userContext';
import { useNavigate } from 'react-router-dom';


function Dashboard() {
  const { username, password, id, setUsername, setPassword, setId, logout,role } = useUserContext();
  const navigate = useNavigate();

  const goToProcessTickets = (): void => {
    navigate('/TicketManager');
  };

  const goToViewTickets= (): void => {
    navigate('/viewTickets');
  };
  const goToCreateTickets = (): void => {
   
    navigate('/createTicket');
  };
  return (
    <div className="mainContainer">
      <div className="titleContainer">
        <b>
          {role === 'manager'
            ? `Welcome to the Manager Dashboard, ${username}`
            : `Welcome to the Dashboard, ${username}`}
        </b>
      </div>

      <div className="buttonContainer">
        {role === 'manager' ? (
          <input
            className="button"
            type="button"
            value="Process Tickets"
            onClick={goToProcessTickets}
          />
        ) : (
          <>
            <input
              className="button"
              type="button"
              value="Create a Ticket"
              onClick={goToCreateTickets}
            />
            <input
              className="button"
              type="button"
              value="View Tickets"
              onClick={goToViewTickets}
            />
          </>
        )}
      </div>
    </div>
  );
}
export default Dashboard;