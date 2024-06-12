import React from 'react';
import {useParams} from 'react-router-dom';

const CommentPage = () => {
  const {id}=useParams();

  return (
    <div>
      <h2>Kommentar</h2>
    </div>
  );
};

export default CommentPage;
