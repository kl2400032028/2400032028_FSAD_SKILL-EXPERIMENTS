import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Dashboard from './components/Dashboard';
import LocalUserList from './components/LocalUserList';
import UserList from './components/UserList';
import FakePostList from './components/FakePostList';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <nav className="top-nav">
          <Link to="/" className="logo">NewsPortal</Link>
          <div className="top-links">
            <Link to="/local">Local</Link>
            <Link to="/api">Public API</Link>
            <Link to="/posts">Fake API</Link>
          </div>
        </nav>

        <main className="main-content">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/local" element={<LocalUserList />} />
            <Route path="/api" element={<UserList />} />
            <Route path="/posts" element={<FakePostList />} />
          </Routes>
        </main>

        <footer className="footer">
          <p>&copy; 2024 Team FSAD News Portal. All rights reserved.</p>
        </footer>
      </div>
    </Router>
  );
}

export default App;
