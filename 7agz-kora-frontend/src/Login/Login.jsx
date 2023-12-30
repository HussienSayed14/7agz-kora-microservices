import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  MDBInput,
  MDBCol,
  MDBRow,
  MDBBtn,
  MDBSpinner,
  MDBModal,
  MDBModalDialog,
  MDBModalContent,
  MDBModalHeader,
  MDBModalTitle,
  MDBModalBody,
} from "mdb-react-ui-kit";
import { useState } from "react";
import "mdb-react-ui-kit/dist/css/mdb.min.css";
import "./Login.css";

function Login() {
  const baseUrl = process.env.REACT_APP_API_URL;
  const [loginRequest, setLoginRequest] = useState({
    email: "",
    password: "",
  });
  const [loading, setLoading] = useState(false);
  const [renderCounter, setRenderCounter] = useState(0);
  const [errorMessage, setErrorMessage] = useState("");
  const [optSmModal, setOptSmModal] = useState(false);

  const toggleOpen = () => setOptSmModal(!optSmModal);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setLoginRequest((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setRenderCounter(renderCounter + 1);
  };

  function handleSuccessRegister(response) {
    if (response.responseCode === "0") {
      // Set Cookies
      window.location = "/homePage";
    }
  }

  function handleFailedRegister(response) {
    setErrorMessage(response.responseMessage);
    toggleOpen();
  }

  useEffect(() => {
    if (registerRequest.email === "") {
      return;
    }
    const endPoint = "accounts/api/v1/auth";
    setLoading(true);
    axios
      .post(`${baseUrl}${endPoint}/register`, registerRequest)
      .then((res) => {
        handleSuccessLogin(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setLoading(false);
        handleFailedLogin(err.response.data);
      });
  }, [renderCounter]);

  return (
    <div className="login-container">
      <form className="login-form">
        <MDBInput
          onChange={handleInputChange}
          className="mb-4 email-field"
          type="email"
          id="form1Example1"
          label="Email address"
          name="email"
        />
        <MDBInput
          onChange={handleInputChange}
          className="mb-4"
          type="password"
          id="form1Example2"
          label="Password"
          name="password"
        />

        <MDBRow className="mb-4">
          <MDBCol className="d-flex">
            <a href="/forgotPasswordRequest">Forgot password?</a>
          </MDBCol>
          <MDBCol className="d-flex justify-content-center">
            <a href="/register">Register</a>
          </MDBCol>
        </MDBRow>
        <MDBBtn onClick={handleSubmit} type="submit" block>
          Sign in
        </MDBBtn>
      </form>
    </div>
  );
}

export default Login;
