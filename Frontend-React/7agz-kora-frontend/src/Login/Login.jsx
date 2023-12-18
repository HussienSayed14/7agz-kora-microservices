import React from 'react';
import {useState} from 'react';
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
        if( email == "" || password == ""){
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
    <div class="login-form">
    <form>
      <h1>Login</h1>
      <div class="content">
        <div class="input-field">
          <input onChange={handleEmailChange} type="email" placeholder="Email" autocomplete="nope"/>
        </div>
        <div class="input-field">
          <input onChange={handlePasswordChange} type="password" placeholder="Password" autocomplete="new-password"/>
        </div>
        <a href="#" class="link">Forgot Your Password?</a>
      </div>
      <div class="action">
        <button>Register</button>
        <button onClick={handleLogin}>Sign in</button>
      </div>
    </form>
  </div>
  );

  
}

export default Login;