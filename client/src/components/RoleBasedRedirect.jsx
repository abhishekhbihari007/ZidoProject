// src/components/RoleBasedRedirect.jsx

import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';

export default function RoleBasedRedirect() {
  const { auth } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    if (!auth?.token || !auth?.role) return;

    switch (auth.role) {
      case 'STUDENT':
        navigate('/student/dashboard');
        break;
      case 'RECRUITER':
        navigate('/recruiter/dashboard');
        break;
      case 'ADMIN':
        navigate('/admin/dashboard');
        break;
      default:
        navigate('/unauthorized');
    }
  }, [auth, navigate]);

  return null;
}
