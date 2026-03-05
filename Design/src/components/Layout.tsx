import { useState } from 'react';
import { Outlet, Link, useLocation } from 'react-router-dom';
import { Search, ShoppingBag, User, Instagram, Twitter, Facebook, Menu, X } from 'lucide-react';

export default function Layout() {
  const location = useLocation();
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  
  return (
    <div className="min-h-screen flex flex-col bg-gray-50 font-sans">
      <nav className="bg-white shadow-sm sticky top-0 z-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between h-16">
            <div className="flex items-center">
              <div className="flex items-center sm:hidden mr-4">
                <button 
                  onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
                  className="text-gray-500 hover:text-gray-700 focus:outline-none"
                >
                  {isMobileMenuOpen ? <X className="w-6 h-6" /> : <Menu className="w-6 h-6" />}
                </button>
              </div>
              <Link to="/" className="flex-shrink-0 flex items-center">
                <span className="text-2xl font-serif text-emerald-800">Zen Tea</span>
              </Link>
              <div className="hidden sm:ml-10 sm:flex sm:space-x-8">
                <Link to="/" className={`${location.pathname === '/' ? 'border-emerald-500 text-gray-900' : 'border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700'} inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium`}>Home</Link>
                <Link to="/drinks" className={`${location.pathname === '/drinks' ? 'border-emerald-500 text-gray-900' : 'border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700'} inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium`}>Drink</Link>
                <Link to="/cart" className={`${location.pathname === '/cart' ? 'border-emerald-500 text-gray-900' : 'border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700'} inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium`}>Cart</Link>
                <Link to="/locations" className={`${location.pathname === '/locations' ? 'border-emerald-500 text-gray-900' : 'border-transparent text-gray-500 hover:border-gray-300 hover:text-gray-700'} inline-flex items-center px-1 pt-1 border-b-2 text-sm font-medium`}>Location</Link>
              </div>
            </div>
            <div className="flex items-center space-x-4">
              <div className="relative hidden sm:block">
                <input type="text" placeholder="Search..." className="w-64 pl-10 pr-4 py-2 border border-gray-300 rounded-full text-sm focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:border-transparent" />
                <Search className="absolute left-3 top-2.5 text-gray-400 w-4 h-4" />
              </div>
              <Link to="/cart" className="text-gray-500 hover:text-emerald-600 relative">
                <ShoppingBag className="w-6 h-6" />
                <span className="absolute -top-1 -right-1 bg-emerald-500 text-white text-xs rounded-full h-4 w-4 flex items-center justify-center">2</span>
              </Link>
              <Link to="/profile" className="text-gray-500 hover:text-emerald-600">
                <User className="w-6 h-6" />
              </Link>
            </div>
          </div>
        </div>

        {/* Mobile Menu */}
        {isMobileMenuOpen && (
          <div className="sm:hidden bg-white border-t border-gray-200">
            <div className="pt-2 pb-3 space-y-1">
              <Link to="/" onClick={() => setIsMobileMenuOpen(false)} className={`${location.pathname === '/' ? 'bg-emerald-50 border-emerald-500 text-emerald-700' : 'border-transparent text-gray-500 hover:bg-gray-50 hover:border-gray-300 hover:text-gray-700'} block pl-3 pr-4 py-2 border-l-4 text-base font-medium`}>Home</Link>
              <Link to="/drinks" onClick={() => setIsMobileMenuOpen(false)} className={`${location.pathname === '/drinks' ? 'bg-emerald-50 border-emerald-500 text-emerald-700' : 'border-transparent text-gray-500 hover:bg-gray-50 hover:border-gray-300 hover:text-gray-700'} block pl-3 pr-4 py-2 border-l-4 text-base font-medium`}>Drink</Link>
              <Link to="/cart" onClick={() => setIsMobileMenuOpen(false)} className={`${location.pathname === '/cart' ? 'bg-emerald-50 border-emerald-500 text-emerald-700' : 'border-transparent text-gray-500 hover:bg-gray-50 hover:border-gray-300 hover:text-gray-700'} block pl-3 pr-4 py-2 border-l-4 text-base font-medium`}>Cart</Link>
              <Link to="/locations" onClick={() => setIsMobileMenuOpen(false)} className={`${location.pathname === '/locations' ? 'bg-emerald-50 border-emerald-500 text-emerald-700' : 'border-transparent text-gray-500 hover:bg-gray-50 hover:border-gray-300 hover:text-gray-700'} block pl-3 pr-4 py-2 border-l-4 text-base font-medium`}>Location</Link>
            </div>
            <div className="pt-4 pb-3 border-t border-gray-200">
              <div className="px-4 relative">
                <input type="text" placeholder="Search..." className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-full text-sm focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:border-transparent" />
                <Search className="absolute left-7 top-2.5 text-gray-400 w-4 h-4" />
              </div>
            </div>
          </div>
        )}
      </nav>

      <main className="flex-grow">
        <Outlet />
      </main>

      <footer className="bg-emerald-950 text-emerald-50 pt-16 pb-8 border-t border-emerald-900">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-12 mb-12">
            {/* Brand */}
            <div className="md:col-span-1">
              <Link to="/" className="inline-block mb-6">
                <span className="text-3xl font-serif text-white">Zen Tea</span>
              </Link>
              <p className="text-emerald-200/80 text-sm leading-relaxed max-w-sm">
                Crafting moments of peace through perfect cups of tea since 2010. 
                Experience the harmony of nature in every sip.
              </p>
            </div>
            
            {/* Links */}
            <div>
              <h3 className="text-sm font-bold uppercase tracking-wider text-emerald-400 mb-6">Shop</h3>
              <ul className="space-y-4 text-sm text-emerald-200/80">
                <li><Link to="/" className="hover:text-white transition-colors">Home</Link></li>
                <li><Link to="/drinks" className="hover:text-white transition-colors">Drink</Link></li>
                <li><Link to="/cart" className="hover:text-white transition-colors">Cart</Link></li>
                <li><Link to="/locations" className="hover:text-white transition-colors">Location</Link></li>
              </ul>
            </div>

            {/* Socials */}
            <div className="flex flex-col md:items-end">
              <div className="text-left md:text-right">
                <h3 className="text-sm font-bold uppercase tracking-wider text-emerald-400 mb-6">Follow Us</h3>
                <div className="flex space-x-4">
                  <Link to="#" className="text-emerald-200/80 hover:text-white transition-colors p-2.5 bg-emerald-900/50 rounded-full hover:bg-emerald-800"><Instagram className="w-5 h-5" /></Link>
                  <Link to="#" className="text-emerald-200/80 hover:text-white transition-colors p-2.5 bg-emerald-900/50 rounded-full hover:bg-emerald-800"><Twitter className="w-5 h-5" /></Link>
                  <Link to="#" className="text-emerald-200/80 hover:text-white transition-colors p-2.5 bg-emerald-900/50 rounded-full hover:bg-emerald-800"><Facebook className="w-5 h-5" /></Link>
                </div>
              </div>
            </div>
          </div>
          
          <div className="border-t border-emerald-900/50 pt-8 flex flex-col md:flex-row justify-between items-center gap-4">
            <p className="text-emerald-200/60 text-sm">&copy; {new Date().getFullYear()} Zen Tea. All rights reserved.</p>
            <div className="flex space-x-6 text-sm text-emerald-200/60">
              <Link to="#" className="hover:text-white transition-colors">Privacy Policy</Link>
              <Link to="#" className="hover:text-white transition-colors">Terms of Service</Link>
            </div>
          </div>
        </div>
      </footer>
    </div>
  );
}
