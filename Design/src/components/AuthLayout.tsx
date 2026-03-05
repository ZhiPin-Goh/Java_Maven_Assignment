import React from 'react';
import { Link } from 'react-router-dom';

export default function AuthLayout({ children, title, subtitle, imageSrc }: { children: React.ReactNode, title: string, subtitle: string, imageSrc: string }) {
  return (
    <div className="min-h-screen flex flex-col md:flex-row bg-white font-sans">
      {/* Left side - Image */}
      <div className="md:w-1/2 relative hidden md:block">
        <img src={imageSrc} alt="Zen Tea" className="absolute inset-0 w-full h-full object-cover" />
        <div className="absolute inset-0 bg-emerald-900/40 mix-blend-multiply"></div>
        <div className="absolute inset-0 flex flex-col justify-center px-12 lg:px-24">
          <Link to="/" className="text-white text-3xl font-serif font-bold mb-8">Zen Tea</Link>
          <h2 className="text-4xl lg:text-5xl font-serif font-bold text-white mb-6 leading-tight">
            {title}
          </h2>
          <p className="text-emerald-50 text-lg max-w-md">
            {subtitle}
          </p>
        </div>
      </div>

      {/* Right side - Form */}
      <div className="md:w-1/2 flex items-center justify-center p-8 sm:p-12 lg:p-24 bg-gray-50">
        <div className="w-full max-w-md">
          <Link to="/" className="md:hidden text-emerald-800 text-3xl font-serif font-bold mb-8 block text-center">Zen Tea</Link>
          {children}
        </div>
      </div>
    </div>
  );
}
