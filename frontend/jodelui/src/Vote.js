import React, { useState, useEffect, useRef } from "react";
import { Toast } from "primereact/toast";
import "primereact/resources/primereact.min.css";
import apiService from "./services/Api";
import "primeicons/primeicons.css";
import "./Jodel.css";

function Votes({ id, type }) {
  const [votes, setVotes] = useState(0);
  const toast = useRef(null);

  const getVotes = async () => {
    const voteNumber = await apiService.getJodelVote(id, type);
    setVotes(voteNumber);
  };

  useEffect(() => {
    getVotes();
  }, [id]);

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


  const showConflict = () => {
    toast.current.show({
      severity: "info",
      icon: "pi pi-angle-up",
      detail: "Dieser Vote bereits getätigt",
      life: 1500,
    });
  };

  const showUp = () => {
    toast.current.show({
      severity: "success",
      icon: "pi pi-angle-up",
      detail: "Upvote",
      life: 1500,
    });
  };
  const showDown = () => {
    toast.current.show({
      severity: "error",
      icon: "pi pi-angle-down",
      detail: "Downvote",
      life: 1500,
    });
  };

  const postVote = async (direction) => {
    try {
      const myVote = await apiService.setVote(
        id,
        direction,
        "userid",
        type
      );
    } catch (error) {
      throw error;
    }
  };

  return (
    <div>
      <div className="jodel-karma" style={{flexDirection: "column"}}>
          <button className="vote-button pi pi-angle-up" onClick={handleUpvote}></button>
          <span>{votes}</span>
          <button className="vote-button pi pi-angle-down" onClick={handleDownvote}></button>
        </div>

        <div>
            <Toast ref={toast} position="top-center"/>
        </div>

    </div>
  );
}

export default Votes;
