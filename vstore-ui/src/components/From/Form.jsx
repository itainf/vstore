import React, {useEffect, useState} from 'react';

const  Form = props =>  {
    let initialState={};
    const [state, setState]  = useState(initialState );

    useEffect((t) => {
        console.log("Form----->");
        console.log(props.dataSource);
        if(props.dataSource){
            if(props.getState){
                props.getState(props.dataSource);
                setState( props.dataSource);
            }
        }
    });
    let className= "pr-0  col-sm-3 col-form-label col-form-label-sm text-right ";
    if(props.lableStyle){
          className= "pr-0 "+ props.lableStyle +" col-form-label col-form-label-sm text-right ";
    }



    const  handleInputChange = (event) => {


    };



        function build(item,index) {
            let html="" ;

            if(item.hideInForm){
                return false;
            }

            if(item.valueType === "select"){
                html=  <select name= {item.dataIndex}      defaultValue={state[item.dataIndex]} className="form-control form-control-sm" onChange={ handleInputChange} >

                    {item.data.map( (item,index) => (
                        <option key={index} value={item.value}>{item.text}</option>
                    ))}

                    </select>
            }else{
                html=   <input type="text"
                               className="form-control form-control-sm"
                               name={item.dataIndex}
                               key={index}
                               placeholder={ "请填写"+ item.title }
                               defaultValue={state[item.dataIndex]  }
                               onChange={ handleInputChange}
                               />
            }
            return (
                <div className="col"    key={index} >
                    <div className="mb-2 row justify-content-end">

                        <label className={className}  >{item.title}</label>
                        <div className="col">
                            {html}
                        </div>
                    </div>
                </div>
            );
        }


    return (
            <>
                {
                    props.columns.map( (item,index) => (  build( item,index ) ))
                }

            </>
    );
};

export default  Form






