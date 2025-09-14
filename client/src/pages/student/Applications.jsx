// src/pages/student/Applications.jsx

import { useEffect, useState } from 'react';
import axios from '../../services/axios';

export default function Applications() {
  const [applications, setApplications] = useState([]);

  useEffect(() => {
    axios.get('/student/applications').then(res => setApplications(res.data));
  }, []);

  return (
    <div className="max-w-3xl mx-auto mt-10">
      <h2 className="text-xl font-bold mb-4">Your Applications</h2>
      {applications.map(app => (
        <div key={app.id} className="p-4 mb-3 border rounded shadow-sm">
          <p><strong>Job:</strong> {app.job?.title}</p>
          <p><strong>Company:</strong> {app.job?.company}</p>
          <p><strong>Status:</strong> <span className="text-blue-600">{app.status}</span></p>
        </div>
      ))}
    </div>
  );
}
