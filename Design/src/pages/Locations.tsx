import { MapPin, Clock, Phone, Navigation } from 'lucide-react';
import { motion } from 'motion/react';

export default function Locations() {
  const location = {
    name: 'Zen Tea - Flagship Store',
    address: '123 Main Street, San Francisco, CA 94105',
    hours: 'Mon-Sun: 8:00 AM - 9:00 PM',
    phone: '(415) 555-0123',
    image: 'https://images.unsplash.com/photo-1554118811-1e0d58224f24?ixlib=rb-4.0.3&auto=format&fit=crop&w=1200&q=80',
    description: 'Visit our flagship store in the heart of downtown. Experience our full menu of artisan teas, seasonal specials, and a peaceful atmosphere designed for your comfort.'
  };

  return (
    <div className="bg-[#FAFAFA] min-h-screen pb-20">
      {/* Hero Header */}
      <div className="bg-emerald-950 text-white py-16 md:py-24 relative overflow-hidden">
        <div className="absolute inset-0 opacity-20">
          <img src={location.image} alt="Store Interior" className="w-full h-full object-cover" />
          <div className="absolute inset-0 bg-emerald-950/80 mix-blend-multiply"></div>
        </div>
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 relative z-10 text-center">
          <motion.h1 
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            className="text-4xl md:text-5xl font-serif font-bold mb-4"
          >
            Our Location
          </motion.h1>
          <motion.p 
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.1 }}
            className="text-lg text-emerald-100/80 max-w-2xl mx-auto"
          >
            Find your zen at our flagship store. We can't wait to serve you.
          </motion.p>
        </div>
      </div>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 -mt-12 relative z-20">
        <motion.div 
          initial={{ opacity: 0, y: 30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.2 }}
          className="bg-white rounded-[2rem] shadow-xl border border-gray-100 overflow-hidden flex flex-col lg:flex-row"
        >
          {/* Map Section */}
          <div className="lg:w-3/5 h-[400px] lg:h-auto relative bg-gray-200">
            <iframe 
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3153.097746481134!2d-122.39578138468205!3d37.78779977975772!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x80858064e4213199%3A0x1b4c3821a8848d70!2s123%20Main%20St%2C%20San%20Francisco%2C%20CA%2094105!5e0!3m2!1sen!2sus!4v1680000000000!5m2!1sen!2sus" 
              className="absolute inset-0 w-full h-full border-0"
              allowFullScreen={false} 
              loading="lazy" 
              referrerPolicy="no-referrer-when-downgrade"
              title="Zen Tea Location Map"
            ></iframe>
          </div>

          {/* Details Section */}
          <div className="lg:w-2/5 p-8 md:p-12 flex flex-col justify-center">
            <div className="inline-block bg-emerald-50 text-emerald-700 px-4 py-1.5 rounded-full text-sm font-bold tracking-wide uppercase mb-6 w-max">
              Flagship Store
            </div>
            
            <h2 className="text-3xl font-serif font-bold text-gray-900 mb-4">{location.name}</h2>
            <p className="text-gray-600 mb-8 leading-relaxed">
              {location.description}
            </p>
            
            <div className="space-y-6 mb-10">
              <div className="flex items-start">
                <div className="w-10 h-10 rounded-full bg-emerald-50 flex items-center justify-center flex-shrink-0 mr-4">
                  <MapPin className="w-5 h-5 text-emerald-600" />
                </div>
                <div>
                  <h3 className="text-sm font-bold text-gray-900 mb-1">Address</h3>
                  <span className="text-gray-600">{location.address}</span>
                </div>
              </div>
              
              <div className="flex items-start">
                <div className="w-10 h-10 rounded-full bg-emerald-50 flex items-center justify-center flex-shrink-0 mr-4">
                  <Clock className="w-5 h-5 text-emerald-600" />
                </div>
                <div>
                  <h3 className="text-sm font-bold text-gray-900 mb-1">Opening Hours</h3>
                  <span className="text-gray-600">{location.hours}</span>
                </div>
              </div>
              
              <div className="flex items-start">
                <div className="w-10 h-10 rounded-full bg-emerald-50 flex items-center justify-center flex-shrink-0 mr-4">
                  <Phone className="w-5 h-5 text-emerald-600" />
                </div>
                <div>
                  <h3 className="text-sm font-bold text-gray-900 mb-1">Contact</h3>
                  <span className="text-gray-600">{location.phone}</span>
                </div>
              </div>
            </div>

            <a 
              href={`https://maps.google.com/?q=${encodeURIComponent(location.address)}`}
              target="_blank"
              rel="noopener noreferrer"
              className="w-full flex items-center justify-center bg-emerald-800 text-white py-4 rounded-xl font-medium hover:bg-emerald-900 transition-colors shadow-md group"
            >
              <Navigation className="w-5 h-5 mr-2 group-hover:-translate-y-1 group-hover:translate-x-1 transition-transform" />
              Get Directions
            </a>
          </div>
        </motion.div>
      </div>
    </div>
  );
}
