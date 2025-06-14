import React, { useEffect, useState } from "react";
import axios from "axios";
import "./styles/Transactions.css";

const Transactions = () => {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/transactions/getTransactions", { withCredentials: true })
      .then((res) => setTransactions(res.data))
      .catch((err) => console.error("Error fetching transactions:", err));

      
  }, []);

  const handleDelete = (id) => {
    axios
      .delete(`http://localhost:8080/transactions/deleteTransaction?transactionId=${id}`, {
        withCredentials: true,
      })
      .then(() => {
        setTransactions(transactions.filter((tx) => tx.transaction_id !== id));
      })
      .catch((err) => console.error("Delete failed:", err));
  };

  const handleUpdate = (id) => {
    // You can open a modal here to update data (not covered now)
    alert(`Update transaction ${id} (feature coming soon)`);
  };

  return (
    <div className="transaction-container">
      <h2>Your Transactions</h2>
      {console.log(transactions)}
      <table className="transaction-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Amount</th>
            <th>Created Date</th>
            <th>Updated Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((tx) => (
            <tr key={tx.transactionId}>
              <td>{tx.transactionId}</td>
              <td>{tx.description}</td>
              <td>₹{tx.amount}</td>
              <td>{tx.createdAt}</td>
              <td>{tx.updatedAt}</td>
              <td>
                <button onClick={() => handleUpdate(tx.transaction_id)} className="update-btn">Update</button>
                <button onClick={() => handleDelete(tx.transaction_id)} className="delete-btn">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Transactions;
