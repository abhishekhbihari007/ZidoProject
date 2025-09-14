import { useState, useEffect } from 'react';
import axios from '../../services/axios';
import { useAuth } from '../../hooks/useAuth';
import { toast } from 'react-toastify';

export default function Profile() {
  const { user } = useAuth(); // Access the full user object
  const userId = user?.id;    // Safely extract the student ID

  const [profile, setProfile] = useState({});
  const [form, setForm] = useState({});

  useEffect(() => {
    if (userId) {
      axios.get(`/student/profile/${userId}`)
        .then(res => {
          setProfile(res.data);
          setForm(res.data);
        })
        .catch(err => {
          toast.error("Failed to load profile");
          console.error("Profile fetch error:", err);
        });
    } else {
      console.warn("No user ID available");
    }
  }, [userId]);

  const handleChange = (field) => (e) => {
    setForm({ ...form, [field]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`/student/profile/${userId}`, form);
      toast.success("Profile updated!");
    } catch (err) {
      toast.error("Update failed");
      console.error("Profile update error:", err);
    }
  };

  return (
    <div className="p-4">
      <h2 className="text-xl font-bold mb-4">Profile</h2>
      <form onSubmit={handleSubmit} className="space-y-4 max-w-md">
        {['name', 'phone', 'college', 'branch', 'yearOfPassing'].map(field => (
          <input
            key={field}
            className="w-full border rounded p-2"
            placeholder={field}
            value={form[field] || ''}
            onChange={handleChange(field)}
          />
        ))}
        <button type="submit" className="bg-green-600 text-white py-2 px-4 rounded">Save</button>
      </form>
    </div>
  );
}
