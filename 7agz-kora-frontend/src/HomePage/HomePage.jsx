import Cookies from "universal-cookie";
import React from "react";
import Avatar from "@mui/material/Avatar";

function HomePage() {
  const cookies = new Cookies();

  const nameCookie = cookies.get("fullName");
  const imageCookie = cookies.get("image");
  return (
    <div>
      <Avatar
        alt="Se7s Pic"
        src={imageCookie}
        style={{ width: "70px", height: "70px" }}
      />
    </div>
  );
}

export default HomePage;
