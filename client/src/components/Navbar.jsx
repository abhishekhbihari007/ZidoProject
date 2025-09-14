import { Link } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';
import { useEffect, useState, useRef } from 'react';
import {
  UserIcon,
  ChevronDownIcon,
  Cog6ToothIcon,
  ArrowRightOnRectangleIcon,
  UserCircleIcon,
  BellIcon
} from '@heroicons/react/24/outline';

export default function Navbar() {
  const { auth, logout } = useAuth();
  const [isProfileDropdownOpen, setIsProfileDropdownOpen] = useState(false);
  const dropdownRef = useRef(null);

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsProfileDropdownOpen(false);
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  const toggleProfileDropdown = () => setIsProfileDropdownOpen(!isProfileDropdownOpen);

  const getDashboardLink = () => {
    switch (auth?.role) {
      case 'STUDENT': return '/student/dashboard';
      case 'RECRUITER': return '/recruiter/dashboard';
      case 'ADMIN': return '/admin/dashboard';
      default: return '/dashboard';
    }
  };

  const getInitials = (name) => {
    if (!name) return 'U';
    return name.split(' ').map(word => word[0]).join('').toUpperCase().slice(0, 2);
  };

  const displayName = auth?.name || auth?.username || 'User';

  return (
    <nav className="bg-white border-b border-gray-200 sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          {/* Logo */}
          <Link to="/" className="flex items-center space-x-3">
            <div className="w-10 h-10 bg-blue-600 rounded-lg flex items-center justify-center">
              <span className="text-white font-bold text-lg">Z</span>
            </div>
            <div>
              <span className="text-xl font-bold text-gray-900">ZIDIOConnect</span>
            </div>
          </Link>

          {/* Navigation Links */}
          <div className="hidden md:flex items-center space-x-8">
            <Link to="/" className="text-gray-600 hover:text-gray-900 font-medium transition-colors">
              Home
            </Link>
            <Link to="/about" className="text-gray-600 hover:text-gray-900 font-medium transition-colors">
              About
            </Link>
            <Link to="/contact" className="text-gray-600 hover:text-gray-900 font-medium transition-colors">
              Contact
            </Link>
          </div>

          {/* Right Side */}
          <div className="flex items-center space-x-4">
            {auth ? (
              <>
                {/* Notifications */}
                <button className="relative p-2 text-gray-600 hover:text-gray-900 hover:bg-gray-100 rounded-lg transition-colors">
                  <BellIcon className="h-5 w-5" />
                  <span className="absolute top-1 right-1 w-2 h-2 bg-red-500 rounded-full"></span>
                </button>

                {/* Profile Dropdown */}
                <div className="relative" ref={dropdownRef}>
                  <button
                    onClick={toggleProfileDropdown}
                    className="flex items-center space-x-3 p-2 rounded-lg hover:bg-gray-100 transition-colors"
                  >
                    <div className="w-8 h-8 bg-blue-600 rounded-full flex items-center justify-center text-white text-sm font-semibold">
                      {getInitials(displayName)}
                    </div>
                    <div className="hidden md:block text-left">
                      <div className="text-sm font-medium text-gray-900">{displayName}</div>
                      <div className="text-xs text-gray-500 capitalize">{auth.role?.toLowerCase()}</div>
                    </div>
                    <ChevronDownIcon className={`h-4 w-4 text-gray-500 transition-transform ${isProfileDropdownOpen ? 'rotate-180' : ''}`} />
                  </button>

                  {isProfileDropdownOpen && (
                    <div className="absolute right-0 mt-2 w-64 bg-white rounded-lg shadow-lg border border-gray-200 py-2 z-50">
                      <div className="px-4 py-3 border-b border-gray-200">
                        <div className="flex items-center space-x-3">
                          <div className="w-10 h-10 bg-blue-600 rounded-full flex items-center justify-center text-white font-semibold">
                            {getInitials(displayName)}
                          </div>
                          <div>
                            <p className="font-medium text-gray-900">{displayName}</p>
                            <p className="text-sm text-gray-500">{auth.email}</p>
                          </div>
                        </div>
                      </div>

                      <div className="py-2">
                        <Link
                          to={getDashboardLink()}
                          className="flex items-center space-x-3 px-4 py-2 text-gray-700 hover:bg-gray-100 transition-colors"
                          onClick={() => setIsProfileDropdownOpen(false)}
                        >
                          <UserCircleIcon className="h-5 w-5" />
                          <span>Dashboard</span>
                        </Link>
                        <Link
                          to="/profile"
                          className="flex items-center space-x-3 px-4 py-2 text-gray-700 hover:bg-gray-100 transition-colors"
                          onClick={() => setIsProfileDropdownOpen(false)}
                        >
                          <UserIcon className="h-5 w-5" />
                          <span>Profile Settings</span>
                        </Link>
                        <Link
                          to="/settings"
                          className="flex items-center space-x-3 px-4 py-2 text-gray-700 hover:bg-gray-100 transition-colors"
                          onClick={() => setIsProfileDropdownOpen(false)}
                        >
                          <Cog6ToothIcon className="h-5 w-5" />
                          <span>Settings</span>
                        </Link>
                      </div>

                      <div className="border-t border-gray-200 pt-2">
                        <button
                          onClick={() => {
                            logout();
                            setIsProfileDropdownOpen(false);
                          }}
                          className="flex items-center space-x-3 w-full px-4 py-2 text-red-600 hover:bg-red-50 transition-colors"
                        >
                          <ArrowRightOnRectangleIcon className="h-5 w-5" />
                          <span>Sign Out</span>
                        </button>
                      </div>
                    </div>
                  )}
                </div>
              </>
            ) : (
              <div className="flex items-center space-x-4">
                <Link
                  to="/login"
                  className="text-gray-600 hover:text-gray-900 font-medium transition-colors"
                >
                  Sign In
                </Link>
                <Link
                  to="/register"
                  className="bg-blue-600 text-white px-4 py-2 rounded-lg font-medium hover:bg-blue-700 transition-colors"
                >
                  Get Started
                </Link>
              </div>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}