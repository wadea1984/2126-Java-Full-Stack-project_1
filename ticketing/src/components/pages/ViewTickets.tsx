import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useUserContext } from '../context/userContext';

const ViewTickets: React.FC = () => {
  const { id } = useUserContext(); // Get the user ID from context

  const [tickets, setTickets] = useState<any[]>([]); // Store the tickets in state
  const [loading, setLoading] = useState<boolean>(true); // Track loading state
  const [error, setError] = useState<string>(''); // Track error state

  useEffect(() => {
    // Fetch the tickets for the logged-in user
    const fetchTickets = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/EmployeeTickets/${1}`);

        setTickets(response.data); // Store the fetched tickets in state
      } catch (err) {
        console.error('Error fetching tickets:', err);
        setError('Failed to fetch tickets');
      } finally {
        setLoading(false); // Set loading to false after request is complete
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
    <div>
      <h2>User Tickets</h2>
      {tickets.length === 0 ? (
        <p>No tickets available</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>Amount</th>
              <th>Description</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {tickets.map((ticket) => (
              <tr key={ticket.id}>
                <td>{ticket.amount}</td>
                <td>{ticket.description}</td>
                <td>{ticket.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};
export default ViewTickets;