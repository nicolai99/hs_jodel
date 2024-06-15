import React, { useState } from "react";
import apiService from "./services/Api";
import "./Jodel.css";
import Comments from "./Comments";
import Votes from "./Vote";
// import { NavLink } from "react-router-dom";

function Jodel({ jodel }) {
  const [showComments, setShowComments] = useState(false);
  const [reloadedJodel, setReloadedJodel] = useState(jodel);
  // const link = `/jodel/${jodel.id}`; // Für Comment-Page

  const reloadJodel = async () => {
    const response = await apiService.getJodelById(jodel.id);
    setReloadedJodel(response);
  }

  const toggleComments = () => {
    //neu fetchen
    reloadJodel();
    setShowComments(!showComments);
  };

  return (

    // custom backgroundcolor
    <div className="jodel" style={{ backgroundColor: "#2b5a87" }}>
      <div className="jodel-header">
        <div className="jodel-username">{jodel.user.name}</div>
        <div className="jodel-time">
          {new Date(jodel.timestemp).toLocaleString()}
        </div>
      </div>

      <div className="jodel-text">{jodel.text}</div>

      <div className="jodel-footer">
        <div className="jodel-karma">
          <Votes id={jodel.id} type={'jodel'}/>
        </div>

        

        <button className="comment-button" onClick={toggleComments}>
            <svg
              xmlns="http://www.w3.org/2000/svg"
              height="24px"
              viewBox="0 -960 960 960"
              width="24px"
              fill="#FFFFFF"
            >
              <path d="M280-240q-17 0-28.5-11.5T240-280v-80h520v-360h80q17 0 28.5 11.5T880-680v600L720-240H280ZM80-280v-560q0-17 11.5-28.5T120-880h520q17 0 28.5 11.5T680-840v360q0 17-11.5 28.5T640-440H240L80-280Zm520-240v-280H160v280h440Zm-440 0v-280 280Z" />
            </svg>
        </button>

      </div>

      {showComments && (
        // jodel neu fetchen und übergeben
        <Comments jodel={reloadedJodel}/>
      )}

    </div>
  );
}

export default Jodel;
