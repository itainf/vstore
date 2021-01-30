import React from 'react';

const Toolbar = props =>  {

    return (
        <div className="toolbar bg-white    mt-1 mb-1   pb-1">
            <div className="form-group  mb-0   ">
                {
                    props.toolbar.map( (childItem,index) => (
                        <button type="button" key={index} className="btn btn-primary btn-sm" data-toggle="modal" data-target= {childItem.dataTarget} onClick={childItem.handler} >{childItem.title}</button>
                    ))
                }
            </div>
        </div>
    );
};
export default Toolbar






