import { Link } from 'react-router-dom';
import { Clock, CheckCircle2, ArrowRight, Sparkles, Plus } from 'lucide-react';
import { motion } from 'motion/react';

export default function Home() {
  const products = [
    { id: 1, name: 'Signature Matcha Crème', price: '$5.50', image: 'https://images.unsplash.com/photo-1515823662972-da6a2e4d3002?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80', tag: 'Bestseller' },
    { id: 2, name: 'Classic Brown Sugar Boba', price: '$5.00', image: 'https://images.unsplash.com/photo-1558855567-1a34221b20ea?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80', tag: 'Popular' },
    { id: 3, name: 'Jasmine Green Tea', price: '$4.25', image: 'https://images.unsplash.com/photo-1556679343-c7306c1976bc?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80' },
    { id: 4, name: 'Taro Milk Tea', price: '$5.25', image: 'https://images.unsplash.com/photo-1625860633266-112658591961?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80' },
    { id: 5, name: 'Mango Passion Fruit', price: '$5.75', image: 'https://images.unsplash.com/photo-1536935338788-846bb9981813?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80', tag: 'Seasonal' },
    { id: 6, name: 'Oolong Milk Tea', price: '$4.75', image: 'https://images.unsplash.com/photo-1517244326705-73955d81540f?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80' },
  ];

  const recentOrders = [
    { id: '#ZT-8503', name: 'James W.', status: 'Preparing', time: '2 mins ago' },
    { id: '#ZT-8502', name: 'Sarah K.', status: 'Preparing', time: '5 mins ago' },
    { id: '#ZT-8501', name: 'Alex M.', status: 'Preparing', time: '8 mins ago' },
    { id: '#ZT-8500', name: 'Jessica L.', status: 'Ready for Pickup', time: '10 mins ago' },
    { id: '#ZT-8499', name: 'Michael B.', status: 'Ready for Pickup', time: '15 mins ago' },
    { id: '#ZT-8498', name: 'Emily R.', status: 'Ready for Pickup', time: '18 mins ago' },
  ];

  return (
    <div className="bg-[#FAFAFA] min-h-screen pb-20">
      {/* Hero Section */}
      <div className="relative bg-emerald-950 overflow-hidden">
        {/* Decorative elements */}
        <div className="absolute top-0 left-0 w-full h-full overflow-hidden z-0 pointer-events-none">
          <div className="absolute -top-[20%] -right-[10%] w-[50%] h-[50%] rounded-full bg-emerald-800/30 blur-[120px]"></div>
          <div className="absolute top-[60%] -left-[10%] w-[40%] h-[40%] rounded-full bg-emerald-900/40 blur-[100px]"></div>
        </div>

        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-20 lg:py-32 relative z-10">
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-16 items-center">
            <motion.div 
              initial={{ opacity: 0, y: 30 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.8 }}
            >
              <span className="inline-flex items-center space-x-2 bg-emerald-900/50 rounded-full px-4 py-2 text-emerald-300 text-sm font-medium tracking-wide mb-6 border border-emerald-800/50">
                <Sparkles className="w-4 h-4" />
                <span>Premium Artisan Tea</span>
              </span>
              <h1 className="text-5xl lg:text-7xl font-serif text-white leading-[1.1] mb-6">
                Find Your Zen in <br/>
                <span className="text-emerald-400 italic">Every Sip.</span>
              </h1>
              <p className="text-emerald-100/80 text-lg mb-10 max-w-lg leading-relaxed font-light">
                Crafted with ethically sourced ingredients to bring balance and harmony to your busy day. Experience the art of tea.
              </p>
              <div className="flex flex-col sm:flex-row gap-4">
                <Link to="/drinks" className="inline-flex items-center justify-center px-8 py-4 text-base font-medium rounded-full text-emerald-950 bg-emerald-400 hover:bg-emerald-300 transition-all duration-300 hover:shadow-[0_0_20px_rgba(52,211,153,0.4)] hover:-translate-y-1">
                  Order Now <ArrowRight className="ml-2 w-5 h-5" />
                </Link>
                <a href="#status" className="inline-flex items-center justify-center px-8 py-4 text-base font-medium rounded-full text-white border border-emerald-700 hover:bg-emerald-800/50 transition-colors">
                  Track Order
                </a>
              </div>
            </motion.div>

            <motion.div 
              initial={{ opacity: 0, scale: 0.9 }}
              animate={{ opacity: 1, scale: 1 }}
              transition={{ duration: 1, delay: 0.2 }}
              className="relative hidden lg:block"
            >
              <div className="relative w-full max-w-md mx-auto aspect-[4/5] rounded-t-full rounded-b-[2.5rem] overflow-hidden border-[12px] border-emerald-900/30 shadow-2xl">
                <img src="https://images.unsplash.com/photo-1515823662972-da6a2e4d3002?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" alt="Matcha Drink" className="absolute inset-0 w-full h-full object-cover" />
                <div className="absolute inset-0 bg-gradient-to-t from-emerald-950/60 to-transparent"></div>
              </div>
              
              {/* Floating badge */}
              <motion.div 
                initial={{ opacity: 0, x: 20 }}
                animate={{ opacity: 1, x: 0 }}
                transition={{ duration: 0.8, delay: 0.8 }}
                className="absolute bottom-12 -left-8 bg-white p-4 rounded-2xl shadow-xl flex items-center space-x-4 border border-emerald-50"
              >
                <div className="w-12 h-12 bg-emerald-100 rounded-full flex items-center justify-center">
                  <span className="text-emerald-600 font-bold text-xl">#1</span>
                </div>
                <div>
                  <p className="text-sm font-bold text-gray-900">Signature Matcha</p>
                  <p className="text-xs text-gray-500">Bestseller this week</p>
                </div>
              </motion.div>
            </motion.div>
          </div>
        </div>
      </div>

      {/* Live Order Status Section */}
      <div id="status" className="relative -mt-10 z-20 max-w-5xl mx-auto px-4 sm:px-6 lg:px-8 mb-24">
        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="bg-white rounded-[2rem] shadow-[0_20px_40px_rgba(0,0,0,0.08)] overflow-hidden border border-gray-100 flex flex-col md:flex-row"
        >
          {/* Status Header Info */}
          <div className="bg-gray-50 p-8 md:w-1/3 border-b md:border-b-0 md:border-r border-gray-100 flex flex-col justify-center">
            <h2 className="text-3xl font-serif font-bold text-gray-900 mb-2">Live Status</h2>
            <p className="text-gray-500 mb-8">Track your order progress in real-time.</p>
            
            <div className="space-y-4">
              <div className="flex items-center justify-between p-4 bg-white rounded-xl border border-gray-100 shadow-sm">
                <div className="flex items-center space-x-3">
                  <div className="w-2 h-2 bg-orange-400 rounded-full animate-pulse"></div>
                  <span className="font-medium text-gray-700">Preparing</span>
                </div>
                <span className="font-mono font-bold text-orange-600">3</span>
              </div>
              <div className="flex items-center justify-between p-4 bg-white rounded-xl border border-gray-100 shadow-sm">
                <div className="flex items-center space-x-3">
                  <div className="w-2 h-2 bg-emerald-500 rounded-full"></div>
                  <span className="font-medium text-gray-700">Ready</span>
                </div>
                <span className="font-mono font-bold text-emerald-600">3</span>
              </div>
            </div>
          </div>

          {/* Order List */}
          <div className="md:w-2/3 p-6 md:p-8 max-h-[400px] overflow-y-auto">
            <div className="space-y-3">
              {recentOrders.map((order) => (
                <div key={order.id} className="group flex items-center justify-between p-4 rounded-2xl hover:bg-gray-50 transition-colors border border-transparent hover:border-gray-100">
                  <div className="flex items-center space-x-4">
                    <div className={`p-3 rounded-xl ${order.status === 'Ready for Pickup' ? 'bg-emerald-50 text-emerald-600' : 'bg-orange-50 text-orange-600'}`}>
                      {order.status === 'Ready for Pickup' ? <CheckCircle2 className="w-5 h-5" /> : <Clock className="w-5 h-5" />}
                    </div>
                    <div>
                      <div className="flex items-center space-x-3">
                        <h3 className="font-bold text-gray-900">{order.id}</h3>
                        <span className="text-sm font-medium text-gray-500">{order.name}</span>
                      </div>
                      <p className="text-xs text-gray-400 mt-0.5">Ordered {order.time}</p>
                    </div>
                  </div>
                  <div className="flex items-center">
                    <span className={`px-3 py-1 rounded-full text-xs font-bold flex items-center ${
                      order.status === 'Ready for Pickup' 
                        ? 'bg-emerald-100 text-emerald-800' 
                        : 'bg-orange-100 text-orange-800'
                    }`}>
                      {order.status === 'Preparing' && <span className="w-1.5 h-1.5 bg-orange-500 rounded-full mr-1.5 animate-pulse"></span>}
                      {order.status}
                    </span>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </motion.div>
      </div>

      {/* Menu Section */}
      <div id="menu" className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-12">
          <motion.h2 
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            className="text-4xl font-serif font-bold text-gray-900 mb-4"
          >
            Curated Selections
          </motion.h2>
          <motion.p 
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            transition={{ delay: 0.1 }}
            className="text-gray-500 max-w-2xl mx-auto"
          >
            Discover our hand-crafted beverages, made to order with the finest ingredients.
          </motion.p>
        </div>

        <div className="flex justify-center mb-12 overflow-x-auto pb-4">
          <div className="flex space-x-2 bg-white p-1.5 rounded-full shadow-sm border border-gray-100">
            <button className="px-6 py-2.5 bg-emerald-800 text-white rounded-full text-sm font-medium whitespace-nowrap transition-all shadow-md">All</button>
            <button className="px-6 py-2.5 text-gray-600 rounded-full text-sm font-medium hover:bg-gray-50 whitespace-nowrap transition-all">Signature</button>
            <button className="px-6 py-2.5 text-gray-600 rounded-full text-sm font-medium hover:bg-gray-50 whitespace-nowrap transition-all">Milk Tea</button>
            <button className="px-6 py-2.5 text-gray-600 rounded-full text-sm font-medium hover:bg-gray-50 whitespace-nowrap transition-all">Fruit Tea</button>
            <button className="px-6 py-2.5 text-gray-600 rounded-full text-sm font-medium hover:bg-gray-50 whitespace-nowrap transition-all">Seasonal</button>
          </div>
        </div>

        {/* Product Grid */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
          {products.map((product, index) => (
            <motion.div 
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              viewport={{ once: true }}
              transition={{ delay: index * 0.1 }}
              key={product.id} 
              className="bg-white rounded-[2rem] shadow-sm hover:shadow-xl transition-all duration-300 overflow-hidden group border border-gray-100 flex flex-col"
            >
              <div className="relative h-72 overflow-hidden bg-gray-100">
                <Link to={`/product/${product.id}`} className="block w-full h-full">
                  <img src={product.image} alt={product.name} className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-700 ease-out" />
                </Link>
                {product.tag && (
                  <div className="absolute top-4 left-4 bg-white/90 backdrop-blur-md px-4 py-1.5 rounded-full text-xs font-bold text-emerald-800 shadow-sm pointer-events-none">
                    {product.tag}
                  </div>
                )}
                <button className="absolute bottom-4 right-4 bg-white text-emerald-900 p-3 rounded-full shadow-lg opacity-0 group-hover:opacity-100 transform translate-y-4 group-hover:translate-y-0 transition-all duration-300 hover:bg-emerald-50 hover:scale-110">
                  <Plus className="w-6 h-6" />
                </button>
              </div>
              <div className="p-8 flex-1 flex flex-col">
                <div className="flex justify-between items-start mb-3">
                  <Link to={`/product/${product.id}`}>
                    <h3 className="text-xl font-bold text-gray-900 hover:text-emerald-600 transition-colors font-serif leading-tight">{product.name}</h3>
                  </Link>
                  <span className="font-bold text-emerald-700 bg-emerald-50 px-3 py-1 rounded-lg">{product.price}</span>
                </div>
                <p className="text-gray-500 text-sm mb-6 line-clamp-2 flex-1">A perfect blend of premium ingredients carefully crafted for your enjoyment.</p>
                <div className="flex items-center space-x-2 pt-4 border-t border-gray-100">
                  <span className="text-xs font-medium text-gray-500 bg-gray-50 px-3 py-1.5 rounded-full border border-gray-100">Iced</span>
                  <span className="text-xs font-medium text-gray-500 bg-gray-50 px-3 py-1.5 rounded-full border border-gray-100">Hot</span>
                </div>
              </div>
            </motion.div>
          ))}
        </div>
        
        <div className="mt-16 text-center">
          <Link to="/drinks" className="inline-flex items-center justify-center px-8 py-4 text-base font-medium rounded-full text-emerald-800 bg-emerald-50 hover:bg-emerald-100 transition-colors border border-emerald-200">
            View Full Menu
          </Link>
        </div>
      </div>
    </div>
  );
}
