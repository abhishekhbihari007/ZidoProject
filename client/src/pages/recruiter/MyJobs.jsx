// src/pages/recruiter/MyJobs.jsx

import { useEffect, useState } from 'react';
import axios from '../../services/axios';

export default function MyJobs() {
  const [jobs, setJobs] = useState([]);

  useEffect(() => {
    axios.get('/recruiter/my-jobs')
      .then(res => setJobs(res.data))
      .catch(() => alert("Failed to fetch jobs"));
  }, []);

  return (
    <div className="max-w-4xl mx-auto mt-10 px-4">
      <h2 className="text-2xl font-bold text-purple-700 mb-4">📄 My Posted Jobs</h2>
      {jobs.length === 0 ? (
        <p className="text-gray-600">You haven’t posted any jobs yet.</p>
      ) : (
        jobs.map(job => (
          <div key={job.id} className="border rounded p-4 mb-4 shadow">
            <h3 className="font-semibold text-lg">{job.title}</h3>
            <p className="text-sm text-gray-600">{job.company}</p>
            <p className="text-sm text-gray-500 mb-2">Deadline: {job.deadline}</p>
          </div>
        ))
      )}
    </div>
  );
}
