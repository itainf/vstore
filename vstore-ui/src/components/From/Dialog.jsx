import React, {useEffect, useRef, useState} from 'react';


const  Dialog  = props =>  {

    const [state, setState]  = useState({} );
    const [visible, setVisible]  = useState(false );
    const [msg, setMsg]  = useState({visible:false,mgs:""} );
    const modalRef = useRef(null);

    const {
        onCancel: handleUpdateModalVisible,
        modalVisible,
        load,
        title,
    } = props;

    useEffect((t) => {
        if(props.modalVisible===true){
            $('#'+props.id).modal('show');
        }else{
            $('#'+props.id).modal('hide');
        }
      if(load){
          load();
      }

    });

    return (
            <>
                <div className="modal"   id={props.id} ref={modalRef} tabIndex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="false"  >

                    <div className="modal-dialog  modal-xl modal-dialog-centered ">
                        <div className="modal-content">
                            <div className="modal-header pt-1 pb-1">
                                <h5 className="modal-title" id="exampleModalLabel">{props.title ? props.title :"弹出层" }</h5>
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true"  onClick={() => handleUpdateModalVisible(false, modalVisible)}>&times;</span>
                                </button>
                            </div>
                            <div className="modal-body">
                                        {props.children}
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
                                    props.handleSubmit();


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

export default  Dialog






