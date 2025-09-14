// src/components/ProtectedRoute.jsx

import { Navigate, useLocation } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';

export default function ProtectedRoute({ children, role }) {
  const { auth } = useAuth();
  const location = useLocation();

  if (!auth?.token) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  if (role) {
    const allowedRoles = Array.isArray(role) ? role : [role];
    if (!allowedRoles.includes(auth.role)) {
      return <Navigate to="/unauthorized" replace />;
    }
  }

  return children;
}

