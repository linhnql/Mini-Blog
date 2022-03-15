import React, { Component } from "react";
import { Link } from "react-router-dom";
import style from "./styles.module.scss";

export default class About extends Component {
  render() {
    return (
      <div className={style["about"]}>
        <div className={style["about__body"]}>
          <div className={style["about__body__header1"]}>About me</div>
          <p>
            The rich text elements allows you to create and format headings,
            paragraph, blockquotes, image. and video all in one place onstead of
            having to add and format them individually. Just double-click and
            easily create content.
          </p>
          <div className={style["about__body__header2"]}>Something else here</div>
          <p className={style['about__body__last-text']}>
            Yasuo noob, dolor sit amet consectetur adipisicing elit. Doloribus,
            corrupti? Soluta sed quidem unde minus. Facilis eveniet dolor ipsa
            maxime veniam eum assumenda deleniti fuga aperiam corporis commodi,
            cum consequatur?
            <br />
            <br />
            Aphelios ipsum dolor sit amet consectetur adipisicing elit. Similique
            quibusdam quidem, ea excepturi quod, porro, ut id voluptate dicta
            placeat suscipit vero nam voluptas ab distinctio nobis voluptatibus
            nemo facere!
          </p>
          <Link className={style["about__body__touch"]} to="/contacts/send">
            Get in touch
          </Link>
          <div><br /></div>
        </div>
      </div>
    );
  }
}
