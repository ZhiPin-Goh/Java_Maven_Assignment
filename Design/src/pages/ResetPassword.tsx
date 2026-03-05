import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import AuthLayout from '../components/AuthLayout';
import { ArrowLeft, CheckCircle2 } from 'lucide-react';

export default function ResetPassword() {
  const [step, setStep] = useState<1 | 2 | 3 | 4>(1);
  const [email, setEmail] = useState('');
  const [otp, setOtp] = useState(['', '', '', '', '', '']);
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const handleSendOtp = (e: React.FormEvent) => {
    e.preventDefault();
    if (email) setStep(2);
  };

  const handleChange = (element: HTMLInputElement, index: number) => {
    if (isNaN(Number(element.value))) return false;

    setOtp([...otp.map((d, idx) => (idx === index ? element.value : d))]);

    // Focus next input
    if (element.nextSibling && element.value !== '') {
      (element.nextSibling as HTMLInputElement).focus();
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>, index: number) => {
    if (e.key === 'Backspace') {
      if (otp[index] === '' && (e.target as HTMLInputElement).previousSibling) {
        ((e.target as HTMLInputElement).previousSibling as HTMLInputElement).focus();
      }
    }
  };

  const handleVerifyOtp = (e: React.FormEvent) => {
    e.preventDefault();
    if (otp.join('').length === 6) setStep(3);
  };

  const handleResetPassword = (e: React.FormEvent) => {
    e.preventDefault();
    if (password && password === confirmPassword) setStep(4);
  };

  return (
    <AuthLayout
      title="Reset Your Password"
      subtitle="Don't worry, we'll help you get back to ordering your favorite artisan teas."
      imageSrc="https://images.unsplash.com/photo-1558855567-1a34221b20ea?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80"
    >
      <div className="bg-white p-8 rounded-3xl shadow-xl border border-gray-100">
        {step < 4 && (
          <button 
            onClick={() => step === 1 ? window.history.back() : setStep((prev) => (prev - 1) as 1 | 2 | 3)} 
            className="inline-flex items-center text-sm font-medium text-emerald-600 hover:text-emerald-500 mb-6"
          >
            <ArrowLeft className="w-4 h-4 mr-1" />
            {step === 1 ? 'Back to sign in' : 'Back'}
          </button>
        )}
        
        {step === 1 && (
          <>
            <h1 className="text-3xl font-serif font-bold text-gray-900 mb-2 text-center">Reset Password</h1>
            <p className="text-gray-500 text-center mb-8">Enter your email address and we'll send you a 6-digit code to reset your password.</p>

            <form onSubmit={handleSendOtp} className="space-y-6">
              <div>
                <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-2">Email Address</label>
                <input
                  type="email"
                  id="email"
                  required
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition-shadow"
                  placeholder="you@example.com"
                />
              </div>

              <button
                type="submit"
                className="w-full flex justify-center py-3 px-4 border border-transparent rounded-xl shadow-sm text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-colors"
              >
                Send Verification Code
              </button>
            </form>
          </>
        )}

        {step === 2 && (
          <>
            <h1 className="text-3xl font-serif font-bold text-gray-900 mb-2 text-center">Verify Code</h1>
            <p className="text-gray-500 text-center mb-8">We've sent a 6-digit verification code to <span className="font-medium text-gray-900">{email}</span>.</p>

            <form onSubmit={handleVerifyOtp} className="space-y-8">
              <div className="flex justify-between gap-2">
                {otp.map((data, index) => (
                  <input
                    key={index}
                    type="text"
                    maxLength={1}
                    value={data}
                    onChange={(e) => handleChange(e.target, index)}
                    onKeyDown={(e) => handleKeyDown(e, index)}
                    className="w-10 h-12 sm:w-12 sm:h-14 border-2 border-gray-200 rounded-xl text-center text-2xl font-bold focus:border-emerald-500 focus:ring-2 focus:ring-emerald-200 transition-all outline-none"
                  />
                ))}
              </div>

              <button
                type="submit"
                disabled={otp.join('').length !== 6}
                className="w-full flex justify-center py-3 px-4 border border-transparent rounded-xl shadow-sm text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
              >
                Verify Code
              </button>
              
              <div className="text-center">
                <button type="button" className="text-sm font-medium text-emerald-600 hover:text-emerald-500">
                  Resend Code
                </button>
              </div>
            </form>
          </>
        )}

        {step === 3 && (
          <>
            <h1 className="text-3xl font-serif font-bold text-gray-900 mb-2 text-center">New Password</h1>
            <p className="text-gray-500 text-center mb-8">Create a new, strong password for your account.</p>

            <form onSubmit={handleResetPassword} className="space-y-6">
              <div>
                <label htmlFor="new-password" className="block text-sm font-medium text-gray-700 mb-2">New Password</label>
                <input
                  type="password"
                  id="new-password"
                  required
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition-shadow"
                  placeholder="••••••••"
                />
                <p className="mt-2 text-xs text-gray-500">
                  Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.
                </p>
              </div>
              
              <div>
                <label htmlFor="confirm-password" className="block text-sm font-medium text-gray-700 mb-2">Confirm New Password</label>
                <input
                  type="password"
                  id="confirm-password"
                  required
                  value={confirmPassword}
                  onChange={(e) => setConfirmPassword(e.target.value)}
                  className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:ring-2 focus:ring-emerald-500 focus:border-transparent transition-shadow"
                  placeholder="••••••••"
                />
                {password && confirmPassword && password !== confirmPassword && (
                  <p className="mt-2 text-sm text-red-600">Passwords do not match.</p>
                )}
              </div>

              <button
                type="submit"
                disabled={
                  !password || 
                  password !== confirmPassword || 
                  password.length < 8 || 
                  !/[A-Z]/.test(password) || 
                  !/[a-z]/.test(password) || 
                  !/\d/.test(password) || 
                  !/[^A-Za-z0-9]/.test(password)
                }
                className="w-full flex justify-center py-3 px-4 border border-transparent rounded-xl shadow-sm text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
              >
                Reset Password
              </button>
            </form>
          </>
        )}

        {step === 4 && (
          <div className="text-center py-8">
            <div className="mx-auto flex items-center justify-center h-16 w-16 rounded-full bg-emerald-100 mb-6">
              <CheckCircle2 className="h-8 w-8 text-emerald-600" />
            </div>
            <h1 className="text-3xl font-serif font-bold text-gray-900 mb-2">Password Reset!</h1>
            <p className="text-gray-500 mb-8">Your password has been successfully reset. You can now sign in with your new password.</p>
            <Link
              to="/login"
              className="w-full flex justify-center py-3 px-4 border border-transparent rounded-xl shadow-sm text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-colors"
            >
              Go to Sign In
            </Link>
          </div>
        )}
      </div>
    </AuthLayout>
  );
}
