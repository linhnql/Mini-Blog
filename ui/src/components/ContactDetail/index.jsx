import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import style from "./styles.module.scss";
import axios from "axios";
import { useParams } from "react-router-dom";
import Moment from "react-moment";

const ContactDetail = () => {
  const [contact, setContact] = useState();
  const { id } = useParams();
  const [confirm, setConfirm] = useState(false);

  const baseURL = `http://localhost:8082/miniblog/backend/v1/contacts/${id}`;
  const headers = {
    apikey: "2347edfd-c55c-4f59-96ee-600492f904f3"
  };

  useEffect(() => {
    axios.get(baseURL, { headers }).then((response) => {
      setContact(response.data);
    });
  }, [id]);

  const deleteAPI = async () => {
    axios
      .delete(baseURL, { headers })
      .then(() => {
        alert("Delete successful. Thank you!");
        window.location.replace("/contacts");
      })
      .catch((error) => alert("Something went wrong. Please try again."));
  };

  const DeleteContact = () => {
    return (
      <>
        <div
          className={style["background"]}
          onClick={() => setConfirm(false)}
        />
        <div className={style["popup"]}>
          <p className={style["popup_title"]}>
            Are you sure you want to delete this contact?
          </p>
          <div className={style["popup_btn"]}>
            <button
              className="btn-react btn-yes"
              color="success"
              onClick={() => deleteAPI()}
            >
              Yes
            </button>
            <button
              className="btn-react btn-no"
              color="danger"
              onClick={() => setConfirm(false)}
            >
              No
            </button>
          </div>
        </div>
      </>
    );
  };

  return (
    <>
      {confirm ? <DeleteContact /> : <></>}
      <div className={style["detail"]}>
        <div className={style["detail__body"]}>
          <div className={style["detail__body__header1"]}>{contact?.name}</div>
          <p className={style["detail__body__info"]}>
            {contact?.email} | <span> </span>
            <Moment date={contact?.sentAt} format="DD MMM, YYYY" />
          </p>
          <hr />
          <p className={style["detail__body__last-text"]}>{contact?.messenger}</p>
          <Link className={style["detail__body__touch"]} to="/contacts">
            ← All contacts
          </Link>
          <Link className={style["btn"]} to={`/contacts/reply/${id}`}>
            Reply
          </Link>
          <button className={style["delete"]} onClick={() => setConfirm(true)}>
            Delete
          </button>
        </div>
      </div>
    </>
  );
};

export default ContactDetail;
