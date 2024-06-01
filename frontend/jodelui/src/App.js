import React, { useState } from "react";
import "./App.css";
import Post from "./Post";
import apiService from "./services/Api";

const posts = [
  {
    id: 1,
    username: "Nicolai",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 5min",
    karma: 85,
    color: "#82B1FF",
  },
  {
    id: 2,
    username: "Linus",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 9min",
    karma: 85,
    color: "#CE93D8",
  },
  {
    id: 3,
    username: "usrname",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 9min",
    karma: 85,
    color: "#FF8A80",
  },
  {
    id: 4,
    username: "Nicolai",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 5min",
    karma: 85,
    color: "#82B1FF",
  },
  {
    id: 5,
    username: "Linus",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 9min",
    karma: 85,
    color: "#CE93D8",
  },
  {
    id: 6,
    username: "usrname",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 9min",
    karma: 85,
    color: "#FF8A80",
  },
  {
    id: 7,
    username: "Nicolai",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 5min",
    karma: 85,
    color: "#82B1FF",
  },
  {
    id: 8,
    username: "Linus",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 9min",
    karma: 85,
    color: "#CE93D8",
  },
  {
    id: 9,
    username: "usrname",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 9min",
    karma: 85,
    color: "#FF8A80",
  },
  {
    id: 10,
    username: "Nicolai",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 5min",
    karma: 85,
    color: "#82B1FF",
  },
  {
    id: 11,
    username: "Linus",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 9min",
    karma: 85,
    color: "#CE93D8",
  },
  {
    id: 12,
    username: "usrname",
    text: "Ich: Sollen wir uns das letzte Stück Pizza teilen?\nSie: Nee, das schaffe ich alleine!",
    time: "nah - 9min",
    karma: 85,
    color: "#FF8A80",
  },
];

function App() {
  const [filter, setFilter] = useState("Neueste");
  const [isCreatingPost, setIsCreatingPost] = useState(false);
  const [newPostText, setNewPostText] = useState('');

  const handleFilterChange = (event) => {
    setFilter(event.target.value);
  };

  const getAllJodel = async () => {
    apiService.getAllJodel();
  };

  const handleNewPostButtonClick = () => {
    setIsCreatingPost(true);
  };

  const handlePostCancel = () => {
    setIsCreatingPost(false);
    setNewPostText('');
  };

  const handlePostSubmit = () => {
    setIsCreatingPost(false);
    setNewPostText('');
  };


  return (
    <div className="container">

      <header className="header">
        <h1>Jodel</h1>
        <div className="header-right">
          {!isCreatingPost && (
            <button className="new-post-button" onClick={handleNewPostButtonClick}>+</button>
          )}
          <button onClick={getAllJodel}>TestAPI</button>
        </div>
      </header>

      {isCreatingPost && (
        <div className="new-post-form">
          <textarea
            value={newPostText}
            onChange={(e) => setNewPostText(e.target.value)}
            placeholder="Schreibe deinen Yodel-Text...">
          </textarea>
          <div className="new-post-buttons">
            <button onClick={handlePostCancel}>Cancel</button>
            <button onClick={handlePostSubmit}>Post</button>
          </div>
        </div>
      )}

      {!isCreatingPost && (
        <div className="filter">
          <select value={filter} onChange={handleFilterChange}>
            <option value="Neueste">Neueste</option>
            <option value="Beliebteste">Beliebteste</option>
            <option value="Kommentare">Kommentare</option>
          </select>
        </div>
      )}

      {!isCreatingPost && (
        <div className="posts">
          {posts.map(post => (
            <Post key={post.id} post={post} />
          ))}
        </div>
      )}

    </div>
  );
}

export default App;
