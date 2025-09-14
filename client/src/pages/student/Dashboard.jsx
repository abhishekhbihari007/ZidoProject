import { Link } from 'react-router-dom';
import { useState, useEffect } from 'react';

export default function StudentDashboard() {
  const [greeting, setGreeting] = useState('');
  const [currentTime, setCurrentTime] = useState(new Date());

  useEffect(() => {
    const hour = new Date().getHours();
    if (hour < 12) setGreeting('Good Morning');
    else if (hour < 18) setGreeting('Good Afternoon');
    else setGreeting('Good Evening');

    const timer = setInterval(() => setCurrentTime(new Date()), 1000);
    return () => clearInterval(timer);
  }, []);

  // Mock data - in real app, this would come from API
  const stats = {
    appliedJobs: 8,
    pendingApplications: 3,
    interviewsScheduled: 2,
    profileCompleteness: 85
  };

  const quickActions = [
    {
      title: 'Manage Profile',
      description: 'Update your personal info and showcase your skills',
      icon: '👤',
      link: '/student/profile',
      color: 'emerald',
      badge: `${stats.profileCompleteness}% Complete`
    },
    {
      title: 'Upload Resume',
      description: 'Upload your latest resume in PDF format',
      icon: '📄',
      link: '/student/resume',
      color: 'blue',
      badge: 'Required'
    },
    {
      title: 'Browse Jobs',
      description: 'Explore and apply for available positions',
      icon: '💼',
      link: '/student/jobs',
      color: 'purple',
      badge: '45 New'
    },
    {
      title: 'My Applications',
      description: 'Monitor the status of your applications',
      icon: '📊',
      link: '/student/applications',
      color: 'orange',
      badge: `${stats.appliedJobs} Applied`
    }
  ];

  const recentActivity = [
    { action: 'Applied to Frontend Developer at TechCorp', time: '2 hours ago', type: 'application' },
    { action: 'Profile viewed by Microsoft Recruiter', time: '1 day ago', type: 'view' },
    { action: 'Interview scheduled with Google', time: '2 days ago', type: 'interview' }
  ];

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-purple-50 px-4 py-8">
      <div className="max-w-7xl mx-auto">
        {/* Header Section */}
        <div className="mb-8">
          <div className="flex flex-col lg:flex-row lg:items-center lg:justify-between">
            <div>
              <h1 className="text-3xl lg:text-4xl font-bold text-gray-900 mb-2">
                {greeting}, Student! 👋
              </h1>
              <p className="text-gray-600 text-lg">
                Ready to take the next step in your career journey?
              </p>
              <p className="text-sm text-gray-500 mt-1">
                {currentTime.toLocaleDateString('en-US', { 
                  weekday: 'long', 
                  year: 'numeric', 
                  month: 'long', 
                  day: 'numeric' 
                })}
              </p>
            </div>
            <div className="mt-4 lg:mt-0">
              <div className="card-professional p-4 lg:p-6">
                <div className="text-center">
                  <div className="text-2xl font-bold text-green-600 mb-1">Level 3</div>
                  <div className="text-sm text-gray-600">Career Explorer</div>
                  <div className="w-full bg-gray-200 rounded-full h-2 mt-2">
                    <div className="bg-green-600 h-2 rounded-full" style={{width: '75%'}}></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Stats Overview */}
        <div className="grid grid-cols-2 lg:grid-cols-4 gap-4 lg:gap-6 mb-8">
          <div className="card-professional p-4 lg:p-6 text-center">
            <div className="text-2xl lg:text-3xl font-bold text-blue-600 mb-1">{stats.appliedJobs}</div>
            <div className="text-sm text-gray-600">Applications Sent</div>
          </div>
          <div className="card-professional p-4 lg:p-6 text-center">
            <div className="text-2xl lg:text-3xl font-bold text-orange-600 mb-1">{stats.pendingApplications}</div>
            <div className="text-sm text-gray-600">Pending Reviews</div>
          </div>
          <div className="card-professional p-4 lg:p-6 text-center">
            <div className="text-2xl lg:text-3xl font-bold text-green-600 mb-1">{stats.interviewsScheduled}</div>
            <div className="text-sm text-gray-600">Interviews Scheduled</div>
          </div>
          <div className="card-professional p-4 lg:p-6 text-center">
            <div className="text-2xl lg:text-3xl font-bold text-purple-600 mb-1">{stats.profileCompleteness}%</div>
            <div className="text-sm text-gray-600">Profile Complete</div>
          </div>
        </div>

        <div className="grid lg:grid-cols-3 gap-8">
          {/* Quick Actions */}
          <div className="lg:col-span-2">
            <h2 className="text-xl font-semibold text-gray-900 mb-6">Quick Actions</h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
              {quickActions.map((action, index) => (
                <Link
                  key={index}
                  to={action.link}
                  className="card-professional p-6 group relative overflow-hidden"
                >
                  <div className="flex items-start justify-between mb-4">
                    <div className="text-3xl">{action.icon}</div>
                    <span className={`status-badge status-${action.color === 'emerald' ? 'active' : action.color === 'blue' ? 'pending' : action.color === 'purple' ? 'approved' : 'pending'} text-xs`}>
                      {action.badge}
                    </span>
                  </div>
                  <h3 className="text-lg font-semibold text-gray-900 mb-2 group-hover:text-blue-600 transition-colors">
                    {action.title}
                  </h3>
                  <p className="text-sm text-gray-600 leading-relaxed">
                    {action.description}
                  </p>
                  <div className="mt-4 flex items-center text-blue-600 text-sm font-medium group-hover:translate-x-1 transition-transform">
                    Get Started
                    <svg className="w-4 h-4 ml-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M17 8l4 4m0 0l-4 4m4-4H3" />
                    </svg>
                  </div>
                </Link>
              ))}
            </div>
          </div>

          {/* Recent Activity & Notifications */}
          <div className="space-y-6">
            {/* Recent Activity */}
            <div className="card-professional p-6">
              <h3 className="text-lg font-semibold text-gray-900 mb-4">Recent Activity</h3>
              <div className="space-y-4">
                {recentActivity.map((activity, index) => (
                  <div key={index} className="flex items-start space-x-3">
                    <div className={`w-8 h-8 rounded-full flex items-center justify-center text-xs ${
                      activity.type === 'application' ? 'bg-blue-100 text-blue-600' :
                      activity.type === 'view' ? 'bg-green-100 text-green-600' :
                      'bg-purple-100 text-purple-600'
                    }`}>
                      {activity.type === 'application' ? '📝' : activity.type === 'view' ? '👁️' : '📅'}
                    </div>
                    <div className="flex-1 min-w-0">
                      <p className="text-sm text-gray-900 font-medium">{activity.action}</p>
                      <p className="text-xs text-gray-500">{activity.time}</p>
                    </div>
                  </div>
                ))}
              </div>
              <button className="mt-4 text-sm text-blue-600 hover:text-blue-500 font-medium">
                View All Activity →
              </button>
            </div>

            {/* Career Progress */}
            <div className="card-professional p-6">
              <h3 className="text-lg font-semibold text-gray-900 mb-4">Career Progress</h3>
              <div className="space-y-4">
                <div>
                  <div className="flex justify-between text-sm mb-1">
                    <span className="text-gray-600">Profile Setup</span>
                    <span className="text-gray-900 font-medium">85%</span>
                  </div>
                  <div className="w-full bg-gray-200 rounded-full h-2">
                    <div className="bg-green-600 h-2 rounded-full" style={{width: '85%'}}></div>
                  </div>
                </div>
                <div>
                  <div className="flex justify-between text-sm mb-1">
                    <span className="text-gray-600">Skills Added</span>
                    <span className="text-gray-900 font-medium">60%</span>
                  </div>
                  <div className="w-full bg-gray-200 rounded-full h-2">
                    <div className="bg-blue-600 h-2 rounded-full" style={{width: '60%'}}></div>
                  </div>
                </div>
                <div>
                  <div className="flex justify-between text-sm mb-1">
                    <span className="text-gray-600">Resume Uploaded</span>
                    <span className="text-gray-900 font-medium">100%</span>
                  </div>
                  <div className="w-full bg-gray-200 rounded-full h-2">
                    <div className="bg-purple-600 h-2 rounded-full" style={{width: '100%'}}></div>
                  </div>
                </div>
              </div>
            </div>

            {/* Tips */}
            <div className="card-professional p-6 bg-gradient-to-br from-blue-50 to-purple-50">
              <h3 className="text-lg font-semibold text-gray-900 mb-2">💡 Pro Tip</h3>
              <p className="text-sm text-gray-600 mb-3">
                Complete your profile to increase your chances of being discovered by recruiters by 3x!
              </p>
              <Link 
                to="/student/profile" 
                className="text-sm text-blue-600 hover:text-blue-500 font-medium"
              >
                Complete Profile →
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
