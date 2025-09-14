// src/pages/recruiter/Applications.jsx

import { useEffect, useState } from 'react';
import axios from '../../services/axios';

export default function Applications() {
  const [jobs, setJobs] = useState([]);
  const [applications, setApplications] = useState([]);
  const [selectedJobId, setSelectedJobId] = useState(null);

  useEffect(() => {
    axios.get('/recruiter/my-jobs').then(res => setJobs(res.data));
  }, []);

  const fetchApplications = async (jobId) => {
    setSelectedJobId(jobId);
    const res = await axios.get(`/recruiter/applications/${jobId}`);
    setApplications(res.data);
  };

  const updateStatus = async (appId, status) => {
    try {
      await axios.put(`/recruiter/applications/${appId}?status=${status}`);
      setApplications(prev =>
        prev.map(a => a.id === appId ? { ...a, status } : a)
      );
    } catch {
      alert("Failed to update status");
    }
  };

  return (
    <div className="max-w-5xl mx-auto mt-10 px-4">
      <h2 className="text-2xl font-bold text-green-700 mb-4">📊 View Applications</h2>

      {/* Job Selection */}
      <div className="mb-4">
        <label className="block mb-1 font-medium">Select a Job</label>
        <select
          className="w-full border p-2 rounded"
          onChange={(e) => fetchApplications(e.target.value)}
          value={selectedJobId || ""}
        >
          <option value="" disabled>Select job...</option>
          {jobs.map(job => (
            <option key={job.id} value={job.id}>
              {job.title} - {job.company}
            </option>
          ))}
        </select>
      </div>

      {/* Applications Table */}
      {applications.length > 0 ? (
        <table className="w-full border mt-4">
          <thead>
            <tr className="bg-gray-100 text-left">
              <th className="p-2 border">Student</th>
              <th className="p-2 border">Status</th>
              <th className="p-2 border">Actions</th>
            </tr>
          </thead>
          <tbody>
            {applications.map(app => (
              <tr key={app.id} className="border-t">
                <td className="p-2 border">{app.student?.username}</td>
                <td className="p-2 border">{app.status}</td>
                <td className="p-2 border space-x-2">
                  <button
                    onClick={() => updateStatus(app.id, 'Shortlisted')}
                    className="bg-blue-500 text-white px-2 py-1 rounded"
                  >
                    Shortlist
                  </button>
                  <button
                    onClick={() => updateStatus(app.id, 'Rejected')}
                    className="bg-red-500 text-white px-2 py-1 rounded"
                  >
                    Reject
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : selectedJobId && (
        <p className="text-gray-500 mt-4">No applications for this job yet.</p>
      )}
    </div>
  );
}
