import React from "react";
import "./styles.css";
import Header from "../Header";
import AlertCustom from "../Alert";

export default function App() {
  return (
    <div className="App">
      <AlertCustom />
      <Header />
    </div>
  );
}