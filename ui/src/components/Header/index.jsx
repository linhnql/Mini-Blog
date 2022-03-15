import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import Info from "../Info";
import About from "../About";
import style from "./styles.module.scss";
import Contact from "../ContactSend";
import BlogDetail from "../BlogDetail";
import BlogList from "../Blog/BlogList";
import AddBlog from "../BlogDetail/AddBlog/index";
import EditBlog from "../BlogDetail/EditBlog/index";
import ContactList from "../Contact/ContactList";
import ContactDetail from "../ContactDetail";
import ContactReply from "../ContactDetail/ContactReply";

export default class Header extends Component {
  render() {
    return (
      <div className={style['body']}>
        <Info />
        <Router>
          <div className={style["header"]}>
            <Link className={style["header__name"]} to="/">
              Denali
            </Link>
            <div>
              <ul className={style["header__choice"]}>
                <li>
                  <Link className={style["header__choice__act"]} to="/">
                    Home
                  </Link>
                </li>
                <li>
                  <Link className={style["header__choice__act"]} to="/about">
                    About
                  </Link>
                </li>
                <li>
                  <Link className={style["header__choice__act"]} to="/contacts/send">
                    Contact
                  </Link>
                </li>
                <li>
                  <Link className={style["header__choice__act"]} to="/contacts">
                    Contact List
                  </Link>
                </li>
              </ul>
            </div> 
          </div>
          <Switch>
            <Route exact path="/about" component={About} />
            <Route exact path="/contacts/send" component={Contact} />
            <Route exact path="/contacts" component={ContactList} />
            <Route exact path="/contacts/:id" component={ContactDetail} />
            <Route exact path="/contacts/reply/:id" component={ContactReply} />
            <Route exact path="/blogs/:id" component={BlogDetail} />
            <Route exact path="/blogs" component={AddBlog} />
            <Route exact path="/blogs/edit/:id" component={EditBlog} />
            <Route path="/" component={BlogList} />
          </Switch>
        </Router>
      </div>
    );
  }
}
