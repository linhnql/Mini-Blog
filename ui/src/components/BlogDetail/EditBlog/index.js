import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { alertService } from "../../Alert/alert.service";
import axios from "axios";
import "./styles.css";

const categorySelect = [
  "ART", "MUSIC", "TRAVEL", "TECH"
]

const EditBlog = () => {
  const [loader, setLoader] = useState(false);
  const [values, setValues] = useState({});
  const { id } = useParams();
  const [blogId, setBlogId] = useState();

  const baseURL = `http://localhost:8082/miniblog/backend/v1/blogs/${id}`;
  const headers = {
    apikey: "2347edfd-c55c-4f59-96ee-600492f904f3",
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  };

  useEffect(() => {
    axios.get(baseURL, { headers }).then((response) => {
      setValues(response.data);
      setBlogId(id)
    });
  }, [blogId]);

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
      alertService.error("Please enter ALL mandatory fields!");
    } else {
      axios
        .put(
          `http://localhost:8082/miniblog/backend/v1/blogs/${id}`,
          {
            ...values,
          },
          {
            headers: {
              apikey: "2347edfd-c55c-4f59-96ee-600492f904f3",
              "Access-Control-Allow-Origin": "*",
              "Content-Type": "application/json"
            },
          }
        )
        .then((request) => {
          setValues(request.data);
          alert(`Edit ${values.title} successful. Thank you!`);
          setLoader(false);
          console.log(request.data);
          window.location.replace(`/blogs/${id}`);
        })
        .catch((error) => {
          alertService.error("Something went wrong! Please try again.");
          console.log(values);
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
              defaultValue={values?.title}
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
            <select id="category" name="category" onChange={handleChange}>
              {categorySelect.map((item) => (
                <option key={item} selected={values.category === item}>{item}</option>
              ))}
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
              id="image"
              name="image"
              placeholder="Image url..."
              defaultValue={values?.image}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-25">
            <label htmlFor="subject">
              Introduction
            </label>
          </div>
          <div className="col-75">
            <textarea
              id="intro"
              name="intro"
              placeholder="Write something..."
              rows="6"
              defaultValue={values?.intro}
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
              id="detail"
              name="detail"
              placeholder="Write something..."
              rows="20"
              defaultValue={values?.detail}
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

export default EditBlog;
