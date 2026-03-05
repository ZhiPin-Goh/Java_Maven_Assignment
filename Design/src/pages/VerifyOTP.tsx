import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate, Link } from 'react-router-dom';
import AuthLayout from '../components/AuthLayout';
import { ArrowLeft, CheckCircle2 } from 'lucide-react';

export default function VerifyOTP() {
  const location = useLocation();
  const navigate = useNavigate();
  const [otp, setOtp] = useState(['', '', '', '', '', '']);
  const [isVerified, setIsVerified] = useState(false);
  const email = location.state?.email || 'your email';

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

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (otp.join('').length === 6) {
      setIsVerified(true);
      setTimeout(() => {
        navigate('/login');
      }, 2000);
    }
  };

  return (
    <AuthLayout
      title="Verify Your Account"
      subtitle="We've sent a 6-digit verification code to your email. Please enter it below to complete your registration."
      imageSrc="https://images.unsplash.com/photo-1556679343-c7306c1976bc?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80"
    >
      <div className="bg-white p-8 rounded-3xl shadow-xl border border-gray-100">
        {!isVerified ? (
          <>
            <Link to="/signup" className="inline-flex items-center text-sm font-medium text-emerald-600 hover:text-emerald-500 mb-6">
              <ArrowLeft className="w-4 h-4 mr-1" />
              Back to sign up
            </Link>
            
            <h1 className="text-3xl font-serif font-bold text-gray-900 mb-2 text-center">Verify OTP</h1>
            <p className="text-gray-500 text-center mb-8">
              Please enter the 6-digit code sent to <br/>
              <span className="font-medium text-gray-900">{email}</span>
            </p>

            <form onSubmit={handleSubmit} className="space-y-8">
              <div className="flex justify-between gap-2">
                {otp.map((data, index) => (
                  <input
                    key={index}
                    type="text"
                    maxLength={1}
                    value={data}
                    onChange={(e) => handleChange(e.target, index)}
                    onKeyDown={(e) => handleKeyDown(e, index)}
                    className="w-12 h-14 border-2 border-gray-200 rounded-xl text-center text-2xl font-bold focus:border-emerald-500 focus:ring-2 focus:ring-emerald-200 transition-all outline-none"
                  />
                ))}
              </div>

              <button
                type="submit"
                disabled={otp.join('').length !== 6}
                className="w-full flex justify-center py-3 px-4 border border-transparent rounded-xl shadow-sm text-sm font-medium text-white bg-emerald-800 hover:bg-emerald-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
              >
                Verify & Complete
              </button>

              <div className="text-center">
                <p className="text-sm text-gray-500">
                  Didn't receive the code?{' '}
                  <button type="button" className="font-medium text-emerald-600 hover:text-emerald-500">
                    Resend Code
                  </button>
                </p>
              </div>
            </form>
          </>
        ) : (
          <div className="text-center py-8">
            <div className="mx-auto flex items-center justify-center h-20 w-20 rounded-full bg-emerald-100 mb-6">
              <CheckCircle2 className="h-10 w-10 text-emerald-600" />
            </div>
            <h1 className="text-3xl font-serif font-bold text-gray-900 mb-2">Verified!</h1>
            <p className="text-gray-500 mb-4">Your account has been successfully verified.</p>
            <p className="text-sm text-gray-400">Redirecting to login...</p>
          </div>
        )}
      </div>
    </AuthLayout>
  );
}
