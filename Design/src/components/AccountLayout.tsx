import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { User, ShoppingBag, Shield, LogOut } from 'lucide-react';

export default function AccountLayout({ children }: { children: React.ReactNode }) {
  const location = useLocation();
  
  const navItems = [
    { path: '/profile', label: 'Profile Information', icon: User },
    { path: '/orders', label: 'Order History', icon: ShoppingBag },
    { path: '/settings/security', label: 'Security Settings', icon: Shield },
  ];

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
      <div className="flex flex-col md:flex-row gap-8">
        {/* Sidebar */}
        <div className="w-full md:w-64 flex-shrink-0">
          <div className="bg-white rounded-2xl shadow-sm border border-gray-100 overflow-hidden">
            <div className="p-6 border-b border-gray-100 text-center">
              <div className="w-20 h-20 mx-auto bg-emerald-100 rounded-full flex items-center justify-center mb-4">
                <span className="text-2xl font-bold text-emerald-700">JD</span>
              </div>
              <h2 className="text-xl font-bold text-gray-900">John Doe</h2>
              <p className="text-sm text-gray-500">john.doe@example.com</p>
            </div>
            
            <nav className="p-4 space-y-2">
              {navItems.map((item) => {
                const Icon = item.icon;
                const isActive = location.pathname === item.path;
                return (
                  <Link
                    key={item.path}
                    to={item.path}
                    className={`flex items-center px-4 py-3 rounded-xl transition-colors ${isActive ? 'bg-emerald-50 text-emerald-700 font-medium' : 'text-gray-600 hover:bg-gray-50 hover:text-gray-900'}`}
                  >
                    <Icon className={`w-5 h-5 mr-3 ${isActive ? 'text-emerald-600' : 'text-gray-400'}`} />
                    {item.label}
                  </Link>
                );
              })}
              
              <div className="pt-4 mt-4 border-t border-gray-100">
                <Link to="/login" className="flex items-center px-4 py-3 rounded-xl text-red-600 hover:bg-red-50 transition-colors">
                  <LogOut className="w-5 h-5 mr-3 text-red-500" />
                  Sign Out
                </Link>
              </div>
            </nav>
          </div>
        </div>

        {/* Main Content */}
        <div className="flex-grow">
          {children}
        </div>
      </div>
    </div>
  );
}
