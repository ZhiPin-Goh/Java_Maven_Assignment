import { Link } from 'react-router-dom';
import { CheckCircle, ArrowRight, ShoppingBag } from 'lucide-react';
import { motion } from 'motion/react';

export default function CheckoutSuccess() {
  return (
    <div className="min-h-[70vh] flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
      <motion.div 
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="max-w-md w-full bg-white rounded-3xl shadow-xl overflow-hidden text-center p-10 relative"
      >
        {/* Decorative background elements */}
        <div className="absolute top-0 left-0 w-full h-32 bg-emerald-50 rounded-b-[50%] -z-0"></div>
        
        <div className="relative z-10">
          <motion.div 
            initial={{ scale: 0 }}
            animate={{ scale: 1 }}
            transition={{ type: "spring", stiffness: 260, damping: 20, delay: 0.2 }}
            className="mx-auto flex items-center justify-center h-24 w-24 rounded-full bg-emerald-100 mb-6 shadow-inner"
          >
            <CheckCircle className="h-12 w-12 text-emerald-600" />
          </motion.div>
          
          <motion.h2 
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ delay: 0.4 }}
            className="text-3xl font-serif font-bold text-gray-900 mb-2"
          >
            Payment Successful!
          </motion.h2>
          <motion.p 
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ delay: 0.5 }}
            className="text-gray-500 mb-8"
          >
            Thank you for your order. We are preparing your delicious drinks.
          </motion.p>
          
          <motion.div 
            initial={{ opacity: 0, x: -20 }}
            animate={{ opacity: 1, x: 0 }}
            transition={{ delay: 0.6 }}
            className="bg-gray-50 rounded-2xl p-6 mb-8 text-left border border-gray-100"
          >
            <h3 className="text-sm font-medium text-gray-500 uppercase tracking-wider mb-4">Order Details</h3>
            
            <div className="flex justify-between items-center mb-3">
              <span className="text-gray-600">Order Number</span>
              <span className="font-mono font-medium text-gray-900">#ZT-8492</span>
            </div>
            
            <div className="flex justify-between items-center mb-3">
              <span className="text-gray-600">Date & Time</span>
              <span className="text-gray-900">Oct 24, 2023 - 14:30</span>
            </div>
            
            <div className="flex justify-between items-center mb-4">
              <span className="text-gray-600">Payment Method</span>
              <span className="flex items-center text-gray-900">
                <span className="w-8 h-5 bg-blue-600 rounded text-white text-[10px] font-bold flex items-center justify-center mr-2">VISA</span>
                •••• 4242
              </span>
            </div>
            
            <div className="border-t border-gray-200 pt-4 flex justify-between items-center">
              <span className="font-medium text-gray-900">Total Amount</span>
              <span className="text-xl font-bold text-emerald-700">$19.26</span>
            </div>
          </motion.div>
          
          <div className="space-y-4">
            <motion.div
              initial={{ opacity: 0, y: 10 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: 0.7 }}
            >
              <Link to="/orders/ZT-8492" className="w-full flex items-center justify-center px-8 py-4 border border-transparent text-base font-medium rounded-xl text-white bg-emerald-800 hover:bg-emerald-900 transition-colors shadow-md group">
                Track Order
                <ArrowRight className="ml-2 -mr-1 h-5 w-5 group-hover:translate-x-1 transition-transform" />
              </Link>
            </motion.div>
            
            <motion.div
              initial={{ opacity: 0, y: 10 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ delay: 0.8 }}
            >
              <Link to="/" className="w-full flex items-center justify-center px-8 py-4 border-2 border-emerald-100 text-base font-medium rounded-xl text-emerald-700 bg-white hover:bg-emerald-50 transition-colors">
                <ShoppingBag className="mr-2 h-5 w-5" />
                Continue Shopping
              </Link>
            </motion.div>
          </div>
        </div>
      </motion.div>
    </div>
  );
}
