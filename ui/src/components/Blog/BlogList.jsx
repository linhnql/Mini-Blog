import React, { useEffect, useState, useMemo } from "react";
import Blog from "./index";
import axios from "axios";
import Pagination from "../Pagination/Pagination";
import style from "./styles.module.scss";
import { Link } from "react-router-dom";
import LazyLoad from "react-lazyload";

const Spinner = () => (
  <div className={style["spinner"]}>
    <svg
      width="80"
      height="80"
      viewBox="0 0 100 100"
      preserveAspectRatio="xMidYMid"
    >
      <circle
        cx="50"
        cy="50"
        fill="none"
        stroke="#49d1e0"
        strokeWidth="10"
        r="35"
        strokeDasharray="164.93361431346415 56.97787143782138"
        transform="rotate(275.845 50 50)"
      >
        <animateTransform
          attributeName="transform"
          type="rotate"
          calcMode="linear"
          values="0 50 50;360 50 50"
          keyTimes="0;1"
          dur="1s"
          begin="0s"
          repeatCount="indefinite"
        />
      </circle>
    </svg>
  </div>
);

const BlogList = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const [blogs, setBlogs] = useState([]);
  const [pageSize, setPageSize] = useState(10);

  const baseURL = `http://localhost:8082/miniblog/backend/v1/blogs`;
  const headers = {
    apikey: "2347edfd-c55c-4f59-96ee-600492f904f3",
  };
  const params = {
    pageSize: pageSize
  };

  const PageSize = pageSize;
  
  useEffect(() => {
    axios.get(baseURL, { headers }).then((response) => {
      console.log(response.data)
      setBlogs(response.data);
    });
  }, [currentPage]);
  
  const currentData = useMemo(() => {
    const firstPageIndex = (currentPage - 1) * PageSize;
    const lastPageIndex = firstPageIndex + PageSize;
    return blogs.slice(firstPageIndex, lastPageIndex);
  }, [blogs]);
  
  return (
    <div className={style["list"]}>
      {currentData.map((blog) => (
        <LazyLoad
          key={blog.id}
          height={100}
          offset={[-100, 100]}
          placeholder={<Spinner />}
        >
          <Blog key={blog.id} blog={blog} />
        </LazyLoad>
      ))}
      <Pagination
        className="pagination-bar"
        currentPage={currentPage}
        totalCount={blogs.length}
        pageSize={PageSize}
        onPageChange={(page) => setCurrentPage(page)}
      />
      <Link className={style["button"]} to="/blogs">
        <span className={style["tooltip"]}>Add post</span>
      </Link>
    </div>
  );
};

export default BlogList;
