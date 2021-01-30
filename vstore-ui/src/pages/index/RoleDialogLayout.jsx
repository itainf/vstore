import React, { useState, useRef,useEffect } from 'react';
import SearchForm from "../../components/From/SearchForm";
import Table from "../../components/Table";
import reqwest from 'reqwest';
import Dialog from "../../components/From/Dialog";

const RoleDialogLayout = (props) =>  {

    const [tableData, setTableData]  = useState({ });
    const [loading, setLoading]  = useState(  true);
    const tableRef = useRef(null);

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
            hideInForm:true,
            key: 'id'
        },
        {
            title: '角色编码',
            dataIndex: 'roleCode',
            key: 'roleCode',
        },
        {
            title: '角色名称',
            dataIndex: 'roleName',
            key: 'roleName',
        },
        {
            title: '备注',
            dataIndex: 'remark',
            key: 'remark',
        },

        {
            title: '状态',
            dataIndex: 'state',
            key: 'state',
            valueType:'select',
            data:[{
                text: '启用',
                value: '1',
            },{
                text: '停用',
                value: '2',
            }]
        }


    ];


    useEffect(() => {

            fetch( {userId: props.userId.id } );
    }, []);


    const  fetch = (params = {},pageNum=1) => {
        setLoading(true);

        if(props.ifCancel){
            reqwest({
                url: '/vstore/user/queryCancelUserRole?pageNum='+pageNum,
                method: 'post',
                contentType: 'application/json',
                type: 'json',
                data: JSON.stringify(params)
            }).then(data => {
                console.log(data);
                setTableData(data);
                setLoading(false);
            });
        }else{
            reqwest({
                url: '/vstore/user/querySaveUserRole?pageNum='+pageNum,
                method: 'post',
                contentType: 'application/json',
                type: 'json',
                data:  JSON.stringify(params)
            }).then(data => {
                console.log(data);
                setTableData(data);
                setLoading(false);
            });
        }


    };



    const handleSearch = (value) => {
        if (value) {
             value["userId"]=props.userId.id;
             fetch(value);
        } else {

        }
    };

    const saveUserRole  = (params = {}) => {

        let roleId
        $(tableRef.current).find("input[type=checkbox]").each(function () {
            if($(this).prop("checked")){
                roleId=$(this).val();
                console.log(roleId);
            }
        });


        if(props.ifCancel) {
            reqwest({
                url: '/vstore/user/deleteUserRole',
                method: 'post',
                contentType: 'application/json',
                type: 'json',
                data: JSON.stringify({userId: props.userId.id, roleId: roleId})
            }).then(data => {
                setTableData(data);
                setLoading(false);
                fetch({userId: props.userId.id});
            });
        }else{
            reqwest({
                url: '/vstore/user/saveUserRole',
                method: 'post',
                contentType: 'application/json',
                type: 'json',
                data: JSON.stringify({userId: props.userId.id, roleId: roleId})
            }).then(data => {
                setTableData(data);
                setLoading(false);
                fetch({userId: props.userId.id});
            });
        }


    };

    return (
        <>
            <Dialog id="roleDialogModal"
                    modalVisible={props.modalVisible}
                    onCancel={() => {
                        props.onCancel();
                    }}
                    title="资源"
                    columns={columns}
                    handleSubmit={ saveUserRole }
                    url="/vstore/user/saveUserRole"
            >
                <div className="row">
                    <div className="col">
                        <SearchForm  columns={columns} onSearch={handleSearch}   />
                        <Table loading={loading} load={fetch} pageBar={true} dataSource={tableData} columns={columns} tableRef={tableRef} />
                    </div>
                </div>
            </Dialog>
        </>
    );
};

export default RoleDialogLayout
