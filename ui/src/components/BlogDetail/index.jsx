import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import style from "./styles.module.scss";
import axios from "axios";
import { useParams } from "react-router-dom";
import Moment from "react-moment";

const BlogDetail = () => {
  const [blog, setBlog] = useState();
  const { id } = useParams();
  const [confirm, setConfirm] = useState(false);

  const baseURL = `http://localhost:8082/miniblog/backend/v1/blogs/${id}`;
  const headers = {
    apikey: "2347edfd-c55c-4f59-96ee-600492f904f3",
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  };

  useEffect(() => {
    axios.get(baseURL, { headers }).then((response) => {
      setBlog(response.data);
    });
  }, [id]);

  const deleteAPI = async () => {
    axios
      .delete(baseURL, { headers })
      .then(() => {
        alert("Delete successful. Thank you!");
        window.location.replace("/");
      })
      .catch((error) => alert("Something went wrong. Please try again."));
  };

  const DeleteBlog = () => {
    return (
      <>
        <div
          className={style["background"]}
          onClick={() => setConfirm(false)}
        />
        <div className={style["popup"]}>
          <p className={style["popup_title"]}>
            Are you sure you want to delete this blog?
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
      {confirm ? <DeleteBlog /> : <></>}
      <div className={style["detail"]}>
        <img src={blog?.image} alt="image" />
        <div className={style["detail__body"]}>
          <div className={style["detail__body__header1"]}>{blog?.title}</div>
          <p className={style["detail__body__info"]}>
            <Moment date={blog?.createdAt} format="DD MMM, YYYY" /> |{" "}
            {blog?.category}
          </p>
          <hr className={style['hr1']}/>
          <p className={style["detail__body__last-text"]}>{blog?.detail}</p>
          <Link className={style["btn"]} to={`/blogs/edit/${id}`}>
            Edit
          </Link>
          <button className={style["delete"]} onClick={() => setConfirm(true)}>
            Delete
          </button>
          <Link className={style["detail__body__touch"]} to="/">
            ← All posts
          </Link>
        </div>
      </div>
    </>
  );
};

export default BlogDetail;
