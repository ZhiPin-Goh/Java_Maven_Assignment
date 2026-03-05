import { Link } from 'react-router-dom';

export default function Drinks() {
  const products = [
    { id: 1, name: 'Signature Matcha Crème', price: '$5.50', image: 'https://images.unsplash.com/photo-1515823662972-da6a2e4d3002?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80', tag: 'Bestseller' },
    { id: 2, name: 'Classic Brown Sugar Boba', price: '$5.00', image: 'https://images.unsplash.com/photo-1558855567-1a34221b20ea?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80', tag: 'Popular' },
    { id: 3, name: 'Jasmine Green Tea', price: '$4.25', image: 'https://images.unsplash.com/photo-1556679343-c7306c1976bc?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80' },
    { id: 4, name: 'Taro Milk Tea', price: '$5.25', image: 'https://images.unsplash.com/photo-1625860633266-112658591961?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80' },
    { id: 5, name: 'Mango Passion Fruit', price: '$5.75', image: 'https://images.unsplash.com/photo-1536935338788-846bb9981813?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80', tag: 'Seasonal' },
    { id: 6, name: 'Oolong Milk Tea', price: '$4.75', image: 'https://images.unsplash.com/photo-1517244326705-73955d81540f?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80' },
    { id: 7, name: 'Strawberry Matcha Latte', price: '$6.00', image: 'https://images.unsplash.com/photo-1556679343-c7306c1976bc?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80', tag: 'New' },
    { id: 8, name: 'Thai Milk Tea', price: '$4.75', image: 'https://images.unsplash.com/photo-1558855567-1a34221b20ea?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80' },
    { id: 9, name: 'Wintermelon Tea', price: '$4.50', image: 'https://images.unsplash.com/photo-1515823662972-da6a2e4d3002?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80' },
  ];

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
      <div className="text-center mb-12">
        <h1 className="text-4xl font-serif font-bold text-gray-900 mb-4">All Drinks</h1>
        <p className="text-lg text-gray-600 max-w-2xl mx-auto">
          Explore our full menu of handcrafted beverages, made to order with premium ingredients.
        </p>
      </div>

      <div className="flex flex-col md:flex-row justify-between items-end mb-8">
        <div className="flex space-x-2 overflow-x-auto pb-2 w-full md:w-auto">
          <button className="px-4 py-2 bg-emerald-800 text-white rounded-full text-sm font-medium whitespace-nowrap">All</button>
          <button className="px-4 py-2 bg-white text-gray-600 border border-gray-300 rounded-full text-sm font-medium hover:border-emerald-500 hover:text-emerald-600 whitespace-nowrap transition-colors">Signature</button>
          <button className="px-4 py-2 bg-white text-gray-600 border border-gray-300 rounded-full text-sm font-medium hover:border-emerald-500 hover:text-emerald-600 whitespace-nowrap transition-colors">Milk Tea</button>
          <button className="px-4 py-2 bg-white text-gray-600 border border-gray-300 rounded-full text-sm font-medium hover:border-emerald-500 hover:text-emerald-600 whitespace-nowrap transition-colors">Fruit Tea</button>
          <button className="px-4 py-2 bg-white text-gray-600 border border-gray-300 rounded-full text-sm font-medium hover:border-emerald-500 hover:text-emerald-600 whitespace-nowrap transition-colors">Seasonal</button>
        </div>
      </div>

      {/* Product Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
        {products.map((product) => (
          <div key={product.id} className="bg-white rounded-2xl shadow-sm hover:shadow-md transition-shadow overflow-hidden group border border-gray-100">
            <div className="relative h-64 overflow-hidden bg-gray-100">
              <Link to={`/product/${product.id}`} className="block w-full h-full">
                <img src={product.image} alt={product.name} className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500" />
              </Link>
              {product.tag && (
                <div className="absolute top-4 left-4 bg-white/90 backdrop-blur-sm px-3 py-1 rounded-full text-xs font-bold text-emerald-800 shadow-sm pointer-events-none">
                  {product.tag}
                </div>
              )}
              <Link to={`/product/${product.id}`} className="absolute bottom-4 right-4 bg-emerald-600 text-white p-3 rounded-full shadow-lg opacity-0 group-hover:opacity-100 transform translate-y-4 group-hover:translate-y-0 transition-all duration-300 hover:bg-emerald-700">
                <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6"></path></svg>
              </Link>
            </div>
            <div className="p-6">
              <div className="flex justify-between items-start mb-2">
                <Link to={`/product/${product.id}`}>
                  <h3 className="text-lg font-bold text-gray-900 hover:text-emerald-600 transition-colors">{product.name}</h3>
                </Link>
                <span className="font-medium text-emerald-700">{product.price}</span>
              </div>
              <p className="text-gray-500 text-sm mb-4 line-clamp-2">A perfect blend of premium ingredients carefully crafted for your enjoyment.</p>
              <div className="flex items-center space-x-2">
                <span className="text-xs text-gray-400 bg-gray-100 px-2 py-1 rounded">Iced</span>
                <span className="text-xs text-gray-400 bg-gray-100 px-2 py-1 rounded">Hot</span>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
