import React, {useEffect, useRef, useState} from 'react';
import Form from "./Form";
import reqwest from "reqwest";

const  DialogForm = props =>  {

    const [state, setState]  = useState({} );
    const [visible, setVisible]  = useState(false );
    const [msg, setMsg]  = useState({visible:false,mgs:""} );
    const modalRef = useRef(null);

    const {
        onCancel: handleUpdateModalVisible,
        modalVisible,
    } = props;



    function getState(param) {
        if(param){
            setState(param)   ;
        }
        console.log(state);
    }

    function getFormData () {
        let   cols=props.columns ;
        let data={};
        for (let i=0;i<cols.length;i++)
        {
            let dataIndex=cols[i]["dataIndex"];
           // data[dataIndex]= $("#"+props.id+" [name='"+cols[i]["dataIndex"]+"']").val( );
           data[dataIndex]= $(modalRef.current).find("[name="+cols[i]["dataIndex"]+"]").val();

        }
        console.log(data);

        return data;
    }


    const handleSubmit = (value) => {
        if (value) {
            setVisible(true);
            setMsg({visible:false,mgs:""});
            let  newParams={};
            if(props.params){
                newParams= Object.assign(value,props.params);
            }else {
                newParams=value;
            }
            let  params= JSON.stringify(newParams ) ;
            setState(state,{ loading: true });
            if(!props.url){
                props.url="";
            }
            reqwest({
                url: props.url,
                method: 'post',
                contentType: 'application/json',
                type: 'json',
                data:  params

            }).then(data => {
                console.log(data);

                if(data.isSuccess==="true"){
                    $('#'+ props.id ).modal('hide');
                    handleUpdateModalVisible(false, modalVisible);
                    if(props.afterSubmit){
                        props.afterSubmit(params);
                    }
                }else{
                    setVisible(false);
                    setMsg({visible:true,msg:data.result})
                }
            });
        } else {

        }
    };



    const loadForm = (value) => {

        let  newParams={};
        if(props.loadFormParams){

            newParams= Object.assign(value,props.loadFormParams);
            let  params= JSON.stringify(newParams ) ;

            reqwest({
                url: props.loadFormUrl,
                method: 'post',
                contentType: 'application/json',
                type: 'json',
                data:  params

            }).then(data => {

                if(data.isSuccess==="true"){
                    let   cols=props.columns ;
                    for (let i=0;i<cols.length;i++)
                    {
                        $(modalRef.current).find("[name="+cols[i]["dataIndex"]+"]").val(data.result[cols[i]["dataIndex"]] );

                        //$("#"+props.id+" [name='"+cols[i]["dataIndex"]+"']").val( data.result[cols[i]["dataIndex"]] );
                    }
                }else{
                    setMsg({visible:true,msg:data.result})
                }
            });
        }

    };


    useEffect((t) => {
        if(props.modalVisible===true){
            $('#'+props.id).modal('show');
        }else{
            $('#'+props.id).modal('hide');
        }
        loadForm({});
    });

    return (
            <>
                <div className="modal" id={props.id} ref={modalRef} tabIndex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="false"  >

                    <div className="modal-dialog  modal-xl modal-dialog-centered ">
                        <div className="modal-content">
                            <div className="modal-header pt-1 pb-1">
                                <h5 className="modal-title" id="exampleModalLabel">新增</h5>
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true"  onClick={() => handleUpdateModalVisible(false, modalVisible)}>&times;</span>
                                </button>
                            </div>
                            <div className="modal-body">
                                <form>
                                    <div className="row row-cols-2 ">
                                        <Form columns={props.columns}  getState={getState} />
                                    </div>
                                </form>
                            </div>

                            { msg.visible ? (
                                <div className="alert alert-danger" role="alert">
                                    { msg.msg}
                                </div>
                            ) : null}

                            <div className="modal-footer">

                                <button type="button" className="btn btn-secondary btn-sm" data-dismiss="modal"
                                        onClick={() => handleUpdateModalVisible(false, modalVisible)}
                                >取消</button>
                                <button type="button" className="btn btn-primary btn-sm" onClick= {e => {
                                    e.preventDefault();
                                    handleSubmit(getFormData( ) );


                                }}   >

                                    { visible ? (
                                        <span className="spinner-border spinner-border-sm  "     role="status" aria-hidden="true"/>
                                    ) : null}

                                    确认</button>
                            </div>
                        </div>
                    </div>
                </div>

            </>
    );
};

export default  DialogForm






