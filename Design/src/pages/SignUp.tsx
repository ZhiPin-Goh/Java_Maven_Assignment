import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import AuthLayout from '../components/AuthLayout';
import { Eye, EyeOff, Check, X } from 'lucide-react';

export default function SignUp() {
  const navigate = useNavigate();
  const [showPassword, setShowPassword] = useState(false);
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    phone: '',
    password: '',
  });

  const passwordRequirements = [
    { label: 'At least 8 characters', test: (p: string) => p.length >= 8 },
    { label: 'Uppercase letter', test: (p: string) => /[A-Z]/.test(p) },
    { label: 'Lowercase letter', test: (p: string) => /[a-z]/.test(p) },
    { label: 'A digit', test: (p: string) => /\d/.test(p) },
    { label: 'Special character', test: (p: string) => /[^A-Za-z0-9]/.test(p) },
  ];

  const isPasswordValid = passwordRequirements.every(req => req.test(formData.password));

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (isPasswordValid) {
      // In a real app, we would call the API here
      navigate('/verify-otp', { state: { email: formData.email } });
    }
  };

  return (
    <AuthLayout
      title="Join the Zen Tea Family"
      subtitle="Create an account to start earning rewards, save your favorite orders, and enjoy exclusive offers."
      imageSrc="https://images.unsplash.com/photo-1517244326705-73955d81540f?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80"
    >
      <div className="bg-white p-8 rounded-3xl shadow-xl border border-gray-100">
        <h1 className="text-3xl font-serif font-bold text-gray-900 mb-2 text-center">Create Account</h1>
        <p className="text-gray-500 text-center mb-8">Join us to start earning rewards today.</p>

        <form onSubmit={handleSubmit} className="space-y-5">
          <div>
            <label htmlFor="name" className="block text-sm font-medium text-gray-700 mb-1.5">Full Name</label>
            <input
              type="text"
              id="name"
              required
              value={formData.name}
              onChange={(e) => setFormData({ ...formData, name: e.target.value })}
              className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition-shadow"
              placeholder="John Doe"
            />
          </div>

          <div>
            <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-1.5">Email Address</label>
            <input
              type="email"
              id="email"
              required
              value={formData.email}
              onChange={(e) => setFormData({ ...formData, email: e.target.value })}
              className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition-shadow"
              placeholder="you@example.com"
            />
          </div>

          <div>
            <label htmlFor="phone" className="block text-sm font-medium text-gray-700 mb-1.5">Phone Number</label>
            <input
              type="tel"
              id="phone"
              required
              value={formData.phone}
              onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
              className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition-shadow"
              placeholder="+1 (555) 000-0000"
            />
          </div>

          <div>
            <label htmlFor="password" className="block text-sm font-medium text-gray-700 mb-1.5">Password</label>
            <div className="relative">
              <input
                type={showPassword ? 'text' : 'password'}
                id="password"
                required
                value={formData.password}
                onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition-shadow"
                placeholder="••••••••"
              />
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600"
              >
                {showPassword ? <EyeOff size={20} /> : <Eye size={20} />}
              </button>
            </div>
            
            {/* Password Requirements Checklist */}
            <div className="mt-3 grid grid-cols-1 sm:grid-cols-2 gap-2">
              {passwordRequirements.map((req, i) => {
                const met = req.test(formData.password);
                return (
                  <div key={i} className={`flex items-center text-xs ${met ? 'text-emerald-600' : 'text-gray-400'}`}>
                    {met ? <Check size={12} className="mr-1.5" /> : <X size={12} className="mr-1.5" />}
                    {req.label}
                  </div>
                );
              })}
            </div>
          </div>

          <div className="flex items-center pt-2">
            <input
              id="terms"
              name="terms"
              type="checkbox"
              required
              className="h-4 w-4 text-emerald-600 focus:ring-emerald-500 border-gray-300 rounded"
            />
            <label htmlFor="terms" className="ml-2 block text-sm text-gray-700">
              I agree to the <a href="#" className="text-emerald-600 hover:text-emerald-500">Terms of Service</a> and <a href="#" className="text-emerald-600 hover:text-emerald-500">Privacy Policy</a>
            </label>
          </div>

          <button
            type="submit"
            disabled={!isPasswordValid}
            className="w-full flex justify-center py-3 px-4 border border-transparent rounded-xl shadow-sm text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
          >
            Create Account
          </button>
        </form>

        <div className="mt-8 text-center">
          <p className="text-sm text-gray-600">
            Already have an account?{' '}
            <Link to="/login" className="font-medium text-emerald-600 hover:text-emerald-500">
              Sign in
            </Link>
          </p>
        </div>
      </div>
    </AuthLayout>
  );
}
