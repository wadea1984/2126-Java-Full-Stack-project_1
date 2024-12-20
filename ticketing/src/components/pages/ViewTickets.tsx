import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useUserContext } from '../context/userContext';

const ViewTickets: React.FC = () => {
  const { id } = useUserContext(); 

  const [tickets, setTickets] = useState<any[]>([]); 
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>('');

  useEffect(() => {
    
    const fetchTickets = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/EmployeeTickets/${id}`);

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
              <th>Amount</th>
              <th>Description</th>
              <th>Status</th>
              <th>Date Created</th>
            </tr>
          </thead>
          <tbody>
            {tickets.map((ticket) => (
              <tr key={ticket.id}>
                <td> {ticket.id}</td>
                <td>{ticket.amount}</td>
                <td>{ticket.description}</td>
                <td>{ticket.status}</td>
                <td>{ticket.date}</td>
              </tr>
            ))}
          </tbody>
        </table>
        
      )}
    </div>
    
  );
};
export default ViewTickets;