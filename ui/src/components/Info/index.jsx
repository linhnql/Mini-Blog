import React, { Component } from "react";
import style from "./styles.module.scss";

export default class Info extends Component {
  render() {
    return (
      <div className={style["info"]}>
        <div className={style["info__body"]}>
          <div className={style["info__body--image"]} />
          <p className={style["info__body--intro"]}>
            Denali is a simple responsive blog template. Easily add new pots
            using the Editor or change layout and design using the Designer.
          </p>
          <hr className={style['hr']}/>
          <p className={style["info__body--featured"]}>Featured Posts:</p>
          <p className={style["info__body--post"]}>
            According a funnily until pre-set or arrogant well cheerful <br />
            <br />
            Overlaid the jeepers uselessly much excluding
          </p>
          <hr className={style['hr']}/>
          <div className={style["info__body--social"]}>
            <a href="https://facebook.com" target="_blank" rel="noreferrer">
              <i className="fab fa-facebook-square" />
            </a>
            <a href="https://instagram.com" target="_blank" rel="noreferrer">
              <i className="fab fa-instagram-square" />
            </a>
            <a href="https://twitter.com" target="_blank" rel="noreferrer">
              <i className="fab fa-twitter" />
            </a>
            <a href="https://linkedin.com" target="_blank" rel="noreferrer">
              <i className="fab fa-linkedin-in" />
            </a>
            <p> BUILT WITH WEBFLOW</p>
          </div>
        </div>
      </div>
    );
  }
}
