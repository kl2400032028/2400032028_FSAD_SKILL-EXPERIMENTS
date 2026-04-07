import React, { useState, useEffect } from 'react';
import axios from 'axios';

const FakePostList = () => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [filter, setFilter] = useState('');

  const fetchPosts = () => {
    setLoading(true);
    axios.get('https://dummyjson.com/posts')
      .then(response => {
        setPosts(response.data.posts);
        setLoading(false);
      })
      .catch(err => {
        setError(err.message);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  const filteredPosts = filter 
    ? posts.filter(post => post.userId.toString() === filter)
    : posts;

  if (loading) return <div className="loader">Loading fake posts (Axios)...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <div className="view-container">
      <h2>Fake Posts (DummyJSON via Axios)</h2>
      
      <div className="controls">
        <button onClick={fetchPosts} className="btn-refresh">🔄 Refresh Data</button>
        
        <select onChange={(e) => setFilter(e.target.value)} value={filter} className="dropdown">
          <option value="">Filter by User ID (All)</option>
          {[...new Set(posts.map(p => p.userId))].sort((a,b)=>a-b).map(id => (
            <option key={id} value={id}>User {id}</option>
          ))}
        </select>
      </div>

      <div className="card-grid">
        {filteredPosts.map(post => (
          <div key={post.id} className="card post-card">
            <h3>{post.title}</h3>
            <p>{post.body.substring(0, 100)}...</p>
            <span className="user-tag">User {post.userId}</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default FakePostList;
