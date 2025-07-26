import React, { useEffect, useState } from "react";
import axios from "axios";
import "./styles/Transactions.css";

const Transactions = () => {
  const [transactions, setTransactions] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [editModalOpen, setEditModalOpen] = useState(false);
  const [newTransaction, setNewTransaction] = useState({
    description: "",
    amount: ""
  });
  const [editTransaction, setEditTransaction] = useState({
    transactionId: "",
    description: "",
    amount: ""
  });
  const apiUrl = process.env.REACT_APP_API_URL;

  useEffect(() => {
    fetchTransactions();
  }, []);

  const fetchTransactions = () => {
    axios
      .get(`${apiUrl}/transactions/getTransactions`, { withCredentials: true })
      .then((res) => setTransactions(res.data))
      .catch((err) => console.error("Error fetching transactions:", err));
  };

  const handleDelete = (id) => {
    axios
      .delete(`${apiUrl}/transactions/deleteTransaction?transactionId=${id}`, {
        withCredentials: true,
      })
      .then(() => {
        setTransactions(transactions.filter((tx) => tx.transactionId !== id));
      })
      .catch((err) => console.error("Delete failed:", err));
  };

  const handleUpdate = (tx) => {
    setEditTransaction({
      transactionId: tx.transactionId,
      description: tx.description,
      amount: tx.amount
    });
    setEditModalOpen(true);
  };

  const handleEditInputChange = (e) => {
    setEditTransaction({ ...editTransaction, [e.target.name]: e.target.value });
  };

  const handleUpdateSubmit = (e) => {
    e.preventDefault();
    axios
      .patch(`${apiUrl}/transactions/updateTransaction`, editTransaction, {
        withCredentials: true,
      })
      .then(() => {
        setEditModalOpen(false);
        setEditTransaction({ transactionId: "", description: "", amount: "" });
        fetchTransactions();
      })
      .catch((err) => console.error("Update transaction failed:", err));
  };

  const handleInputChange = (e) => {
    setNewTransaction({ ...newTransaction, [e.target.name]: e.target.value });
  };

  const handleAddTransaction = (e) => {
    e.preventDefault();
    axios
      .post(`${apiUrl}/transactions/addTransaction`, newTransaction, {
        withCredentials: true,
      })
      .then(() => {
        setShowModal(false);
        setNewTransaction({ description: "", amount: "" });
        fetchTransactions();
      })
      .catch((err) => console.error("Add transaction failed:", err));
  };

  return (
    <div className="transaction-container">
      <h2>Your Transactions</h2>

      <button
        onClick={() => setShowModal(true)}
        style={{
          marginBottom: "20px",
          padding: "10px 15px",
          fontWeight: "bold",
          background: "#007bff",
          color: "white",
          border: "none",
          borderRadius: "8px",
          cursor: "pointer",
          width: "max-content",
          alignSelf: "flex-end"
        }}
      >
        + Add Expense
      </button>

      <table className="transaction-table">
        <thead>
          <tr>
            <th>Note</th>
            <th>Amount</th>
            <th>Updated Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((tx) => (
            <tr key={tx.transactionId}>
              <td>{tx.description}</td>
              <td>₹{tx.amount}</td>
              <td>{tx.updatedAt}</td>
              <td>
                <button onClick={() => handleUpdate(tx)} className="update-btn">Update</button>
                <button onClick={() => handleDelete(tx.transactionId)} className="delete-btn">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Add Modal */}
      {showModal && (
        <div className="modal-overlay">
          <div className="modal">
            <h3>Add New Transaction</h3>
            <form onSubmit={handleAddTransaction}>
              <input
                name="description"
                placeholder="Description"
                value={newTransaction.description}
                onChange={handleInputChange}
                required
              />
              <input
                name="amount"
                type="number"
                placeholder="Amount"
                value={newTransaction.amount}
                onChange={handleInputChange}
                required
              />
              <div className="modal-buttons">
                <button type="submit" className="submit-btn">Submit</button>
                <button type="button" className="cancel-btn" onClick={() => setShowModal(false)}>Back</button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Update Modal */}
      {editModalOpen && (
        <div className="modal-overlay">
          <div className="modal">
            <h3>Edit Transaction</h3>
            <form onSubmit={handleUpdateSubmit}>
              <input
                name="description"
                placeholder="Description"
                value={editTransaction.description}
                onChange={handleEditInputChange}
                required
              />
              <input
                name="amount"
                type="number"
                placeholder="Amount"
                value={editTransaction.amount}
                onChange={handleEditInputChange}
                required
              />
              <div className="modal-buttons">
                <button type="submit" className="submit-btn">Submit</button>
                <button type="button" className="cancel-btn" onClick={() => setEditModalOpen(false)}>Back</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Transactions;
