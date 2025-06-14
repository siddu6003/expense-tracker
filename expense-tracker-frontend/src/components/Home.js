// src/components/Home.js
import React, { useEffect, useState } from "react";
import axios from "axios";
import Navbar from './Navbar'
import Transactions from "./Transactions";

const Home = () => {
  const [transactions, setTransactions] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTransactions = async () => {
      try {
        const res = await axios.get("http://localhost:8080/transactions/getTransactions", {
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
      <h2>Welcome to Expense Tracker</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <Transactions/>
    </div>
  );
};

export default Home;
