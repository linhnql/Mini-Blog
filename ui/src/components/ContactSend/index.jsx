import React, { useState } from "react";
import { alertService } from "../Alert/alert.service";
import style from "./styles.module.scss";
import axios from "axios";
import { Link } from "react-router-dom";

const Contact = () => {
  const [values, setValues] = useState({
    name: "",
    email: "",
    messenger: "",
  });
  const [loader, setLoader] = useState(false);

  const handleChange = (event) => {
    setValues({
      ...values,
      [event.target.name]: event.target.value,
    });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoader(true);
    if (!values.name || !values.email || !values.messenger) {
      alertService.error("Please enter all mandatory fields!");
    } else {
      axios
        .post(
          "http://localhost:8082/miniblog/backend/v1/contacts",
          {
            ...values,
          },
          {
            headers: {
              apikey: "2347edfd-c55c-4f59-96ee-600492f904f3",
              "Access-Control-Allow-Origin": "*",
            },
          }
        )
        .then((request) => {
          setValues(request.data);
          alert("Message Sent! Thank you for your contact.");
          setLoader(false);
          window.location.replace(`/contacts`);
        })
        .catch((error) => {
          alertService.error("Something went wrong! Please try again.");
        });
    }
  };

  return (
    <div className={style["contact"]}>
      <div className={style["contact__body"]}>
        <div className={style["contact__body--header"]}>Get in touch</div>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Eum sapiente
          minus omnis accusantium natus, a doloribus numquam sed neque
          architecto adipisci non quia dolor aperiam obcaecati quibusdam
          corporis ullam saepe em est voluptatem, ea quia quis. Lorem ipsum
          dolor sit amet consectetur, adipisicing elit. Consequuntur, distinctio
          soluta ex ea perferendis corrupti?
        </p>
        <p>
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Sequi,
          deserunt velit veniam harum ad nulla hic minima odit omnis dolorum
          dolores veritatis unde dolordit recusandae dolore omnis facere
          asperiores incidunt vel autem fuga minus sint eligendi vitae. Facilis,
          quo?
        </p>
        <form
          className={style["contact__body--form"]}
          method="post"
          action="action_page.java"
        >
          <label htmlFor="name">Name</label>
          <input
            type="text"
            name="name"
            id="name"
            placeholder="Enter your name"
            value={values.name}
            onChange={handleChange}
          />
          <label htmlFor="email">Email Address</label>
          <input
            type="email"
            name="email"
            placeholder="Enter your email address"
            value={values.email}
            onChange={handleChange}
          />
          <label htmlFor="messenger">Message</label>
          <textarea
            name="messenger"
            rows="6"
            placeholder="Enter your message"
            value={values.messenger}
            onChange={handleChange}
          ></textarea>
          <input
            type="submit"
            name="submit"
            value="Submit"
            onClick={(e) => handleSubmit(e)}
          />
        </form>
      </div>
    </div>
  );
};

export default Contact;
