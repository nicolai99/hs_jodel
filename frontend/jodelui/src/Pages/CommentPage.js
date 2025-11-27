import React, {useState, useEffect} from 'react';
import {useParams} from 'react-router-dom';
import apiService from '../services/Api'

const CommentPage = () => {

  const [jodel, setJodel] = useState([]);
  const {id}=useParams();

  const getJodel = async() => {
    try {
      const response = await apiService.getJodelById(id);
      
      setJodel(response);
    } catch (error) {
      console.error("Get jodel by ID / commentpage: ", error);
    }
  }

  useEffect(() => {
    getJodel();
  }, [id]);

  return (
    <div>
      <h2>Kommentar</h2>
      <div>{jodel.text}</div>
    </div>
  );
};

export default CommentPage;