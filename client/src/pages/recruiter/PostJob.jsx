// src/pages/recruiter/PostJob.jsx

import { useState } from 'react';
import axios from '../../services/axios';

export default function PostJob() {
  const [job, setJob] = useState({
    title: '',
    company: '',
    description: '',
    deadline: ''
  });

  const handleChange = (e) => {
    setJob({ ...job, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/recruiter/post-job', job);
      alert('Job posted successfully');
      setJob({ title: '', company: '', description: '', deadline: '' });
    } catch {
      alert('Failed to post job');
    }
  };

  return (
    <div className="max-w-xl mx-auto mt-10 p-6 border shadow rounded">
      <h2 className="text-xl font-bold mb-4">Post a New Job</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          className="w-full p-2 border rounded"
          name="title"
          placeholder="Job Title"
          value={job.title}
          onChange={handleChange}
        />
        <input
          className="w-full p-2 border rounded"
          name="company"
          placeholder="Company Name"
          value={job.company}
          onChange={handleChange}
        />
        <textarea
          className="w-full p-2 border rounded"
          name="description"
          placeholder="Job Description"
          value={job.description}
          onChange={handleChange}
        />
        <input
          className="w-full p-2 border rounded"
          type="date"
          name="deadline"
          value={job.deadline}
          onChange={handleChange}
        />
        <button type="submit" className="bg-purple-600 text-white px-4 py-2 rounded">
          Post Job
        </button>
      </form>
    </div>
  );
}
