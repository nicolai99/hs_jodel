import React, { useState, useRef } from "react";
import apiService from "./services/Api";
import './Comments.css'
import Votes from "./Vote";
import { Toast } from "primereact/toast";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";

function Comments({ jodel }) {
  const [comments, setComments] = useState(jodel.comments);
  const [commentText, setCommentText] = useState("");
  const toast = useRef(null);

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
    if (commentText.length === 0) {
      showToast("info", "Kommentarfeld darf nicht leer sein.");
    } else if (commentText.length > 255) { 
      showToast("info", "Kommentarfeld darf nicht über 255 Zeichen lang sein.");
    } else {
      const newComment = await apiService.setComment(
        jodel.id,
        commentText
      );
      await getComments();
      setCommentText("");
    }
  };

  const showToast = (severity, text) => {
    toast.current.show({
      severity: severity,
      icon: "pi pi-angle-up",
      detail: text,
      life: 1500,
    });
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

        <div>
          <Toast ref={toast} position="top-center"/>
        </div>
      
    </div>
  );
}


function Comment({comment}) {

  const date = new Date(comment.timestemp);
  const options = {
    year: 'numeric',
    month: 'numeric',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  };
  const formattedDate = date.toLocaleString('de-DE', options);

  return (
    <div key={comment.id} className="comment">

      <div className="comment-header">
        <div className="comment-username">{comment.user.name}</div>
        <div className="comment-time">{formattedDate}</div>
      </div>

      <div className="comment-text">{comment.text}</div>

      <div className='comment-karma'>
        <Votes id={comment.id} type={'comment'}/>
      </div>

    </div>
  )
}

export default Comments;