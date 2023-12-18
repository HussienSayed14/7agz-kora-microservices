 import React from 'react';
 import Login from './Login/Login.jsx'
 import ForgotPasswordRequest from './ForgotPassword/ForgotPasswordRequest.jsx';
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />}/>
        <Route path="/forgotPasswordRequest" element={<ForgotPasswordRequest />} />
        {/* <Route path="contact" element={<Contact />} /> */}
        {/* <Route path="*" element={<NoPage />} /> */}
    
      </Routes>
    </BrowserRouter>
  )
}

export default App