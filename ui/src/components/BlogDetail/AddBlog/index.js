import React, { useState } from "react";
import { alertService } from "../../Alert/alert.service";
import axios from "axios";

const AddBlog = () => {
  const [values, setValues] = useState({
    title: "",
    category: "ART",
    image: "",
    intro: "",
    detail: ""
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
    if (!values.title || !values.detail) {
      alertService.error("Please enter all mandatory fields!");
    } else {
      axios
        .post(
          "http://localhost:8082/miniblog/backend/v1/blogs",
          {
            ...values,
          },
          {
            headers: {
              apikey: "2347edfd-c55c-4f59-96ee-600492f904f3",
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json",
            },
          }
        )
        .then((request) => {
          setValues(request.data);
          alert("Add successful. Thank you!");
          setLoader(false);
          window.location.reload();
        })
        .catch((error) => {
          alertService.error("Something went wrong! Please try again.");
        });
    }
  };

  return (
    <div className="container">
      <form action="action_page.java">
        <div className="row">
          <div className="col-25">
            <label htmlFor="title">
              Title<span>*</span>
            </label>
          </div>
          <div className="col-75">
            <input
              type="text"
              id="title"
              name="title"
              placeholder="Blog title..."
              value={values.title}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="category">
              Category<span>*</span>
            </label>
          </div>
          <div className="col-75">
            <select id="category" name="category" defaultValue={values.category} onChange={handleChange}>
              <option selected={values.category === "ART"}>ART</option>
              <option selected={values.category === "MUSIC"}>MUSIC</option>
              <option selected={values.category === "TRAVEL"}>TRAVEL</option>
              <option selected={values.category === "TECH"}>TECH</option>
            </select>
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="image">Image</label>
          </div>
          <div className="col-75">
            <input
              type="text"
              name="image"
              placeholder="Image url..."
              value={values.image}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="subject">Introduction</label>
          </div>
          <div className="col-75">
            <textarea
              name="intro"
              placeholder="Write something..."
              rows="6"
              value={values.intro}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="subject">
              Detail<span>*</span>
            </label>
          </div>
          <div className="col-75">
            <textarea
              name="detail"
              placeholder="Write something..."
              rows="20"
              value={values.detail}
              onChange={handleChange}
            />
          </div>
        </div>
        <input
          type="submit"
          name="submit"
          value="Submit"
          onClick={(e) => handleSubmit(e)}
        />
      </form>
    </div>
  );
};

export default AddBlog;
