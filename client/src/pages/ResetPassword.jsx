import { useState, useEffect } from 'react';
import axios from '../services/axios';
import { useNavigate, useLocation } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export default function ResetPassword() {
  const [newPassword, setNewPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();
  const email = location.state?.email;

  useEffect(() => {
    if (!email) {
      toast.error('No email provided');
      navigate('/forgot-password');
    }
  }, [email, navigate]);

  const handleReset = async (e) => {
    e.preventDefault();

    if (!newPassword.trim()) {
      toast.error('Please enter a new password');
      return;
    }

    setLoading(true);
    try {
      await axios.post('/auth/reset-password-otp', { email, newPassword });
      toast.success('Password reset successful!');
      setTimeout(() => {
        navigate('/login');
      }, 1000);
    } catch (err) {
      toast.error(err.response?.data || 'Password reset failed');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center px-4 bg-gray-50 dark:bg-gray-900">
      <ToastContainer />

      <div className="max-w-md w-full bg-white dark:bg-gray-800 p-8 rounded-xl shadow-xl">
        <div className="text-center mb-6">
          <h1 className="text-3xl font-bold text-green-600 dark:text-green-400">Zidio Connect</h1>
          <p className="text-gray-600 dark:text-gray-300 mt-1">Reset your password</p>
        </div>

        <form onSubmit={handleReset} className="space-y-5">
          <div>
            <label htmlFor="newPassword" className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
              New Password
            </label>
            <input
              id="newPassword"
              type="password"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              className="w-full px-4 py-2 rounded-lg border border-gray-300 dark:border-gray-600 bg-gray-50 dark:bg-gray-700 text-gray-900 dark:text-white"
              placeholder="Enter new password"
              required
              disabled={loading}
            />
          </div>

          <button
            type="submit"
            className="w-full bg-gradient-to-r from-green-600 via-emerald-600 to-teal-600 text-white py-2 rounded-lg font-semibold hover:from-green-700 hover:via-emerald-700 hover:to-teal-700 transition-all duration-300"
            disabled={loading}
          >
            {loading ? 'Resetting...' : 'Reset Password'}
          </button>
        </form>
      </div>
    </div>
  );
}
