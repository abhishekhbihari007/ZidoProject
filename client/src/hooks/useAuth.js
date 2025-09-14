import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';

// ✅ Custom hook for cleaner usage of auth state
export const useAuth = () => {
  return useContext(AuthContext);
};
