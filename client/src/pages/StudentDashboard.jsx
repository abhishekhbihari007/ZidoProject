import React, { useEffect, useState } from 'react';
import axios from '../api/axios';

const StudentDashboard = () => {
  const [message, setMessage] = useState('');

  useEffect(() => {
    axios.get('/student/dashboard')
      .then(res => setMessage(res.data))
      .catch(() => setMessage('Access Denied'));
  }, []);

  return <div>{message}</div>;
};

export default StudentDashboard;
