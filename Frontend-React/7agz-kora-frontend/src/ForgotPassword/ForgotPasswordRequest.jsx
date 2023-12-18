import React from 'react'
import './ForgotPassword.css'
import {
    MDBInput,
    MDBCol,
    MDBRow,
    MDBBtn
  } from 'mdb-react-ui-kit';
  import { useState } from 'react';
  import 'mdb-react-ui-kit/dist/css/mdb.min.css';

function ForgotPasswordRequest() {

    const [email, setEmail] = useState('');


    const handleEmailChange = e =>{
        setEmail(e.target.value);
    };

    const handleRequest = e =>{
        e.preventDefault();
        const req = {
            email: email
        }
        console.log(req);
    }
  return (
    <div className="login-container">
    <form className="login-form">
      <h2>Enter your email</h2>
      <MDBInput onChange={handleEmailChange} className='mb-4 email-field' type='email' id='form1Example1' label='Email address' />

      <MDBRow className='mb-4'>
       
        <MDBCol>
          <a href='/Login'>Return to Login</a>
        </MDBCol>
      </MDBRow>

      <MDBBtn onClick={handleRequest} type='submit' block>
        Submit
      </MDBBtn>
    </form>
  </div>
  )
}

export default ForgotPasswordRequest