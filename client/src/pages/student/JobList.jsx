import { useEffect, useState } from 'react';
import axios from '../../services/axios';
import { useAuth } from '../../hooks/useAuth';
import { toast } from 'react-toastify';

export default function JobList() {
  const [jobs, setJobs] = useState([]);
  const { userId } = useAuth();

  useEffect(() => {
    axios.get('/jobs').then(res => setJobs(res.data));
  }, []);

  const apply = async (jobId) => {
    try {
      await axios.post(`/student/apply/${userId}/${jobId}`);
      toast.success("Application submitted!");
    } catch (err) {
      toast.error("You may have already applied.");
    }
  };

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Available Jobs</h2>
      <div className="grid gap-4">
        {jobs.map(job => (
          <div key={job.id} className="border p-4 rounded shadow">
            <h3 className="text-lg font-semibold">{job.title}</h3>
            <p>{job.company}</p>
            <p>{job.location}</p>
            <button
              onClick={() => apply(job.id)}
              className="mt-2 bg-green-600 text-white px-3 py-1 rounded"
            >
              Apply
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
