import React, { useState } from 'react';
import './index.css'


const Left = props =>  {

    return (

        <>
            <ul className="nav flex-column fist-nav sidebar " >
                <li className="nav-item sidebar-item">
                    <a className="nav-link sidebar-link sidebarActive " href="#">
                        <span id="1">系统管理</span>
                    </a>
                    <ul className="sidebar nav flex-column  " >
                        <li className="nav-item sidebar-item">
                            <a className="nav-link  " href="/index.html">
                                <span id="2">用户管理</span>
                            </a>
                        </li>
                        <li className="nav-item sidebar-item">
                            <a className="nav-link " href="/role.html">
                                <span id="3">角色管理</span>
                            </a>
                        </li>
                        <li className="nav-item sidebar-item">
                            <a className="nav-link " href="/permission.html">
                                <span id="4">权限管理</span>
                            </a>
                        </li>
                        <li className="nav-item sidebar-item">
                            <a className="nav-link " href="/resource.html">
                                <span id="5">资源管理</span>
                            </a>
                        </li>
                        <li className="nav-item sidebar-item">
                            <a className="nav-link " href="/menu.html">
                                <span id="6">菜单管理</span>
                            </a>
                        </li>

                    </ul>
                </li>

            </ul>
        </>
    );
};
export default Left

