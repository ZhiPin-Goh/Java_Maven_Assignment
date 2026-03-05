import { Link } from 'react-router-dom';
import { ShoppingBag, RefreshCw, ChevronRight } from 'lucide-react';
import AccountLayout from '../components/AccountLayout';

export default function OrderHistory() {
  const orders = [
    {
      id: 'ZT-8492',
      date: 'Oct 24, 2023',
      status: 'Completed',
      total: '$19.26',
      items: [
        { name: 'Signature Matcha Crème', qty: 1 },
        { name: 'Mango Passion Fruit', qty: 2 }
      ]
    },
    {
      id: 'ZT-7381',
      date: 'Sep 15, 2023',
      status: 'Ready for Pickup',
      total: '$12.50',
      items: [
        { name: 'Classic Brown Sugar Boba', qty: 2 }
      ]
    },
    {
      id: 'ZT-6210',
      date: 'Aug 02, 2023',
      status: 'Preparing',
      total: '$5.50',
      items: [
        { name: 'Taro Milk Tea', qty: 1 }
      ]
    }
  ];

  return (
    <AccountLayout>
      <div className="bg-white rounded-2xl shadow-sm border border-gray-100 p-8">
        <h1 className="text-2xl font-serif font-bold text-gray-900 mb-8">Order History</h1>
        
        {orders.length > 0 ? (
          <div className="space-y-6">
            {orders.map((order) => (
              <div key={order.id} className="border border-gray-200 rounded-xl p-6 hover:shadow-md transition-shadow">
                <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-4 border-b border-gray-100 pb-4">
                  <div>
                    <div className="flex items-center space-x-3 mb-1">
                      <span className="font-bold text-gray-900">{order.id}</span>
                      <span className={`px-2.5 py-0.5 rounded-full text-xs font-medium ${
                        order.status === 'Completed' ? 'bg-emerald-100 text-emerald-800' :
                        order.status === 'Ready for Pickup' ? 'bg-blue-100 text-blue-800' :
                        'bg-orange-100 text-orange-800'
                      }`}>
                        {order.status}
                      </span>
                    </div>
                    <p className="text-sm text-gray-500">{order.date}</p>
                  </div>
                  <div className="mt-4 sm:mt-0 text-right">
                    <span className="font-bold text-gray-900 block">{order.total}</span>
                    <Link to={`/orders/${order.id}`} className="text-sm text-emerald-600 hover:text-emerald-700 font-medium flex items-center mt-1">
                      View Details <ChevronRight className="w-4 h-4 ml-1" />
                    </Link>
                  </div>
                </div>
                
                <div className="flex justify-between items-center">
                  <div className="text-sm text-gray-600">
                    {order.items.map((item, idx) => (
                      <span key={idx}>
                        {item.qty}x {item.name}
                        {idx < order.items.length - 1 ? ', ' : ''}
                      </span>
                    ))}
                  </div>
                  <button className="flex items-center px-4 py-2 border border-emerald-600 text-emerald-700 rounded-lg text-sm font-medium hover:bg-emerald-50 transition-colors">
                    <RefreshCw className="w-4 h-4 mr-2" />
                    Reorder
                  </button>
                </div>
              </div>
            ))}
          </div>
        ) : (
          <div className="text-center py-16">
            <div className="w-24 h-24 bg-gray-50 rounded-full flex items-center justify-center mx-auto mb-6">
              <ShoppingBag className="w-10 h-10 text-gray-400" />
            </div>
            <h2 className="text-xl font-bold text-gray-900 mb-2">No Active Orders</h2>
            <p className="text-gray-500 mb-8 max-w-md mx-auto">You haven't placed any orders yet. Start exploring our menu to find your perfect cup of tea.</p>
            <Link to="/" className="inline-flex items-center justify-center px-8 py-3 border border-transparent text-base font-medium rounded-xl text-white bg-emerald-800 hover:bg-emerald-900 transition-colors shadow-md">
              Explore Menu
            </Link>
          </div>
        )}
      </div>
    </AccountLayout>
  );
}
