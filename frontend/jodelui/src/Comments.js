import React, { useState, useEffect } from "react";
import apiService from "./services/Api";
import './Comments.css'

function Comments({ jodel }) {
  const [comments, setComments] = useState(jodel.comments);
  const [commentText, setCommentText] = useState("");

  // Wert des Textfeldes der Variable commentText zuweisen
  const handleInputChange = (e) => {
    setCommentText(e.target.value);
  };

  const postComment = async() => {
    if (commentText == "") {
      alert("Kommentarfeld darf nicht leer sein.")
    } else {
      const newComment = await apiService.setComment(
        jodel.id,
        commentText
      );
      setComments([...comments, newComment]);
      setCommentText("");
    }

  }

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
  const [votes, setVotes] = useState(0);

  // const getVotes = async () => {
  //   const voteNumber = await apiService.getJodelVote(comment.id, "comment");
  //   console.log(`Votes: ${voteNumber}`);
  //   setVotes(voteNumber);
  // };

  const getVotes = async () => {
    console.log(`Abrufen der Votes für Kommentar ID: ${comment.id}`);
    try {
      const voteNumber = await apiService.getJodelVote(comment.id, "comment");
    } catch (error) {
      console.log("Error bei commentvote")
    }
  };

  // const getVotes = async () => {
  //   try {
  //     const voteNumber = await apiService.getJodelVote(comment.id, "comment");
  //     if (voteNumber !== undefined) {
  //       console.log(`Votes: ${voteNumber}`);
  //       setVotes(voteNumber);
  //     } else {
  //       console.error('Votes konnten nicht geladen werden');
  //     }
  //   } catch (error) {
  //     console.error('Fehler beim Abrufen der Votes:', error);
  //   }
  // };
  

  useEffect(() => {
    getVotes();
  }, [comment.id]);

  return (
    <div key={comment.id} className="comment">
      <div className="comment-username">{comment.user.name}</div>
      <div className="comment-text">{comment.text}</div>

      <div className='comment-karma'>
        <button className="vote-button">&lt;</button>
        <span>{votes}</span>
        <button className="vote-button">&gt;</button>
      </div>

    </div>
  )
}

export default Comments;