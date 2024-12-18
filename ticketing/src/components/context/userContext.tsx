import React, { createContext, useContext, useState, ReactNode } from 'react';
import { useNavigate } from 'react-router-dom';

// Define the type for the context value
interface UserContextType {
  username: string;
  password: string;
  id: string;
  setUsername: (username: string) => void;
  setPassword: (password: string) => void;
  setId: (id: string) => void;
  logout: () => void; // Add the logout function
}

// Create the context
const UserContext = createContext<UserContextType | undefined>(undefined);

// Create the provider component
export const UserContextProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [id, setId] = useState('');
  const navigate = useNavigate();

  // Logout function to reset all user data and navigate to the login page
  const logout = () => {
    setUsername('');
    setPassword('');
    setId('');
    navigate('/'); // Navigate to the login page
  };

  return (
    <UserContext.Provider
      value={{ username, password, id, setUsername, setPassword, setId, logout }}
    >
      {children}
    </UserContext.Provider>
  );
};

// Custom hook to use the context
export const useUserContext = (): UserContextType => {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error('useUserContext must be used within a UserContextProvider');
  }
  return context;
};