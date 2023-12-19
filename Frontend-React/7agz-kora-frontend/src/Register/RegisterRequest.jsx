import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import {
    MDBInput,
    MDBBtn
  } from 'mdb-react-ui-kit';
  import { useState } from 'react';
  import 'mdb-react-ui-kit/dist/css/mdb.min.css';

import './Register.css'


function RegisterRequest() {
    const [email, setEmail] = useState('');

    const baseUrl = "http://localhost:8000/accounts/api/v1"



    const handleEmailChange = e =>{
        setEmail(e.target.value);
    };
 

 
    const handleSubmit = e =>{
        e.preventDefault();
        if( email === "" ){
            console.log("Error");
            window.alert("Please enter Email!")
        }else{

        var registerRequestObj = {
            email: email
        }
         console.log(registerRequestObj);
         axios
            .post(
                `${baseUrl}/registerRequest`,
                { registerRequestObj }
            )
            .then((res) => {
                console.log(res);
                console.log(res.data);
            }).catch((err) => {console.log(err)});
         //window.location='/optVerify'
    }
    };



  
  return (
    <div className="login-container">
    <form className="login-form">
    <h4 className='mb-3'>Forgot Password</h4>

      <MDBInput onChange={handleEmailChange} className='mb-4 email-field' type='email' id='form1Example1' label='Email address' />

     <MDBBtn onClick={handleSubmit} type='submit' block>
        Submit
      </MDBBtn>
    </form>
  </div>
  );

  
}

export default RegisterRequest;