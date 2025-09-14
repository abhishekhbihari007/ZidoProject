import { Link } from 'react-router-dom';

export default function NotFound() {
  return (
    <div className="min-h-screen bg-gradient-to-b from-white via-gray-50 to-white dark:from-gray-900 dark:via-gray-800 dark:to-gray-900 transition-all duration-500 flex items-center justify-center px-4 py-24 relative overflow-hidden">

      {/* Background Pattern */}
      <div className="absolute inset-0 bg-gradient-to-br from-green-50/50 via-transparent to-emerald-50/50 dark:from-green-900/20 dark:to-emerald-900/20"></div>
      <div className="absolute top-0 left-0 w-full h-full opacity-10 pointer-events-none">
        <div className="absolute top-20 left-10 w-72 h-72 bg-green-200 dark:bg-green-700 rounded-full mix-blend-multiply filter blur-xl animate-pulse"></div>
        <div className="absolute top-40 right-10 w-96 h-96 bg-emerald-200 dark:bg-emerald-700 rounded-full mix-blend-multiply filter blur-xl animate-pulse animation-delay-2000"></div>
      </div>

      <div className="relative max-w-2xl w-full text-center transition-all duration-1000 animate-fadeIn">
        
        {/* 404 Text */}
        <h1 className="text-6xl md:text-7xl lg:text-8xl font-black mb-8 leading-tight">
          <span className="bg-gradient-to-r from-green-600 via-emerald-600 to-teal-600 dark:from-green-400 dark:via-emerald-400 dark:to-teal-400 bg-clip-text text-transparent">
            404
          </span>
        </h1>

        {/* Message */}
        <h2 className="text-3xl md:text-4xl font-bold text-gray-900 dark:text-white mb-6">
          Oops! Page Not Found
        </h2>
        <p className="text-lg md:text-xl text-gray-600 dark:text-gray-300 mb-12 max-w-xl mx-auto leading-relaxed font-light">
          The page you’re looking for seems to have disappeared. But don’t worry, you can easily find your way back.
        </p>

        {/* Action Buttons */}
        <div className="flex flex-col sm:flex-row justify-center gap-6">
          <Link 
            to="/" 
            className="group px-10 py-4 rounded-2xl bg-gradient-to-r from-green-600 via-emerald-600 to-teal-600 text-white font-semibold text-lg hover:from-green-700 hover:via-emerald-700 hover:to-teal-700 transition-all duration-300 shadow-xl hover:shadow-2xl hover:scale-105 transform"
          >
            <span className="flex items-center justify-center gap-2">
              <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" />
              </svg>
              Go Home
              <svg className="w-5 h-5 group-hover:translate-x-1 transition-transform" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M17 8l4 4m0 0l-4 4m4-4H3" />
              </svg>
            </span>
          </Link>

          <button 
            onClick={() => window.history.back()}
            className="px-10 py-4 rounded-2xl border-2 border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-200 font-semibold text-lg hover:bg-gray-50 dark:hover:bg-gray-800 hover:border-green-300 dark:hover:border-green-600 transition-all duration-300"
          >
            <span className="flex items-center justify-center gap-2">
              <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M10 19l-7-7m0 0l7-7m-7 7h18" />
              </svg>
              Go Back
            </span>
          </button>
        </div>

        {/* Decorative Dots */}
        <div className="mt-12 flex justify-center space-x-2">
          <div className="w-2 h-2 bg-green-400 rounded-full animate-bounce"></div>
          <div className="w-2 h-2 bg-emerald-400 rounded-full animate-bounce" style={{ animationDelay: '0.1s' }}></div>
          <div className="w-2 h-2 bg-teal-400 rounded-full animate-bounce" style={{ animationDelay: '0.2s' }}></div>
        </div>
      </div>
    </div>
  );
}
