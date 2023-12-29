import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {
    MDBInput,
    MDBCol,
    MDBRow,
    MDBCheckbox,
    MDBBtn
  } from 'mdb-react-ui-kit';
  import { useState } from 'react';
  import 'mdb-react-ui-kit/dist/css/mdb.min.css';

import './Login.css'


function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');


    const handleEmailChange = e =>{
        setEmail(e.target.value);
    };
    const handlePasswordChange = e =>{
        setPassword(e.target.value);
 };

 
    const handleLogin = e =>{
        e.preventDefault();
        if( email === "" || password === ""){
            console.log("Error");
            window.alert("Please enter Email and Password!")
        }else{

        var loginObject = {
            email: email,
            password: password
        }
         console.log(loginObject);
    }
    };



  
  return (
    <div className="login-container">
    <form className="login-form">
      <MDBInput onChange={handleEmailChange} className='mb-4 email-field' type='email' id='form1Example1' label='Email address' />
      <MDBInput onChange={handlePasswordChange} className='mb-4' type='password' id='form1Example2' label='Password' />

      <MDBRow className='mb-4'>
        <MDBCol className='d-flex justify-content-center'>
          <MDBCheckbox id='form1Example3' label='Remember me' defaultChecked />
        </MDBCol>
        <MDBCol>
          <a href='/forgotPasswordRequest'>Forgot password?</a>
        </MDBCol>
      </MDBRow>

      <MDBBtn onClick={handleLogin} type='submit' block>
        Sign in
      </MDBBtn>
    </form>
  </div>
  );

  
}

export default Login;