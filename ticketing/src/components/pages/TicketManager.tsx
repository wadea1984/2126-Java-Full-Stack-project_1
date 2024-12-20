import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useUserContext } from '../context/userContext';

const TicketManager: React.FC = () => {
  const { id } = useUserContext(); 

  const [tickets, setTickets] = useState<any[]>([]); 
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>('');

  useEffect(() => {
    const fetchTickets = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/PendingTickets`);
        setTickets(response.data); 
      } catch (err) {
        console.error('Error fetching tickets:', err);
        setError('Failed to fetch tickets');
      } finally {
        setLoading(false);
      }
    };
    fetchTickets();
  }, [id]); 

  const handleApprove = async (ticketId: number,status:string) => {
    const confirm = window.confirm("Are you sure you want to this ticket to be "+status);
   if(confirm){
    try {
       const response= await axios.patch(`http://localhost:8080/tickets/${ticketId}`, 
            {   name: null,
                amount: null,
                description: null,
                employeeID: id,
                status: status,
                receipt: null
              

            });
            if (response.status === 200) {

                alert('Ticket '+status+'!');
                setTickets((prevTickets) =>
                    prevTickets.filter((ticket) => ticket.id !== ticketId)
                  );
                
                
              } else {
                setError('Failed to process the ticket');
              }
       
      } catch (err) {
        console.error('Error approving ticket:', err);
        alert('Failed to approve the ticket');
      }
    }
    else
       return
   
  };



  if (loading) {
    return <p>Loading tickets...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <div className='mainContainer'>
      <h2>Tickets</h2>
      {tickets.length === 0 ? (
        <p>No tickets available</p>
      ) : (
        <table className='table table-striped'>
          <thead>
            <tr>
              <th>Ticket Number</th>
              <th>Name</th>
              <th>Amount</th>
              <th>Description</th>
              <th>Status</th>
              <th>Date Created</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {tickets.map((ticket) => (
              <tr key={ticket.id}>
                <td>{ticket.id}</td>
                <td>{ticket.name}</td>
                <td>{ticket.amount}</td>
                <td>{ticket.description}</td>
                <td>{ticket.status}</td>
                <td>{ticket.date}</td>
                <td>
                  <button
                    id="approveButton"
                    className='btn btn-success'
                    onClick={() => handleApprove(ticket.id,"approved")}
                    disabled={ticket.status === 'Approved'}
                  >
                    Approve
                  </button>
                  <button
                    id='denyButton'
                    className='btn btn-danger'
                    onClick={() => handleApprove(ticket.id,"denied")}
                    disabled={ticket.status === 'Denied'}
                  >
                    Deny
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default TicketManager;
