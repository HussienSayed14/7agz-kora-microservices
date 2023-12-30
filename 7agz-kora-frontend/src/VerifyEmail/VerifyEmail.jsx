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
import { useLocation } from "react-router-dom";
import "./VerifyEmail.css";

function VerifyEmail() {
  const baseUrl = process.env.REACT_APP_API_URL;
  const [loading, setLoading] = useState(false);
  const [renderCounter, setRenderCounter] = useState(0);
  const [errorMessage, setErrorMessage] = useState("");
  const [optSmModal, setOptSmModal] = useState(false);

  const location = useLocation();
  const userEmail = location.state;

  const [otpRequest, setOtpRequest] = useState({
    email: userEmail,
    otp: "",
  });
  const toggleOpen = () => setOptSmModal(!optSmModal);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setOtpRequest((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setRenderCounter(renderCounter + 1);
  };

  function handleSuccessOtp(response) {
    if (response.responseCode === "0") {
      window.location.href = "/login";
    }
  }

  function handleFailedOtp(response) {
    setErrorMessage(response.responseMessage);
    toggleOpen();
  }

  useEffect(() => {
    if (otpRequest.otp === "") {
      return;
    }

    console.log("Data: ", otpRequest);
    const endPoint = "accounts/api/v1/auth";
    setLoading(true);
    axios
      .post(`${baseUrl}${endPoint}/verifyOtp`, otpRequest)
      .then((res) => {
        console.log(res.data);
        handleSuccessOtp(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setLoading(false);
        console.log(err.response.data);
        handleFailedOtp(err.response.data);
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
          <div class="title">Email Verification</div>
          <h6>We Have Sent an OTP to your email.</h6>
          <div class="content">
            <form>
              <div class="user-details">
                <div class="input-box">
                  <span class="details">OTP</span>
                  <input
                    onChange={handleInputChange}
                    type="number"
                    name="otp"
                    placeholder="Enter OTP"
                    required
                  />
                </div>
              </div>
              <div class="button">
                <input type="submit" onClick={handleSubmit} value="Submit" />
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}

export default VerifyEmail;
