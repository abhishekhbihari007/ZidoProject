import React, { createContext, useContext, useState } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState(() => {
    const token = localStorage.getItem('token');
    const role = localStorage.getItem('role');
    const name = localStorage.getItem('name');
    const email = localStorage.getItem('email');
    const avatar = localStorage.getItem('avatar');
    return token ? { token, role, name, email, avatar } : null;
  });

  const login = (token, role, name, email, avatar = null) => {
    localStorage.setItem('token', token);
    localStorage.setItem('role', role);
    localStorage.setItem('name', name);
    localStorage.setItem('email', email);
    if (avatar) localStorage.setItem('avatar', avatar);
    setAuth({ token, role, name, email, avatar });
  };

  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('name');
    localStorage.removeItem('email');
    localStorage.removeItem('avatar');
    setAuth(null);
  };

  return (
    <AuthContext.Provider value={{ auth, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuthContext = () => useContext(AuthContext);
