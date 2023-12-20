import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import {
    MDBInput,
    MDBBtn,
    MDBSpinner
  } from 'mdb-react-ui-kit';
  import { useState } from 'react';
  import 'mdb-react-ui-kit/dist/css/mdb.min.css';

import './Register.css'


function RegisterRequest() {
    const [email, setEmail] = useState('');
    const [loading, setLoading] = useState(false);

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

        setLoading(true);
         axios.post(`${baseUrl}/registerRequest`,registerRequestObj)
            .then((res) => {
                console.log(res.data);
                if(res.data.responseCode === "0"){
                  sessionStorage.setItem("email",email)
                  window.location='/optVerify'

                }
            }).catch((err) => {
                console.log(err.response.data)
                setLoading(false)

                
            });
        
    }
    };

    if(loading){
      return(
        <div className='loading-icon-div'>
      <MDBSpinner className='loading-icon' color='primary'>
      <span className='visually-hidden'>Loading...</span>
    </MDBSpinner>
    </div>
    )
    }

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