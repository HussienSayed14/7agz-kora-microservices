import React from "react";
import Login from "./Login/Login.jsx";
import Register from "./Register/Register.jsx";
import VerifyEmail from "./VerifyEmail/VerifyEmail.jsx";
import HomePage from "./HomePage/HomePage.jsx";
import ForgotPasswordRequest from "./ForgotPassword/ForgotPasswordRequest.jsx";
import ForgotPassword from "./ForgotPassword/ForgotPassword.jsx";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/verifyEmail" element={<VerifyEmail />} />
        <Route path="/homePage" element={<HomePage />} />
        <Route
          path="/forgotPasswordRequest"
          element={<ForgotPasswordRequest />}
        />
        <Route path="/forgotPassword" element={<ForgotPassword />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
