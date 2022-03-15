import React from "react";
import { Link } from "react-router-dom";
import style from "./styles.module.scss";
import Moment from "react-moment";

const Contact = ({contact}) => {
  return (
    <div className={style["contact"]}>
      <div className={style["contact__body"]}>
        <Link
          className={style["contact__body__title"]}
          to={`/contacts/${contact.id}`}
        >
          {contact.name}
        </Link>
        <div className={style["contact__body__email"]}>
          {contact.email}
          <span className={style["contact__body__info"]}>
            <Moment date={contact.sentAt} format="DD MMM, YYYY" />
          </span>
        </div>
        <div className={style["contact__body__detail"]}>{contact.messenger}</div>
        <div className={style["contact__body__read"]}>
          <Link
            className={style["contact__body__read__btn"]}
            to={`/contacts/${contact.id}`}
          >
            Read...
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Contact;
