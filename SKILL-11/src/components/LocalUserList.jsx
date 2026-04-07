import React, { useState, useEffect } from 'react';

const LocalUserList = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('./users.json')
      .then(response => {
        if (!response.ok) throw new Error('Failed to fetch local users');
        return response.json();
      })
      .then(data => {
        setUsers(data);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <div className="loader">Loading local users...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <div className="view-container">
      <h2>Local Users (from JSON)</h2>
      <div className="card-grid">
        {users.map(user => (
          <div key={user.id} className="card">
            <h3>{user.name}</h3>
            <p>📧 {user.email}</p>
            <p>📞 {user.phone}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default LocalUserList;
