import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {
    MDBInput,
    MDBBtn,
    MDBTypography
  } from 'mdb-react-ui-kit';
  import { useState } from 'react';
  import 'mdb-react-ui-kit/dist/css/mdb.min.css';
  import axios from "axios";

import './Register.css'


function OtpVerify() {
    
  const [email,setEmail] = useState(sessionStorage.getItem("email"));
    const [otp, setOtp] = useState('');


    const handleOtpChange = e =>{
        setOtp(e.target.value);
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
        const baseUrl = "http://localhost:8000/accounts/api/v1"

        axios.post(`${baseUrl}/verifyOtp`,otpVerifyObj)
            .then((res) => {
                console.log(res.data);
                if(res.data.responseCode === "0"){
                  sessionStorage.setItem("email",email)
                  //window.location='/optVerify'
                }
            }).catch((err) => {
              console.log(err.response.data)
                
            });
         
    }
    };



  
  return (
    <div className="login-container">
    <form className="login-form">
    <h4 className='mb-3'>Otp Verification</h4>
    <MDBTypography className='lead mb-0'>
     We have sent otp to your mail.
    </MDBTypography>

      <MDBInput onChange={handleOtpChange} className='mb-4 email-field' type='email' id='form1Example1' label='Otp' />

     <MDBBtn onClick={handleSubmit} type='submit' block>
        Submit
      </MDBBtn>
    </form>
  </div>
  );

  
}

export default OtpVerify;