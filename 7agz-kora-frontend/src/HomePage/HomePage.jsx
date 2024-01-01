import Cookies from "universal-cookie";
import React from "react";
import Avatar from "@mui/material/Avatar";

function HomePage() {
  const cookies = new Cookies();

  const nameCookie = cookies.get("fullName");
  const imageCookie = cookies.get("image");
  return (
    <div>
      <Avatar alt="Se7s Pic" src={imageCookie} width={250} height={250} />
    </div>
  );
}

export default HomePage;
