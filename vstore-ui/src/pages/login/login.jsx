import React, {useState} from 'react'
import { render } from 'react-dom'
import reqwest from "reqwest";



const Login = formList =>  {

const [state, setState]  = useState( "");
const [loginForm, setLoginForm]  = useState( {});

const  loginSubmit = (params = {}) => {
    console.log(params);

    reqwest({
        url: '/vstore/login',
        method: 'post',
       // contentType: 'application/json',
        type: 'json',
        data:  params

    }).then(data => {
        console.log(params);
        console.log(data);
        // const  formJson=JSON.parse(data);
        if(data.resCode===200){
            window.location="/index.html";
        }else{
          setState(data.result)
            console.log(state);
        }

    });
};


    const  handleInputChange = (event) => {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        console.log(value);

        setLoginForm(Object.assign(loginForm,  {
                [name]: value
            }
        ));

        console.log(loginForm);
    };


return(
    <>
        <div className="text-center mb-4">
                <h1 className="h3 mb-3 font-weight-normal">欢迎使用系统</h1>
                <div  className="text-danger"> {state}</div>
        </div>

        <div className="form-label-group">
            <input type="email" id="inputEmail" className="form-control" name="username" onChange={handleInputChange} placeholder="Email address" required autoFocus/>
                <label htmlFor="inputEmail">Email address</label>
        </div>

        <div className="form-label-group">
            <input type="password" id="inputPassword" name="password" className="form-control" onChange={handleInputChange} placeholder="Password" required/>
                <label htmlFor="inputPassword">Password</label>
        </div>


        <button className="btn btn-lg btn-primary btn-block" type="submit"
                onClick= {e => {
                    e.preventDefault();
                    loginSubmit( loginForm);
                }}

        >登录</button>
        <p className="mt-5 mb-3 text-muted text-center">&copy; 2017-2020</p>
    </>
);

};


export default Login
