import React, { useState, useEffect } from "react";
import "./JodelPage.css";
import Jodel from "../Jodel";
import apiService from "../services/Api";

const JodelPage = () => {
    
    const [filter, setFilter] = useState("Neueste");
    const [isCreatingPost, setIsCreatingPost] = useState(false);
    const [newJodelText, setNewJodelText] = useState("");
    const [jodels, setJodels] = useState([]);

    const handleFilterChange = (event) => {
        setFilter(event.target.value);
    };
    const getLocation = async () => {
        const location = await apiService.getCurrentLocation();
        alert(String(location.lat) + " " + String(location.lon));
    };
    const getAllJodel = async () => {
        try {
        const response = await apiService.getAllJodel();
        setJodels(response);
        } catch (error) {
        console.error("getJodel-error: ", error);
        }
    };

    useEffect(() => {
        getAllJodel();
    }, []);

    // const getVote = async () => {
    //   apiService.getJodelVote(1, "jodel");
    // }

    const handleNewPostButtonClick = () => {
        setIsCreatingPost(true);
    };

    const handlePostCancel = () => {
        setIsCreatingPost(false);
        setNewJodelText("");
    };

    const postNewJodel = async () => {
        const location = await apiService.getCurrentLocation();
        const newJodel = await apiService.setJodel(
            newJodelText,
            location.lat,
            location.lon
        );
        setJodels([...jodels, newJodel]);
        setIsCreatingPost(false);
        // apiService.setJodel(newJodelText);
        setNewJodelText("");
    };


  return (
    
    <div className="container">
        
        {isCreatingPost && (
            <div className="new-post-form">
            <textarea
                value={newJodelText}
                onChange={(e) => setNewJodelText(e.target.value)}
                placeholder="Schreibe deinen Jodel..."
            ></textarea>
            <div className="new-post-buttons">
                <button onClick={handlePostCancel}>Cancel</button>
                <button onClick={postNewJodel}>Post</button>
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
                <button className="new-post-button" onClick={handleNewPostButtonClick}>
                +
                </button>
            </div>
        )}

        {!isCreatingPost && (
            <div className="posts">
            {jodels.map((post) => (
                <Jodel key={post.id} jodel={post} />
            ))}
            </div>
        )}

    </div>
  );
};

export default JodelPage;