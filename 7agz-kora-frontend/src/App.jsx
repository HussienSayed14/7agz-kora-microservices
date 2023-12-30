import React from "react";
import Login from "./Login/Login.jsx";
import Register from "./Register/Register.jsx";
import VerifyEmail from "./VerifyEmail/VerifyEmail.jsx";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/verifyEmail" element={<VerifyEmail />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
