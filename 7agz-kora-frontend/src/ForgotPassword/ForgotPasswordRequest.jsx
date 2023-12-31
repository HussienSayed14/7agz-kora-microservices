import { React, useEffect, useState } from "react";
import axios from "axios";
import Cookies from "universal-cookie";
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
import "mdb-react-ui-kit/dist/css/mdb.min.css";
import "./ForgotPassword.css";

function ForgotPasswordRequest() {
  const baseUrl = process.env.REACT_APP_API_URL;
  const [loginRequest, setLoginRequest] = useState({
    email: "",
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

  function handleSuccessLogin(response) {
    if (response.responseCode === "0") {
      console.log(response);
      //window.location = "/forgotPassword";
    }
  }

  function handleFailedLogin(response) {
    setErrorMessage(response.responseMessage);
    toggleOpen();
  }

  useEffect(() => {
    if (loginRequest.email === "") {
      return;
    }
    const endPoint = "accounts/api/v1/auth";
    setLoading(true);
    axios
      .post(`${baseUrl}${endPoint}/forgotPasswordRequest`, loginRequest)
      .then((res) => {
        handleSuccessLogin(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err.response.data);
        setLoading(false);
        handleFailedLogin(err.response.data);
      });
  }, [renderCounter]);

  return (
    <div className="login-container">
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
        <form className="login-form">
          <MDBInput
            onChange={handleInputChange}
            className="mb-4 email-field"
            type="email"
            id="form1Example1"
            label="Email address"
            name="email"
          />
          <MDBRow className="mb-4">
            <MDBCol className="d-flex">
              <a href="/login">Login</a>
            </MDBCol>
            <MDBCol className="d-flex justify-content-center">
              <a href="/register">Register</a>
            </MDBCol>
          </MDBRow>
          <MDBBtn onClick={handleSubmit} type="submit" block>
            Submit
          </MDBBtn>
        </form>
      )}
    </div>
  );
}

export default ForgotPasswordRequest;
