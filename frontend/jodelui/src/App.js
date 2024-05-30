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

  const handleFilterChange = (event) => {
    setFilter(event.target.value);
  };

  const getAllJodel = async () => {
    apiService.getAllJodel();
  };

  return (
    <div className="container">
      <header className="header">
        <h1>Jodel</h1>
        <div className="header-right">
          <button className="new-post-button" onClick={getAllJodel}>
            +
          </button>
        </div>
      </header>

      <div className="filter">
        <select value={filter} onChange={handleFilterChange}>
          <option value="Neueste">Neueste</option>
          <option value="Beliebteste">Beliebteste</option>
          <option value="Kommentare">Kommentare</option>
        </select>
      </div>

      <div className="posts">
        {posts.map((post) => (
          <Post key={post.id} post={post} />
        ))}
      </div>
    </div>
  );
}

export default App;
