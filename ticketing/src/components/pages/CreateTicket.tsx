
import React, { createContext, useContext, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
 import { useUserContext } from '../context/userContext';
 import axios from 'axios';
import useStoreUserData from '../hooks/useStoreUserData';
import useStoreTicket from '../hooks/useStoreTicket';

const CreateTicket: React.FC = () => {
    const { username, password, id, setUsername, setPassword, setId, logout } = useUserContext();
    
  const [name, setName] = useState('');
  const [amount, setAmount] = useState('');
  const [description, setDescription] = useState('');
 const [error1, setError] = useState('');
 const [loading, setLoading] = useState(false);
  
  const navigate = useNavigate();
  

  const validateInputs = () => {
    if (!name || !description || !amount) {
      setError('All fields are required');
      return false;
    }

    if (isNaN(Number(amount))) {
        setError('A number is required for this field');
        return false;
      }

    setError('');
    return true;
  };
  const handleTicket= async () => {
     const status="pending"
    if (!validateInputs()) return;

    setLoading(true);
    try {
      const response = await axios.post('http://localhost:8080/tickets', {
        name: name,
        amount: Number(amount),
        description: description,
        employeeID: id,
        status: status,
        receipt: null

      });
      
      if (response.status === 200) {
        alert('Account Created!');
        navigate('/dashboard')
      } else {
        setError('Failed to create ticket');
      }
    } catch (err) {
      console.error('Error creating ticket:', err);
      setError('An error occurred while creating the ticket');
    } finally {
      setLoading(false);
      
    }
  };

  return (

    <div className='mainContainer'>
      <h2>Create Ticket</h2>
      {error1 && <p className="error">{error1}</p>}

      <div className="inputGroup">
        <label>Name</label>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Name"
          
        />
      </div>
      <div className="inputGroup">
        <label>Description</label>
        <input
          type="text"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          placeholder="Description"
        />
      </div>

      <div className="inputGroup">
        <label>Amount</label>
        <input
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
          placeholder="0.00"
        />
      </div>
      <div className='inputContainer'>
        <input className={'registerButton'} type="button" onClick={handleTicket} value={loading ? 'Processing...' : 'Create Ticket'}
          disabled={loading} />
      </div>
    </div>
  );
};

export default CreateTicket;