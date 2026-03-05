import { Link } from 'react-router-dom';
import { Trash2, Plus, Minus, ArrowRight, ShieldCheck, Tag } from 'lucide-react';

export default function Cart() {
  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
      <h1 className="text-3xl font-serif font-bold text-gray-900 mb-8">Your Cart</h1>

      <div className="flex flex-col lg:flex-row gap-12">
        {/* Cart Items */}
        <div className="lg:w-2/3">
          <div className="bg-white rounded-2xl shadow-sm border border-gray-100 overflow-hidden">
            {/* Item 1 */}
            <div className="p-6 border-b border-gray-100 flex flex-col sm:flex-row gap-6 items-start sm:items-center">
              <div className="w-24 h-24 bg-emerald-50 rounded-xl overflow-hidden flex-shrink-0">
                <img src="https://images.unsplash.com/photo-1515823662972-da6a2e4d3002?ixlib=rb-4.0.3&auto=format&fit=crop&w=300&q=80" alt="Matcha" className="w-full h-full object-cover mix-blend-multiply" />
              </div>
              <div className="flex-grow">
                <div className="flex justify-between items-start mb-1">
                  <h3 className="text-lg font-bold text-gray-900">Signature Matcha Crème</h3>
                  <span className="font-bold text-gray-900">$6.25</span>
                </div>
                <p className="text-sm text-gray-500 mb-3">Large • Iced • 50% Sweet • Less Ice</p>
                <div className="flex flex-wrap gap-2 mb-4">
                  <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-emerald-100 text-emerald-800">
                    + Honey Boba ($0.75)
                  </span>
                </div>
                <div className="flex justify-between items-center">
                  <div className="flex items-center border border-gray-200 rounded-lg">
                    <button className="p-2 text-gray-500 hover:text-emerald-600 transition-colors">
                      <Minus className="w-4 h-4" />
                    </button>
                    <span className="w-8 text-center font-medium text-gray-900 text-sm">1</span>
                    <button className="p-2 text-gray-500 hover:text-emerald-600 transition-colors">
                      <Plus className="w-4 h-4" />
                    </button>
                  </div>
                  <button className="text-gray-400 hover:text-red-500 transition-colors flex items-center text-sm font-medium">
                    <Trash2 className="w-4 h-4 mr-1" />
                    Remove
                  </button>
                </div>
              </div>
            </div>

            {/* Item 2 */}
            <div className="p-6 flex flex-col sm:flex-row gap-6 items-start sm:items-center">
              <div className="w-24 h-24 bg-orange-50 rounded-xl overflow-hidden flex-shrink-0">
                <img src="https://images.unsplash.com/photo-1536935338788-846bb9981813?ixlib=rb-4.0.3&auto=format&fit=crop&w=300&q=80" alt="Mango" className="w-full h-full object-cover mix-blend-multiply" />
              </div>
              <div className="flex-grow">
                <div className="flex justify-between items-start mb-1">
                  <h3 className="text-lg font-bold text-gray-900">Mango Passion Fruit</h3>
                  <span className="font-bold text-gray-900">$11.50</span>
                </div>
                <p className="text-sm text-gray-500 mb-3">Medium • Iced • 100% Sweet • Regular Ice</p>
                <div className="flex flex-wrap gap-2 mb-4">
                  <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-orange-100 text-orange-800">
                    + Lychee Jelly ($0.75)
                  </span>
                </div>
                <div className="flex justify-between items-center">
                  <div className="flex items-center border border-gray-200 rounded-lg">
                    <button className="p-2 text-gray-500 hover:text-emerald-600 transition-colors">
                      <Minus className="w-4 h-4" />
                    </button>
                    <span className="w-8 text-center font-medium text-gray-900 text-sm">2</span>
                    <button className="p-2 text-gray-500 hover:text-emerald-600 transition-colors">
                      <Plus className="w-4 h-4" />
                    </button>
                  </div>
                  <button className="text-gray-400 hover:text-red-500 transition-colors flex items-center text-sm font-medium">
                    <Trash2 className="w-4 h-4 mr-1" />
                    Remove
                  </button>
                </div>
              </div>
            </div>
          </div>

          {/* Add more items prompt */}
          <div className="mt-6 text-center">
            <Link to="/" className="text-emerald-600 font-medium hover:text-emerald-700 transition-colors inline-flex items-center">
              <Plus className="w-4 h-4 mr-1" />
              Add more items
            </Link>
          </div>
        </div>

        {/* Order Summary */}
        <div className="lg:w-1/3">
          <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-6 sticky top-24">
            <h2 className="text-xl font-bold text-gray-900 mb-6">Order Summary</h2>
            
            <div className="space-y-4 mb-6">
              <div className="flex justify-between text-gray-600">
                <span>Subtotal (3 items)</span>
                <span>$17.75</span>
              </div>
              <div className="flex justify-between text-gray-600">
                <span>Tax (8.5%)</span>
                <span>$1.51</span>
              </div>
              <div className="flex justify-between text-gray-600">
                <span>Pickup Fee</span>
                <span>Free</span>
              </div>
            </div>

            <div className="border-t border-gray-100 pt-4 mb-6">
              <div className="flex justify-between items-center">
                <span className="text-lg font-bold text-gray-900">Total</span>
                <span className="text-2xl font-bold text-emerald-700">$19.26</span>
              </div>
              <p className="text-xs text-gray-500 mt-1 text-right">Includes taxes and fees</p>
            </div>

            <Link to="/checkout/success" className="w-full bg-emerald-800 text-white py-4 rounded-xl font-bold text-lg hover:bg-emerald-900 transition-colors shadow-lg flex justify-center items-center group">
              Checkout
              <ArrowRight className="w-5 h-5 ml-2 group-hover:translate-x-1 transition-transform" />
            </Link>

            <div className="mt-6 flex items-center justify-center text-sm text-gray-500 space-x-2">
              <ShieldCheck className="w-4 h-4 text-emerald-600" />
              <span>Secure checkout powered by Stripe</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
