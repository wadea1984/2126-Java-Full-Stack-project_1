import React, { ReactNode } from 'react'

import { Navigate } from 'react-router-dom';
import { useUserContext } from '../context/userContext';

interface RouteGuardProps {
    children: ReactNode;
  }
  
  const RouteGuard: React.FC<RouteGuardProps> = ({ children }) => {
    const { id } = useUserContext(); // Destructure 'id' directly for clarity
  
    // If no user ID is found, navigate to the login page
    if (!id) {
      return <Navigate to="/" replace />;
    }
  
    // Render the children if the user is authenticated
    return <>{children}</>;
  };
  
  export default RouteGuard;