// src/pages/admin/ManageUsers.jsx

import { useEffect, useState } from 'react';
import axios from '../../services/axios';

export default function ManageUsers() {
  const [users, setUsers] = useState([]);

  const fetchUsers = async () => {
    const res = await axios.get('/admin/users');
    setUsers(res.data);
  };

  const deleteUser = async (id) => {
    try {
      await axios.delete(`/admin/users/${id}`);
      setUsers(users.filter(user => user.id !== id));
      alert('User deleted');
    } catch {
      alert('Failed to delete user');
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  return (
    <div className="max-w-4xl mx-auto mt-10">
      <h2 className="text-xl font-bold mb-4">Manage Users</h2>
      <table className="w-full border">
        <thead>
          <tr className="bg-gray-100 text-left">
            <th className="p-2 border">Username</th>
            <th className="p-2 border">Email</th>
            <th className="p-2 border">Role</th>
            <th className="p-2 border">Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map(u => (
            <tr key={u.id} className="border-t">
              <td className="p-2 border">{u.username}</td>
              <td className="p-2 border">{u.email}</td>
              <td className="p-2 border">{u.role}</td>
              <td className="p-2 border">
                <button
                  onClick={() => deleteUser(u.id)}
                  className="bg-red-500 text-white px-2 py-1 rounded"
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
