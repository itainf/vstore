import React, {useRef, useState} from 'react';
import Form from './Form';

const SearchForm = props =>  {

    const searchFormRef = useRef(null);

    function setResetTo( ) {
     let   cols=props.columns ;
     let form=$(searchFormRef.current);

     for (let i=0;i<cols.length;i++)
      {
      // $("#"+props.id+" [name='"+cols[i]["dataIndex"]+"']").val("");
        form.find("[name='"+cols[i]["dataIndex"]+"']").val("");
     }
    }

    function getFormData ( ) {
        let   cols=props.columns ;
        let form=$(searchFormRef.current);
        let data={};
        for (let i=0;i<cols.length;i++)
        {
            let dataIndex=cols[i]["dataIndex"];
            data[dataIndex]= form.find("[name='"+cols[i]["dataIndex"]+"']").val();
        }
        return data;
    }


    const [formData, setFormData]  = useState({} );

    let formItem=  <Form columns={props.columns}  dataSource={formData}  lableStyle={props.lableStyle}/>;

    return (
        <form id={props.id} ref={searchFormRef}>
        <div className="search-from  bg-white">
            <div className="row row-cols-4 ">

                {formItem}

                <div className="col  ml-auto" >
                    <div className="mb-2 row justify-content-center">
                        <div className="form-group mb-2  ">
                            <button type="button" className="btn btn-primary btn-sm" onClick= {e => {
                                e.preventDefault();
                                let data=getFormData();
                                props.onSearch(data);

                            }}

                            >查询</button>
                            <button type="button" className="btn btn-primary btn-sm"  onClick= {e => {

                                e.preventDefault();
                                setResetTo();
                            }}>重置</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </form>
    );
};

export default SearchForm






