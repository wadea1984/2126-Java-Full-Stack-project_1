import React from 'react'
import { useUserContext } from '../context/userContext';


function Dashboard() {
  const { username, password, id, setUsername, setPassword, setId, logout } = useUserContext();
  return (
    <div>
      Dashboard
      
      <div> username:{username}paswword:{password} id:{id}</div>
    

    <input className={'inputButton'} type="button" onClick={logout} value={'Log out'} />
    
    
    </div>
  )
}

export default Dashboard