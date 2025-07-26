import React, {useEffect, useState } from "react";
import axios from "axios";
import "./styles/Dashboard.css";

const Dashboard = () => {
    const apiUrl = process.env.REACT_APP_API_URL;
    const [amount, setAmount] = useState([]);

  useEffect(() => {
    fetchTransactionAmount();
  }, []);

  const fetchTransactionAmount = () => {
    axios
      .get(`${apiUrl}/transactions/getTotalAmount`, { withCredentials: true })
      .then((res) => setAmount(res.data))
      .catch((err) => console.error("Error fetching transaction amount:", err));
  }

  return (
    <div className='dashboard-container'>
        <h3>Your Total Expenses So far</h3>
        <br></br>
        <h4>Amount : {amount}</h4>
    </div>
  )
}

export default Dashboard
