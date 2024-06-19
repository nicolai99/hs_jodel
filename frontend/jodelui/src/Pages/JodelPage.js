import React, { useContext, useState, useEffect, useRef } from "react";
import "./JodelPage.css";
import Jodel from "../Jodel";
import apiService from "../services/Api";
import { Dropdown } from "primereact/dropdown";
import { Toast } from "primereact/toast";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import { useLocation } from "../services/LocationContext";

const JodelPage = () => {
  const [isCreatingPost, setIsCreatingPost] = useState(false);
  const [newJodelText, setNewJodelText] = useState("");
  const [jodels, setJodels] = useState([]);
  const toast = useRef(null);
  const { location } = useLocation();

  const defaultOrder = { name: "Neueste", code: "n" };
  const [selectedOrder, setSelectedOrder] = useState(defaultOrder);
  const order = [
    { name: "Neueste", code: "n" },
    { name: "Beliebteste", code: "b" },
    { name: "Kommentare", code: "k" },
  ];

  // const sortJodels = async (jodels, order) => {
  //   const sortedJodels = [...jodels]; // Erstelle eine Kopie des Arrays, um die Sortierung durchzuführen
  //   switch (order) {
  //     case 'n':
  //       return sortedJodels.sort((a, b) => b.timestemp - a.timestemp); // Neueste zuerst
  //     case 'b':
  //       return sortedJodels.sort((a, b) => b.timestemp - a.timestemp); // Neueste zuerst
  //     case 'k':
  //       return sortedJodels.sort((a, b) => b.comments.length - a.comments.length); // Nach Anzahl der Kommentare sortieren
  //     default:
  //       return sortedJodels;
  //   }
  // }
  // const sortedJodels = sortJodels([...jodels], selectedOrder.code);

  const getLocation = async () => {
    const location = await apiService.getCurrentLocation();
    alert(String(location.lat) + " " + String(location.lon));
  };

  const getAllJodel = async () => {
    if (location.latitude !== null && location.longitude !== null) {
      try {
        const response = await apiService.getJodelWithDistance(
          location.latitude,
          location.longitude,
          50
        );
        setJodels(response);
        console.log(response);
        // Weiterverarbeitung der Antwort
      } catch (error) {
        console.error("Fehler bei getJodel", error);
      }
    } else {
      console.error("Location is not available");
    }
  };

  useEffect(() => {
    getAllJodel();
  }, []);

  const handleNewPostButtonClick = () => {
    setIsCreatingPost(true);
  };

  const handlePostCancel = () => {
    setIsCreatingPost(false);
    setNewJodelText("");
  };

  const postNewJodel = async () => {
    if (newJodelText.length === 0) {
      showToast("info", "Du musst einen Text eingeben.");
    } else if (newJodelText.length > 255) {
      showToast("info", "Text darf nicht über 255 Zeichen lang sein.");
    } else {
      console.log(newJodelText.length);
      const location = await apiService.getCurrentLocation();
      const newJodel = await apiService.setJodel(
        newJodelText,
        location.lat,
        location.lon
      );
      console.log(`Neues Jodel: ${newJodel}`);
      // setJodels([...jodels, newJodel]); //-> Fehler, da keine ID, hinterlegt ist
      await getAllJodel();
      setIsCreatingPost(false);
      setNewJodelText("");
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
          {/* <select value={filter} onChange={handleFilterChange}>
            <option value="Neueste">Neueste</option>
            <option value="Beliebteste">Beliebteste</option>
            <option value="Kommentare">Kommentare</option>
          </select> */}
          {/* <Dropdown  
              value={selectedOrder} 
              onChange={(e) => setSelectedOrder(e.value)} 
              options={order} 
              optionLabel="name" 
              placeholder="Reihenfolge wählen" 
              className="w-full md:w-14rem" 
          /> */}
          <Dropdown
            value={selectedOrder}
            onChange={(e) => setSelectedOrder(e.value)}
            options={order}
            optionLabel="name"
            placeholder="Reihenfolge wählen"
            className="w-full md:w-14rem"
          />
        </div>
      )}

      {!isCreatingPost && (
        <div className="post-button-container">
          <button
            className="new-post-button"
            onClick={handleNewPostButtonClick}
          >
            +
          </button>
        </div>
      )}

      {!isCreatingPost && (
        <div className="posts">
          {jodels.map((post) => (
            <Jodel key={post.jodel.id} jodel={post} />
          ))}
        </div>
      )}

      <div>
        <Toast ref={toast} position="top-center" />
      </div>
    </div>
  );
};

export default JodelPage;
