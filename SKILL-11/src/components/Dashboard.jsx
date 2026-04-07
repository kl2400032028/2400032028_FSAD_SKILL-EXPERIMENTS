import React from 'react';
import { Link } from 'react-router-dom';

const Dashboard = () => {
  return (
    <div className="dashboard-container">
      <h1>🌍 Global News Portal</h1>
      <p>Select a data source to explore user and article information.</p>
      
      <div className="nav-links">
        <Link to="/local" className="nav-btn">📁 Local Users</Link>
        <Link to="/api" className="nav-btn">🌐 Users API</Link>
        <Link to="/posts" className="nav-btn">📝 Fake API Posts</Link>
      </div>
    </div>
  );
};

export default Dashboard;
