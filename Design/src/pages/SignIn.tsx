import { Link } from 'react-router-dom';
import AuthLayout from '../components/AuthLayout';

export default function SignIn() {
  return (
    <AuthLayout
      title="Welcome Back"
      subtitle="Sign in to your account to order your favorite artisan teas and earn rewards."
      imageSrc="https://images.unsplash.com/photo-1556679343-c7306c1976bc?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80"
    >
      <div className="bg-white p-8 rounded-3xl shadow-xl border border-gray-100">
        <h1 className="text-3xl font-serif font-bold text-gray-900 mb-2 text-center">Sign In</h1>
        <p className="text-gray-500 text-center mb-8">Enter your details to access your account.</p>

        <form className="space-y-6">
          <div>
            <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-2">Email Address</label>
            <input
              type="email"
              id="email"
              className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition-shadow"
              placeholder="you@example.com"
            />
          </div>

          <div>
            <div className="flex justify-between items-center mb-2">
              <label htmlFor="password" className="block text-sm font-medium text-gray-700">Password</label>
              <Link to="/reset-password" className="text-sm font-medium text-emerald-600 hover:text-emerald-500">
                Forgot password?
              </Link>
            </div>
            <input
              type="password"
              id="password"
              className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition-shadow"
              placeholder="••••••••"
            />
          </div>

          <div className="flex items-center">
            <input
              id="remember-me"
              name="remember-me"
              type="checkbox"
              className="h-4 w-4 text-emerald-600 focus:ring-emerald-500 border-gray-300 rounded"
            />
            <label htmlFor="remember-me" className="ml-2 block text-sm text-gray-700">
              Remember me
            </label>
          </div>

          <button
            type="submit"
            className="w-full flex justify-center py-3 px-4 border border-transparent rounded-xl shadow-sm text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-colors"
          >
            Sign In
          </button>
        </form>

        <div className="mt-8 text-center">
          <p className="text-sm text-gray-600">
            Don't have an account?{' '}
            <Link to="/signup" className="font-medium text-emerald-600 hover:text-emerald-500">
              Sign up
            </Link>
          </p>
        </div>
      </div>
    </AuthLayout>
  );
}
