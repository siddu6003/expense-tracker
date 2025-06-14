// src/components/Home.js
import React, { useEffect, useState } from "react";
import axios from "axios";
import Navbar from './Navbar'
import Transactions from "./Transactions";

const Home = () => {
  const [transactions, setTransactions] = useState([]);
  const [error, setError] = useState(null);
  const apiUrl = process.env.REACT_APP_API_URL;

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const res = await axios.get(`${apiUrl}/transactions/getTransactions`, {
          withCredentials: true, // include session cookie (JSESSIONID)
        });
        setTransactions(res.data);
      } catch (err) {
        setError("Failed to fetch transactions. Please login.");
        console.error(err);
      }
    };

    fetchTransactions();
  }, []);

  return (
    <div>
      <Navbar />
      {error && <p style={{ color: "red" }}>{error}</p>}
      <Transactions/>
    </div>
  );
};

export default Home;
