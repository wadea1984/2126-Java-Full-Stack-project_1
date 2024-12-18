
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { useUserContext } from '../context/userContext';
import useFetchData from  '../hooks/useFetchData';


const LoginPage: React.FC = () => {
  
  
  const { username, password, id, setUsername, setPassword, setId, logout } = useUserContext();
  const [usernameError, setUsernameError] = useState('')
  const [passwordError, setPasswordError] = useState('')
  
  const navigate = useNavigate()
 
  const { data, loading, error } = useFetchData('http://localhost:8080/Employeelogin',username,password);
 
  
  const goToDashboard = (): void => {
    navigate('Dashboard');
  };
  const goToRegister = (): void => {
    navigate('Register');
  };
  

  const onButtonClick = () => {
    setUsernameError('')
    setPasswordError('')
    
    if ('' === username) {
      setUsernameError('Please enter your username')
      return
    }
    if ('' === password) {
      setPasswordError('Please enter a password')
      return
    }
   
    if (!data) {
      setUsernameError('Your username or password is incorrect');
      return;
    }
    
    setId(data.id);
    alert(`Logged in as ${username}`);
    

    goToDashboard();
  }
  const onButtonClick1 = () => {
    goToRegister();
  }


  return (
    <div className={'mainContainer'}>
      <div className={'titleContainer'}>
        <div><b>Ticking Remboursement</b></div>
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
        <input className={'inputButton'} type="button" onClick={onButtonClick} value={'Log in'} />
      </div>
      <div className={'inputContainer1'}>
        <br></br>
        <input className={'registerButton'} type="button" onClick={onButtonClick1} value={'Create new account'} />
      </div>
    </div>
  )
}

export default LoginPage