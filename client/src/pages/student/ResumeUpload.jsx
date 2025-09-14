import { useState } from 'react';
import axios from '../../services/axios';
import { useAuth } from '../../hooks/useAuth';
import { toast } from 'react-toastify';

export default function ResumeUpload() {
  const { userId } = useAuth();
  const [file, setFile] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file);

    try {
      await axios.post(`/student/resume/${userId}`, formData);
      toast.success("Resume uploaded!");
    } catch (err) {
      toast.error("Upload failed");
    }
  };

  return (
    <div className="p-4 max-w-md">
      <h2 className="text-xl font-bold mb-4">Upload Resume</h2>
      <form onSubmit={handleSubmit}>
        <input type="file" onChange={(e) => setFile(e.target.files[0])} />
        <button className="bg-blue-600 text-white py-2 px-4 mt-2 rounded">Upload</button>
      </form>
    </div>
  );
}
