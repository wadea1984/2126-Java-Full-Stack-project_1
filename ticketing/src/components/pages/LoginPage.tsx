import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useUserContext } from '../context/userContext';
import useFetchData from '../hooks/useFetchData';
import axios from 'axios';

const LoginPage: React.FC = () => {
  const { username, password, id,role, setUsername, setPassword, setId,setRole } = useUserContext();
  const [usernameError, setUsernameError] = useState('');
  const [passwordError, setPasswordError] = useState('');
  const [loading, setLoading] = useState(false);
  const [error1, setError] = useState('');

  const navigate = useNavigate();

 

  const goToDashboard = (): void => {
    navigate('Dashboard');
  };

  const goToRegister = (): void => {
    navigate('Register');
  };

  // Button click handler
  const validateInputs = () => {
    setUsernameError('');
    setPasswordError('');
    
    if ('' === username) {
      setUsernameError('Please enter your username');
      return false;
    }
    if ('' === password) {
      setPasswordError('Please enter a password');
      return false;

      
    }
    return true;
  }
    
    const handleTicket= async () => {
      
      if(!validateInputs())
        return false;
     
 
     setLoading(true);
     try {
       const response = await axios.post('http://localhost:8080/Employeelogin', {
        username:  username, 
        password:   password,
        address:   null,
        phone_number:null,
        first_name:null,
        last_name: null,
        role: null,
       });
       console.log(username+" "+password+" "+response.data.id);
       if (response.status === 200) {
         alert('Account Logged in');
         setId(response.data.id);
         setRole(response.data.role);
          navigate('/dashboard')
          

  
                
       } else {
         setError('Failed to Login');
       }
     } catch (err) {
       console.error('Error logging in:', err);
       setError('An error occurred while logging in');
     } finally {
       setLoading(false);
       
     }
   };
  

  const onButtonClick1 = () => {
    goToRegister();
  };

  return (
    <div className={'mainContainer'}>
      
      <div className={'titleContainer'}>
      

        <div><b>Ticket Reimbursement</b></div>
        {error1 && <p className="error">{error1}</p>}
      </div>
      <br />
      <div className={'inputContainer'}>
        <input
          value={username}
          placeholder="username"
          onChange={(e) => setUsername(e.target.value)}
          className={'inputBox'}
        />
        <label className="errorLabel">{usernameError}</label>
      </div>
      <br />
      <div className={'inputContainer'}>
        <input
          value={password}
          placeholder="password"
          onChange={(e) => setPassword(e.target.value)}
          className={'inputBox'}
          type='password'
        />
        <label className="errorLabel">{passwordError}</label>
      </div>
      <br />
      <div className={'inputContainer'}>
        <input className={'inputButton'} type="button" onClick={handleTicket} value={loading ? 'Processing...' : 'Log in'}
          disabled={loading} />
      </div>
      <div className={'inputContainer1'}>
        <br />
        <input className={'registerButton'} type="button" onClick={onButtonClick1} value={'Register'} />
      </div>
    </div>
  );
};

export default LoginPage;