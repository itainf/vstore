import React, { useState } from 'react';
import Style from "./index.css"
const Navbar = props =>  {




    return (
        <>
        <nav className={Style.layoutHeader} />
        <nav className="nav  fixed-top  navbar-dark  bg-dark "  style={{ height: 43}}  >

            <a className="navbar-brand mr-md-auto " style={{ width: 162}} href="/">0000</a>
            {/*<button className="navbar-toggler  mr-md-auto " type="button" data-toggle="collapse" data-target="#leftNavbars"*/}
            {/*        aria-controls="navbarsExample01" aria-expanded="false" aria-label="Toggle navigation">*/}
            {/*    <span className="navbar-toggler-icon"/>*/}
            {/*</button>*/}
            <div className="row ">
                    <div className="nav-link  text-white"  >您好，欢迎使用！</div>
                    <a className="nav-link text-white" href="/vstore/logout">退出</a>
            </div>
        </nav>

      </>
    );
};
export default Navbar

