// src/components/Navbar.js
import React from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./styles/Navbar.css";

const Navbar = () => {
  const navigate = useNavigate();
  const apiUrl = process.env.REACT_APP_API_URL;

  const handleLogout = () => {
    axios.post(`${apiUrl}/logout`, {}, { withCredentials: true })
      .then(() => {
        // Clear local session data if needed
        navigate("/login");
      })
      .catch((error) => {
        console.error("Logout failed:", error);
      });
  };

  return (
    <nav className="navbar">
      <div className="logo">Expense Tracker</div>
      <button onClick={handleLogout} className="logout-button">Logout</button>
    </nav>
  );
};

export default Navbar;
