import { useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Star, ChevronRight, Info, Plus, Minus } from 'lucide-react';

export default function ProductDetails() {
  const { id } = useParams();
  const [quantity, setQuantity] = useState(1);
  const [size, setSize] = useState('Medium');
  const [temp, setTemp] = useState('Iced');
  const [sweetness, setSweetness] = useState('100%');
  const [ice, setIce] = useState('Regular');
  
  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      {/* Breadcrumbs */}
      <nav className="flex text-sm text-gray-500 mb-8" aria-label="Breadcrumb">
        <ol className="inline-flex items-center space-x-1 md:space-x-3">
          <li className="inline-flex items-center">
            <Link to="/" className="hover:text-emerald-600 transition-colors">Menu</Link>
          </li>
          <li>
            <div className="flex items-center">
              <ChevronRight className="w-4 h-4 mx-1" />
              <Link to="/" className="hover:text-emerald-600 transition-colors">Signature</Link>
            </div>
          </li>
          <li aria-current="page">
            <div className="flex items-center">
              <ChevronRight className="w-4 h-4 mx-1" />
              <span className="text-gray-900 font-medium">Signature Matcha Crème</span>
            </div>
          </li>
        </ol>
      </nav>

      <div className="flex flex-col lg:flex-row gap-12">
        {/* Product Image */}
        <div className="lg:w-1/2">
          <div className="bg-emerald-50 rounded-3xl p-8 flex justify-center items-center relative overflow-hidden group">
            <div className="absolute top-4 left-4 bg-white/90 backdrop-blur-sm px-3 py-1 rounded-full text-xs font-bold text-emerald-800 shadow-sm z-10">
              Bestseller
            </div>
            <img 
              src="https://images.unsplash.com/photo-1515823662972-da6a2e4d3002?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" 
              alt="Signature Matcha Crème" 
              className="w-full max-w-md h-auto object-contain rounded-2xl shadow-2xl transform group-hover:scale-105 transition-transform duration-500"
            />
          </div>
        </div>

        {/* Product Details */}
        <div className="lg:w-1/2">
          <div className="mb-6">
            <h1 className="text-3xl md:text-4xl font-serif font-bold text-gray-900 mb-2">Signature Matcha Crème</h1>
            <div className="flex items-center space-x-4 mb-4">
              <span className="text-2xl font-bold text-emerald-700">$5.50</span>
            </div>
            <p className="text-gray-600 leading-relaxed">
              Ceremonial grade matcha imported directly from Uji, Japan, layered with our signature house-made sweet crème and organic whole milk. A perfect balance of earthy notes and creamy sweetness.
            </p>
          </div>

          <div className="space-y-6 mb-8">
            {/* Size Options */}
            <div>
              <h3 className="text-sm font-medium text-gray-900 mb-3">Size</h3>
              <div className="flex space-x-4">
                {['Medium', 'Large (+$0.75)'].map((s) => (
                  <button 
                    key={s}
                    onClick={() => setSize(s)}
                    className={`flex-1 py-3 border rounded-xl text-center transition-colors ${size === s ? 'border-emerald-500 bg-emerald-50 text-emerald-800 font-medium' : 'border-gray-200 text-gray-600 hover:border-emerald-300'}`}
                  >
                    {s}
                  </button>
                ))}
              </div>
            </div>

            {/* Temperature */}
            <div>
              <h3 className="text-sm font-medium text-gray-900 mb-3">Temperature</h3>
              <div className="flex space-x-4">
                {['Iced', 'Hot'].map((t) => (
                  <button 
                    key={t}
                    onClick={() => setTemp(t)}
                    className={`flex-1 py-3 border rounded-xl text-center transition-colors ${temp === t ? 'border-emerald-500 bg-emerald-50 text-emerald-800 font-medium' : 'border-gray-200 text-gray-600 hover:border-emerald-300'}`}
                  >
                    {t}
                  </button>
                ))}
              </div>
            </div>

            {/* Sweetness */}
            <div>
              <h3 className="text-sm font-medium text-gray-900 mb-3">Sweetness Level</h3>
              <div className="grid grid-cols-4 gap-2">
                {['0%', '25%', '50%', '75%', '100%', '120%'].map((s) => (
                  <button 
                    key={s}
                    onClick={() => setSweetness(s)}
                    className={`py-2 border rounded-lg text-sm text-center transition-colors ${sweetness === s ? 'border-emerald-500 bg-emerald-50 text-emerald-800 font-medium' : 'border-gray-200 text-gray-600 hover:border-emerald-300'}`}
                  >
                    {s}
                  </button>
                ))}
              </div>
            </div>

            {/* Ice Level */}
            <div>
              <h3 className="text-sm font-medium text-gray-900 mb-3">Ice Level</h3>
              <div className="grid grid-cols-4 gap-2">
                {['No Ice', 'Less Ice', 'Regular', 'Extra Ice'].map((i) => (
                  <button 
                    key={i}
                    onClick={() => setIce(i)}
                    className={`py-2 border rounded-lg text-sm text-center transition-colors ${ice === i ? 'border-emerald-500 bg-emerald-50 text-emerald-800 font-medium' : 'border-gray-200 text-gray-600 hover:border-emerald-300'}`}
                  >
                    {i}
                  </button>
                ))}
              </div>
            </div>
          </div>

          {/* Action Area */}
          <div className="border-t border-gray-200 pt-6">
            <div className="flex items-center space-x-4 mb-6">
              <div className="flex items-center border border-gray-300 rounded-full">
                <button 
                  onClick={() => setQuantity(Math.max(1, quantity - 1))}
                  className="p-3 text-gray-600 hover:text-emerald-600 transition-colors"
                >
                  <Minus className="w-5 h-5" />
                </button>
                <span className="w-12 text-center font-medium text-gray-900">{quantity}</span>
                <button 
                  onClick={() => setQuantity(quantity + 1)}
                  className="p-3 text-gray-600 hover:text-emerald-600 transition-colors"
                >
                  <Plus className="w-5 h-5" />
                </button>
              </div>
              <button className="flex-1 bg-emerald-800 text-white py-4 rounded-full font-bold text-lg hover:bg-emerald-900 transition-colors shadow-lg flex justify-center items-center">
                Add to Cart - ${(5.50 * quantity).toFixed(2)}
              </button>
            </div>

            {/* Nutritional Info */}
            <div className="bg-gray-50 rounded-xl p-4 flex items-start space-x-3">
              <Info className="w-5 h-5 text-gray-400 flex-shrink-0 mt-0.5" />
              <div className="text-sm text-gray-600">
                <p className="font-medium text-gray-900 mb-1">Nutritional Information (Medium, Regular Sweetness)</p>
                <p>Calories: 280 | Sugar: 32g | Caffeine: 65mg</p>
                <p className="text-xs mt-2 text-gray-500">2,000 calories a day is used for general nutrition advice, but calorie needs vary.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
