import { Link, useParams } from 'react-router-dom';
import { CheckCircle, Coffee, ChevronLeft } from 'lucide-react';

export default function OrderTracking() {
  const { id } = useParams();
  
  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
      <div className="mb-8 flex items-center">
        <Link to="/orders" className="text-gray-500 hover:text-emerald-600 transition-colors mr-4">
          <ChevronLeft className="w-6 h-6" />
        </Link>
        <h1 className="text-3xl font-serif font-bold text-gray-900">Order {id || '#ZT-8492'}</h1>
      </div>

      <div className="flex flex-col lg:flex-row gap-12">
        {/* Tracking Timeline */}
        <div className="lg:w-2/3">
          <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-8 mb-8">
            <h2 className="text-xl font-bold text-gray-900 mb-8">Tracking Status</h2>
            
            <div className="relative border-l-2 border-emerald-200 ml-4 space-y-12 pb-4">
              {/* Step 1: Placed */}
              <div className="relative flex items-center">
                <div className="absolute -left-2.5 w-5 h-5 rounded-full bg-emerald-500 ring-4 ring-white flex items-center justify-center">
                  <CheckCircle className="w-3 h-3 text-white" />
                </div>
                <div className="ml-8">
                  <h3 className="text-lg font-bold text-gray-900">Order Placed</h3>
                  <p className="text-sm text-gray-500">Oct 24, 2023 - 14:30</p>
                </div>
              </div>

              {/* Step 2: Preparing */}
              <div className="relative flex items-center">
                <div className="absolute -left-2.5 w-5 h-5 rounded-full bg-emerald-500 ring-4 ring-white flex items-center justify-center animate-pulse">
                  <Coffee className="w-3 h-3 text-white" />
                </div>
                <div className="ml-8">
                  <h3 className="text-lg font-bold text-emerald-700">Preparing</h3>
                  <p className="text-sm text-gray-500">Our baristas are crafting your order.</p>
                </div>
              </div>

              {/* Step 3: Ready */}
              <div className="relative flex items-center opacity-50">
                <div className="absolute -left-2.5 w-5 h-5 rounded-full bg-gray-200 ring-4 ring-white"></div>
                <div className="ml-8">
                  <h3 className="text-lg font-medium text-gray-900">Ready</h3>
                  <p className="text-sm text-gray-500">Estimated: 14:45</p>
                </div>
              </div>

              {/* Step 4: Completed */}
              <div className="relative flex items-center opacity-50">
                <div className="absolute -left-2.5 w-5 h-5 rounded-full bg-gray-200 ring-4 ring-white"></div>
                <div className="ml-8">
                  <h3 className="text-lg font-medium text-gray-900">Completed</h3>
                  <p className="text-sm text-gray-500">Enjoy your Zen Tea!</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Order Items */}
        <div className="lg:w-1/3">
          <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-8 sticky top-24">
            <h2 className="text-xl font-bold text-gray-900 mb-6">Order Items</h2>
            
            <div className="space-y-6">
              <div className="flex justify-between items-start border-b border-gray-100 pb-6">
                <div className="flex gap-4">
                  <div className="w-16 h-16 bg-emerald-50 rounded-lg overflow-hidden flex-shrink-0">
                    <img src="https://images.unsplash.com/photo-1515823662972-da6a2e4d3002?ixlib=rb-4.0.3&auto=format&fit=crop&w=150&q=80" alt="Matcha" className="w-full h-full object-cover mix-blend-multiply" />
                  </div>
                  <div>
                    <h3 className="font-bold text-gray-900">Signature Matcha Crème</h3>
                    <p className="text-sm text-gray-500">Large • Iced • 50% Sweet</p>
                    <p className="text-xs text-gray-400 mt-1">+ Honey Boba</p>
                  </div>
                </div>
                <div className="text-right">
                  <span className="font-bold text-gray-900">$6.25</span>
                  <p className="text-sm text-gray-500">Qty: 1</p>
                </div>
              </div>

              <div className="flex justify-between items-start">
                <div className="flex gap-4">
                  <div className="w-16 h-16 bg-orange-50 rounded-lg overflow-hidden flex-shrink-0">
                    <img src="https://images.unsplash.com/photo-1536935338788-846bb9981813?ixlib=rb-4.0.3&auto=format&fit=crop&w=150&q=80" alt="Mango" className="w-full h-full object-cover mix-blend-multiply" />
                  </div>
                  <div>
                    <h3 className="font-bold text-gray-900">Mango Passion Fruit</h3>
                    <p className="text-sm text-gray-500">Medium • Iced • 100% Sweet</p>
                    <p className="text-xs text-gray-400 mt-1">+ Lychee Jelly</p>
                  </div>
                </div>
                <div className="text-right">
                  <span className="font-bold text-gray-900">$11.50</span>
                  <p className="text-sm text-gray-500">Qty: 2</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
