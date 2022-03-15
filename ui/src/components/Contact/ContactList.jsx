import React, { useEffect, useState, useMemo } from "react";
import axios from "axios";
import Pagination from "../Pagination/Pagination";
import Contact from ".";

let PageSize = 4;

const ContactList = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const [contacts, setContacts] = useState([]);

  const baseURL = `http://localhost:8082/miniblog/backend/v1/contacts`;
  const headers = {
    apikey: "2347edfd-c55c-4f59-96ee-600492f904f3",
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  };

  const currentData = useMemo(() => {
    const firstPageIndex = (currentPage - 1) * PageSize;
    const lastPageIndex = firstPageIndex + PageSize;
    return contacts.slice(firstPageIndex, lastPageIndex);
  }, [contacts, currentPage]);

  useEffect(() => {
    axios.get(baseURL, { headers }).then((response) => {
        setContacts( response.data);
    });
  }, [currentPage]); 
  currentData.reverse();

  return (
    <>
      {currentData.map((contact) => {
        console.log(contact.sentAt);
        return (
          <Contact
            key={contact.id}
            contact={contact}
          />
        );
      })}
      <Pagination
        className="pagination-bar"
        currentPage={currentPage}
        totalCount={contacts.length}
        pageSize={PageSize}
        onPageChange={page => setCurrentPage(page)}
      />
    </>
  );
};

export default ContactList;
