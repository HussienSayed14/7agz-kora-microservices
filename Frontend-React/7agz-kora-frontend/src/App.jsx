 import React from 'react';
 import Login from './Login/Login.jsx'
 import ForgotPasswordRequest from './ForgotPassword/ForgotPasswordRequest.jsx';
 import RegisterRequest from './Register/RegisterRequest.jsx'
 import OtpVerify from './Register/OtpVerify.jsx';
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />}/>
        <Route path="/forgotPasswordRequest" element={<ForgotPasswordRequest />} />
        <Route path="/registerRequest" element={<RegisterRequest/>} />
        <Route path="/optVerify" element={<OtpVerify />} />
    
      </Routes>
    </BrowserRouter>
  )
}

export default App