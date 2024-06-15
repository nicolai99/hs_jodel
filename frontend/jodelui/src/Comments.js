import React, { useState } from "react";
import apiService from "./services/Api";
import './Comments.css'
import Votes from "./Vote";

function Comments({ jodel }) {
  const [comments, setComments] = useState(jodel.comments);
  const [commentText, setCommentText] = useState("");

  // Wert des Textfeldes der Variable commentText zuweisen
  const handleInputChange = (e) => {
    setCommentText(e.target.value);
  };

  const getComments = async () => {
    const response = await apiService.getJodelById(jodel.id);
    console.log(response.comments);
    setComments(response.comments);
  };

  const postComment = async() => {
    if (commentText == "") {
      alert("Kommentarfeld darf nicht leer sein.")
    } else {
      const newComment = await apiService.setComment(
        jodel.id,
        commentText
      );
      await getComments();
      setCommentText("");
    }
  };

  return (

    <div>
        <div className={'comments-section'}>
          <div className="add-comment">
              <input type="text" placeholder="Neuer Kommentar..." value={commentText} onChange={handleInputChange}/>
              <button onClick={postComment}>&gt;</button>
          </div>
          
          <div className='scroll-container'>
            {Array.isArray(comments) && comments.map((comment) => (
              <Comment key={comment.id} comment={comment}/>
            ))}
          </div>
        </div>
      
    </div>
  );
}


function Comment({comment}) {

  return (
    <div key={comment.id} className="comment">
      <div className="comment-username">{comment.user.name}</div>
      <div className="comment-text">{comment.text}</div>

      <div className='comment-karma'>
        <Votes id={comment.id} type={'comment'}/>
      </div>

    </div>
  )
}

export default Comments;