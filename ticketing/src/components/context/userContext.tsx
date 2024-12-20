import React, { createContext, useContext, useState, ReactNode, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

// Define the type for the context value
interface UserContextType {
  username: string;
  password: string;
  id: string;
  role: string;
  setRole: (role: string) => void;
  setUsername: (username: string) => void;
  setPassword: (password: string) => void;
  setId: (id: string) => void;
  logout: () => void; // Add the logout function
}

// Create the context
const UserContext = createContext<UserContextType | undefined>(undefined);

// Create the provider component
export const UserContextProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  // Load initial state from localStorage
  const storedUsername = localStorage.getItem('username') || '';
  const storedPassword = localStorage.getItem('password') || '';
  const storedId = localStorage.getItem('id') || '';
  const storedRole = localStorage.getItem('role') || '';

  const [username, setUsername] = useState(storedUsername);
  const [password, setPassword] = useState(storedPassword);
  const [id, setId] = useState(storedId);
  const [role, setRole] = useState(storedRole);

  const navigate = useNavigate();

  // Update localStorage whenever any of the state values change
  useEffect(() => {
    localStorage.setItem('username', username);
    localStorage.setItem('password', password);
    localStorage.setItem('id', id);
    localStorage.setItem('role', role);
  }, [username, password, id, role]);

  // Logout function to reset all user data and navigate to the login page
  const logout = () => {
    const confirmLogout = window.confirm("Are you sure you want to log out?");
    
    if (confirmLogout) {
      setUsername('');
      setPassword('');
      setId('');
      setRole('');
      localStorage.clear(); // Clear all data from localStorage
      alert("Logged out");
      navigate('/'); // Navigate to the login page
    } else {
      // Optional: Handle the case where the user cancels the logout
      console.log("Logout canceled");
    }
  };

  return (
    <UserContext.Provider
      value={{ username, password, id, role, setUsername, setPassword, setId, logout, setRole }}
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