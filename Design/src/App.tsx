/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import Home from './pages/Home';
import Drinks from './pages/Drinks';
import ProductDetails from './pages/ProductDetails';
import Cart from './pages/Cart';
import CheckoutSuccess from './pages/CheckoutSuccess';
import OrderHistory from './pages/OrderHistory';
import OrderTracking from './pages/OrderTracking';
import Profile from './pages/Profile';
import SecuritySettings from './pages/SecuritySettings';
import SignIn from './pages/SignIn';
import SignUp from './pages/SignUp';
import VerifyOTP from './pages/VerifyOTP';
import ResetPassword from './pages/ResetPassword';
import Locations from './pages/Locations';

export default function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<SignIn />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/verify-otp" element={<VerifyOTP />} />
        <Route path="/reset-password" element={<ResetPassword />} />
        
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="drinks" element={<Drinks />} />
          <Route path="product/:id" element={<ProductDetails />} />
          <Route path="cart" element={<Cart />} />
          <Route path="locations" element={<Locations />} />
          <Route path="checkout/success" element={<CheckoutSuccess />} />
          <Route path="orders" element={<OrderHistory />} />
          <Route path="orders/:id" element={<OrderTracking />} />
          <Route path="profile" element={<Profile />} />
          <Route path="settings/security" element={<SecuritySettings />} />
        </Route>
      </Routes>
    </Router>
  );
}
