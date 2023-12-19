import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {
    MDBInput,
    MDBBtn,
    MDBTypography
  } from 'mdb-react-ui-kit';
  import { useState } from 'react';
  import 'mdb-react-ui-kit/dist/css/mdb.min.css';

import './Register.css'


function OtpVerify() {
    const [email, setEmail] = useState('');
    const [otp, setOtp] = useState('');


    const handleOtpChange = e =>{
        setEmail(e.target.value);
    };
 

 
    const handleSubmit = e =>{
        e.preventDefault();
        if( email === "" || otp === "" ){
            console.log("Error");
            window.alert("Please enter otp")
        }else{

        var otpVerifyObj = {
            email: email,
            otp: otp
        }
         console.log(otpVerifyObj);
         //window.location='/register'
    }
    };



  
  return (
    <div className="login-container">
    <form className="login-form">
    <h4 className='mb-3'>Otp Verification</h4>
    <MDBTypography className='lead mb-0'>
     We have sent otp to your mail.
    </MDBTypography>

      <MDBInput onChange={handleOtpChange} className='mb-4 email-field' type='email' id='form1Example1' label='Email address' />

     <MDBBtn onClick={handleSubmit} type='submit' block>
        Submit
      </MDBBtn>
    </form>
  </div>
  );

  
}

export default OtpVerify;