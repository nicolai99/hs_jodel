import React, { useState } from 'react';
import './Post.css';

function Post({ post }) {
  const [karma, setKarma] = useState(post.karma);

  const handleUpvote = () => {
    setKarma(karma + 1);
  };

  const handleDownvote = () => {
    setKarma(karma - 1);
  };

  return (

    <div className="post" style={{ backgroundColor: post.color }}>

      <div className="post-header">
        <div className="post-username">{post.username}</div>
        <div className="post-time">{post.time}</div>
      </div>

      <div className="post-text">{post.text}</div>

      <div className="post-footer">

        <div className="post-karma">
          <button className="vote-button" onClick={handleDownvote}>-
            {/* <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#FFFFFF"><path d="M480-344 240-584l56-56 184 184 184-184 56 56-240 240Z"/></svg> */}
            {/* <img src='./img/test.png'></img> */}
          </button>

          <span>{karma}</span>

          <button className="vote-button" onClick={handleUpvote}>+
            {/* <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#FFFFFF"><path d="M480-528 296-344l-56-56 240-240 240 240-56 56-184-184Z"/></svg> */}
          </button>
        </div>

        <button className="comment-button">
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#FFFFFF"><path d="M280-240q-17 0-28.5-11.5T240-280v-80h520v-360h80q17 0 28.5 11.5T880-680v600L720-240H280ZM80-280v-560q0-17 11.5-28.5T120-880h520q17 0 28.5 11.5T680-840v360q0 17-11.5 28.5T640-440H240L80-280Zm520-240v-280H160v280h440Zm-440 0v-280 280Z"/></svg>
        </button>

      </div>

    </div>
  );
}

export default Post;
