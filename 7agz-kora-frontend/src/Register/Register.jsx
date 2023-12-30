import React, { useEffect } from "react";
import { useState } from "react";
import {
  MDBSpinner,
  MDBBtn,
  MDBModal,
  MDBModalDialog,
  MDBModalContent,
  MDBModalHeader,
  MDBModalTitle,
  MDBModalBody,
} from "mdb-react-ui-kit";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Register.css";

function Register() {
  const baseUrl = process.env.REACT_APP_API_URL;
  const [loading, setLoading] = useState(false);
  const [renderCounter, setRenderCounter] = useState(0);
  const [errorMessage, setErrorMessage] = useState("");
  const [optSmModal, setOptSmModal] = useState(false);
  const [registerRequest, setRegisterRequest] = useState({
    email: "",
    firstName: "",
    lastName: "",
    password: "",
    phoneNumber: "",
    dateOfBirth: "",
    securityQuestion: "",
    securityAnswer: "",
    nationalId: "",
  });

  const toggleOpen = () => setOptSmModal(!optSmModal);
  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setRegisterRequest((prevState) => ({
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
      navigate("/verifyEmail", { state: registerRequest.email });
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
        handleSuccessRegister(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setLoading(false);
        handleFailedRegister(err.response.data);
      });
  }, [renderCounter]);

  return (
    <div className="main-div">
      <MDBModal open={optSmModal} tabIndex="-1" setOpen={setOptSmModal}>
        <MDBModalDialog size="sm">
          <MDBModalContent>
            <MDBModalHeader>
              <MDBModalTitle>Error!</MDBModalTitle>
              <MDBBtn
                className="btn-close"
                color="none"
                onClick={toggleOpen}
              ></MDBBtn>
            </MDBModalHeader>
            <MDBModalBody>{errorMessage}</MDBModalBody>
          </MDBModalContent>
        </MDBModalDialog>
      </MDBModal>
      {loading ? (
        <div className="loading-icon-div">
          <MDBSpinner className="loading-icon" color="primary">
            <span className="visually-hidden">Loading...</span>
          </MDBSpinner>
        </div>
      ) : (
        <div class="container">
          <div class="title">7agz Kora Registeration</div>
          <div class="content">
            <form>
              <div class="user-details">
                <div class="input-box">
                  <span class="details">First Name</span>
                  <input
                    onChange={handleInputChange}
                    type="text"
                    name="firstName"
                    placeholder="Enter your first name"
                    required
                  />
                </div>
                <div class="input-box">
                  <span class="details">Last Name</span>
                  <input
                    type="text"
                    onChange={handleInputChange}
                    name="lastName"
                    placeholder="Enter your last name"
                    required
                  />
                </div>
                <div class="input-box">
                  <span class="details">E-mail</span>
                  <input
                    type="email"
                    onChange={handleInputChange}
                    name="email"
                    placeholder="Enter your email"
                    required
                  />
                </div>
                <div class="input-box">
                  <span class="details">Password</span>
                  <input
                    type="password"
                    onChange={handleInputChange}
                    name="password"
                    placeholder="Enter your password"
                    required
                  />
                </div>
                <div class="input-box">
                  <span class="details">Phone Number</span>
                  <input
                    type="number"
                    onChange={handleInputChange}
                    name="phoneNumber"
                    placeholder="Enter your phone"
                    required
                  />
                </div>
                <div class="input-box">
                  <span class="details">Date of birth</span>
                  <input
                    type="date"
                    onChange={handleInputChange}
                    name="dateOfBirth"
                    placeholder="Enter your date"
                    required
                  />
                </div>
              </div>
              <div class="button">
                <input type="submit" onClick={handleSubmit} value="Register" />
              </div>
              <a href="/login">Already Has An Account?</a>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default Register;
