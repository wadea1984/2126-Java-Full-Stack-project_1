
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
 import { useUserContext } from '../context/userContext';

const RegistrationPage: React.FC = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [address, setAddress] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const { setUsername: setGlobalUsername, setPassword: setGlobalPassword } = useUserContext();
  const navigate = useNavigate();

  const validateInputs = () => {
    if (!firstName || !lastName || !address || !phoneNumber || !username || !password) {
      setError('All fields are required');
      return false;
    }

    if (!/^\d{10}$/.test(phoneNumber)) {
      setError('Phone number must be 10 digits');
      return false;
    }

    setError('');
    return true;
  };

  const handleRegister = () => {
    if (!validateInputs()) {
      return;
    }

    

    alert('Registration successful!');
    navigate('/'); // Navigate to the login page
  };

  return (

    <div className='mainContainer'>
      <h2>Register</h2>
      {error && <p className="error">{error}</p>}

      <div className="inputGroup">
        <label>First Name</label>
        <input
          type="text"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
          placeholder="Enter your first name"
          
        />
      </div>

      <div className="inputGroup">
        <label>Last Name</label>
        <input
          type="text"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
          placeholder="Enter your last name"
        />
      </div>

      <div className="inputGroup">
        <label>Address</label>
        <input
          type="text"
          value={address}
          onChange={(e) => setAddress(e.target.value)}
          placeholder="Enter your address"
        />
      </div>

      <div className="inputGroup">
        <label>Phone Number</label>
        <input
          type="text"
          value={phoneNumber}
          onChange={(e) => setPhoneNumber(e.target.value)}
          placeholder="Enter your phone number"
        />
      </div>

      <div className="inputGroup">
        <label>Username</label>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Enter your username"
        />
      </div>

      <div className='inputGroup'>
        <label>Password</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Enter your password"
          
        />
      </div>

      <div className='inputContainer'>
        <input className={'registerButton'} type="button" onClick={handleRegister} value={'Create new account'}/>
      </div>
    </div>
  );
};

export default RegistrationPage;


