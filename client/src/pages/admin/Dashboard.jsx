import { useEffect, useState } from 'react';
import axios from 'axios';

export default function AdminDashboard() {
  const [stats, setStats] = useState(null);
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  const fetchStats = async () => {
    try {
      const res = await axios.get('/admin/stats');
      setStats(res.data);
    } catch (err) {
      console.error('Failed to load stats:', err);
      setError('Failed to load stats');
    }
  };

  const fetchUsers = async () => {
    try {
      const res = await axios.get('/admin/users');
      console.log('User response:', res.data);

      // Ensure users is always an array
      if (Array.isArray(res.data)) {
        setUsers(res.data);
      } else if (Array.isArray(res.data.users)) {
        setUsers(res.data.users);
      } else {
        throw new Error('Invalid user response format');
      }
    } catch (err) {
      console.error('Failed to load users:', err);
      setError('Failed to load users');
    } finally {
      setLoading(false);
    }
  };

  const toggleUserStatus = async (id, enabled) => {
    try {
      await axios.put(`/admin/users/${id}/status?enabled=${!enabled}`);
      fetchUsers(); // Refresh user list
    } catch (err) {
      alert('Failed to update user status');
    }
  };

  useEffect(() => {
    fetchStats();
    fetchUsers();
  }, []);

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold text-red-600 mb-2">🛠️ Admin Dashboard</h1>
      <p className="text-gray-600 mb-6">Welcome, admin! Manage users and monitor the system.</p>

      {error && <div className="text-red-600 mb-4">{error}</div>}

      {/* Analytics Cards */}
      {stats && (
        <div className="grid grid-cols-1 sm:grid-cols-3 gap-4 mb-8">
          <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow">
            <p className="text-gray-500 dark:text-gray-400">Total Users</p>
            <h2 className="text-xl font-bold text-red-500">{stats.totalUsers}</h2>
          </div>
          <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow">
            <p className="text-gray-500 dark:text-gray-400">Jobs Posted</p>
            <h2 className="text-xl font-bold text-red-500">{stats.totalJobs}</h2>
          </div>
          <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow">
            <p className="text-gray-500 dark:text-gray-400">Applications</p>
            <h2 className="text-xl font-bold text-red-500">{stats.totalApplications}</h2>
          </div>
        </div>
      )}

      {/* User Management */}
      <h2 className="text-xl font-semibold mb-4 text-gray-800 dark:text-white">👥 Manage Users</h2>
      {loading ? (
        <p className="text-gray-500">Loading users...</p>
      ) : (
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white dark:bg-gray-900 shadow rounded-lg">
            <thead className="bg-gray-100 dark:bg-gray-800 text-gray-600 dark:text-gray-300 text-left">
              <tr>
                <th className="px-4 py-3">#</th>
                <th className="px-4 py-3">Username</th>
                <th className="px-4 py-3">Email</th>
                <th className="px-4 py-3">Role</th>
                <th className="px-4 py-3">Status</th>
                <th className="px-4 py-3">Actions</th>
              </tr>
            </thead>
            <tbody className="text-sm text-gray-700 dark:text-gray-200">
              {Array.isArray(users) && users.length > 0 ? (
                users.map((user, index) => (
                  <tr key={user.id} className="border-t border-gray-200 dark:border-gray-800">
                    <td className="px-4 py-2">{index + 1}</td>
                    <td className="px-4 py-2">{user.username}</td>
                    <td className="px-4 py-2">{user.email}</td>
                    <td className="px-4 py-2">{user.role}</td>
                    <td className="px-4 py-2">
                      <span
                        className={`px-2 py-1 text-xs font-semibold rounded-full ${
                          user.enabled
                            ? 'bg-green-100 text-green-600'
                            : 'bg-red-100 text-red-600'
                        }`}
                      >
                        {user.enabled ? 'Active' : 'Blocked'}
                      </span>
                    </td>
                    <td className="px-4 py-2">
                      <button
                        onClick={() => toggleUserStatus(user.id, user.enabled)}
                        className={`px-3 py-1 rounded text-sm font-medium ${
                          user.enabled
                            ? 'bg-red-500 hover:bg-red-600 text-white'
                            : 'bg-green-500 hover:bg-green-600 text-white'
                        }`}
                      >
                        {user.enabled ? 'Block' : 'Approve'}
                      </button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td className="p-4 text-center text-gray-500" colSpan="6">
                    No users found.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
