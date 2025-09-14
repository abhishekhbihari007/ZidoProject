import { Link } from 'react-router-dom';

export default function RecruiterDashboard() {
  return (
    <div className="max-w-4xl mx-auto mt-10 px-4">
      <h1 className="text-3xl font-bold text-purple-700 mb-2">💼 Recruiter Dashboard</h1>
      <p className="text-gray-600 mb-6">Post jobs, manage applications, and find top talent!</p>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        {/* Post Job */}
        <Link
          to="/recruiter/post-job"
          className="block p-6 bg-white border rounded shadow hover:shadow-lg transition"
        >
          <h2 className="text-lg font-semibold text-purple-600">📝 Post a Job</h2>
          <p className="text-sm text-gray-500">Create a new job or internship listing</p>
        </Link>

        {/* View My Jobs */}
        <Link
          to="/recruiter/my-jobs"
          className="block p-6 bg-white border rounded shadow hover:shadow-lg transition"
        >
          <h2 className="text-lg font-semibold text-blue-600">📄 My Posted Jobs</h2>
          <p className="text-sm text-gray-500">Review jobs you’ve posted</p>
        </Link>

        {/* View Applications */}
        <Link
          to="/recruiter/applications"
          className="block p-6 bg-white border rounded shadow hover:shadow-lg transition"
        >
          <h2 className="text-lg font-semibold text-green-600">📊 View Applications</h2>
          <p className="text-sm text-gray-500">Check student applications and manage them</p>
        </Link>

        {/* Excel Upload */}
        <Link
          to="/recruiter/excel-upload"
          className="block p-6 bg-white border rounded shadow hover:shadow-lg transition"
        >
          <h2 className="text-lg font-semibold text-purple-600">📊 Upload Jobs from Excel</h2>
          <p className="text-sm text-gray-500">Bulk upload jobs using Excel template</p>
        </Link>
      </div>
    </div>
  );
}
