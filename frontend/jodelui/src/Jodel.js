import React, { useState, useEffect, useRef } from "react";
import { NavLink } from "react-router-dom";
import { Toast } from "primereact/toast";
import "primereact/resources/primereact.min.css";
import apiService from "./services/Api";
import "primereact/resources/themes/saga-blue/theme.css";
import "primeicons/primeicons.css";
import "./Jodel.css";

function Jodel({ jodel }) {
  const [votes, setVotes] = useState(0);

  const getVotes = async () => {
    const voteNumber = await apiService.getJodelVote(jodel.id, "jodel");
    setVotes(voteNumber);
  };

  useEffect(() => {
    getVotes();
  }, [jodel.id]);

  // -> hat User schon gevotet?

  // const[karma] = await useState( getVotes());

  const handleUpvote = async () => {
    try {
      await postVote(1);
      showUp();
    } catch (error) {
      if (error.message == 409) {
        showConflict();
      }
    }
    await getVotes();
  };

  const handleDownvote = async () => {
    try {
      await postVote(-1);
      showDown();
    } catch (error) {
      if (error.message == 409) {
        showConflict();
      }
    }
    await getVotes();
  };

  const toast = useRef(null);

  const showConflict = () => {
    toast.current.show({
      severity: "info",
      icon: "pi pi-angle-up",
      detail: "Dieser Vote bereits getätigt",
      life: 1000,
    });
  };

  const showUp = () => {
    toast.current.show({
      severity: "success",
      icon: "pi pi-angle-up",
      detail: "Upvote",
      life: 1000,
    });
  };
  const showDown = () => {
    toast.current.show({
      severity: "error",
      icon: "pi pi-angle-down",
      detail: "Downvote",
      life: 1000,
    });
  };

  const postVote = async (direction) => {
    try {
      const myVote = await apiService.setVote(
        jodel.id,
        direction,
        jodel.user.id, //hier der angemeldete User (nicht der, des jodels)
        "jodel"
      );
    } catch (error) {
      throw error;
    }
  };

  const link = `/jodel/${jodel.id}`;
  return (
    // custom backgroundcolor

    <div className="jodel" style={{ backgroundColor: "#2b5a87" }}>
      <div className="jodel-header">
        {/* username aus Backend ? */}
        <div className="jodel-username">{jodel.user.name}</div>
        <div className="jodel-time">
          {new Date(jodel.timestemp).toLocaleString()}
        </div>
      </div>

      <div className="jodel-text">{jodel.text}</div>

      <div className="jodel-footer">
        <div className="jodel-karma">
          <button className="vote-button" onClick={handleDownvote}>
            -
          </button>
          <span>{votes}</span>
          <button className="vote-button" onClick={handleUpvote}>
            +
          </button>
        </div>

        <NavLink to={link} end>
          <button className="comment-button">
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
        </NavLink>
      </div>
      <div>
        <Toast ref={toast} position="top-center" />
      </div>
    </div>
  );
}

export default Jodel;
