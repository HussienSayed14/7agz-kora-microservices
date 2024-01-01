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
import "./Login.css";

function ForgotPassword() {
  const baseUrl = process.env.REACT_APP_API_URL;
  const [loginRequest, setLoginRequest] = useState({
    email: "",
    password: "",
  });
  const [loading, setLoading] = useState(false);
  const [renderCounter, setRenderCounter] = useState(0);
  const [errorMessage, setErrorMessage] = useState("");
  const [optSmModal, setOptSmModal] = useState(false);
  const [token, setToken] = useState("");

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
    const cookie = new Cookies();
    const searchParams = new URLSearchParams(document.location.search);
    setToken(searchParams.get("token"));
    setRenderCounter(renderCounter + 1);
  };

  function handleSuccessLogin(response) {
    if (response.responseCode === "0") {
      window.location = "/login";
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
    const endPoint = "accounts/api/v1/secured";
    setLoading(true);
    let config = {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    };
    axios
      .post(`${baseUrl}${endPoint}/forgotPassword`, loginRequest, config)
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
            type="password"
            id="form1Example1"
            label="Email address"
            name="password"
          />
          <MDBRow className="mb-4">
            <MDBCol className="d-flex">
              <a href="/login">Resend</a>
            </MDBCol>
            <MDBCol className="d-flex justify-content-center">
              <a href="/register">Login Page</a>
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

export default ForgotPassword;
