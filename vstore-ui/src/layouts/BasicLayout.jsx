import React, { useState, useRef,useEffect } from 'react';
import Navbar from "../components/Head";
import Left from "../components/Left";



const BasicLayout = (props) =>  {

return (
    <div>
        <Navbar />
        <div className="row ml-0 mr-0">
            <div className="bg-white pr-0  bg-dark  " style={{width:180}} />
            <div className=" my-bg-dark  pr-0 sidebar-fixed   " style={{width:180}} id="leftNavbars">
                <Left />
            </div>
            <div className="col  pl-2  ">
                <div className="search-from p-2 bg-white justify-content-center" style={{    marginLeft:-10 ,
                    marginBottom: 5  }}>
                    {props.title}
                </div>
                {props.children}
            </div>
        </div>

    </div>
);

}

export default  BasicLayout
