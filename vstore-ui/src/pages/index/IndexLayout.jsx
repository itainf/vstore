import React, { useState, useRef,useEffect } from 'react';
import SearchForm from "../../components/From/SearchForm";
import Toolbar from "../../components/Toolbar/toolbar";
import Table from "../../components/Table/index";
import DialogForm from "../../components/From/DialogForm";
import reqwest from 'reqwest';
import BasicLayout from "../../layouts/BasicLayout";
import RoleDialogLayout from "./RoleDialogLayout";

const IndexLayout = formList =>  {

    const [tableData, setTableData]  = useState({ });
    const [loading, setLoading]  = useState(  true);
    const [updateModalVisible, handleUpdateModalVisible] = useState(false);
    const [createModalVisible, handleCreateModalVisible] = useState(false);
    const [roleModalVisible, handleRoleModalVisible] = useState(false);
    const [deleteRoleModalVisible, handleDeleteRoleModalVisible] = useState(false);
    const [id, setId]  = useState(  { id : null});

    const tableRef = useRef(null);

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
            hideInForm:true,
            key: 'id'
        },
        {
            title: '用户名',
            dataIndex: 'userName',
            key: 'userName',
        },
        {
            title: '登录名',
            dataIndex: 'loginName',
            key: 'loginName',
        },
        {
            title: '密码',
            dataIndex: 'password',
            key: 'password',
        },
        {
            title: '联系电话',
            dataIndex: 'phone',
            key: 'phone',
        },
        {
            title: '是否管理员',
            dataIndex: 'isManager',
            key: 'isManager',
            valueType:'select',
            data:[{
                text: '管理员',
                value: '2',
            },{
                text: '普通员工',
                value: '3',
            }]
        },

        {
            title: '账号类型',
            dataIndex: 'type',
            key: 'type',
            valueType:'select',
            data:[{
                text: '平台',
                value: '1',
            },{
                text: '租户',
                value: '2',
            },{
                text: '客户',
                value: '3',
            }]
        },
        {
            title: '备注',
            dataIndex: 'remark',
            key: 'remark',
        }
    ];


    const formData =
    {
        "key": 50,
        "userName": "张三1",
        "password": "1231",
        "databaseType": "sqlServer1",
        "tableName": "CcOrder1",
        "domainObjectName": "CcOrder1",
        "basePackage": "com.wj1"
    } ;
    var $checkableTree2;
    useEffect(() => {
        setTimeout(() => {
            fetch({});

        }, 1000);
    }, []);



    const  fetch = (params = {},pageNum=1) => {
        setLoading(true);
        reqwest({
            url: '/vstore/user/queryUser?pageNum='+pageNum,
            method: 'post',
            contentType: 'application/json',
            type: 'json',
            data:  params
        }).then(data => {
            console.log(data);
            setTableData(data);
            setLoading(false);
        });
    };




    const handleSearch = (value) => {
        if (value) {
            console.log(value);
            const  formJson=JSON.stringify(value);
            console.log(formJson);
             fetch(formJson);
        } else {
            fetch({});
        }
    };



    const handleAssignRole = (value) => {
          let treeNodes=  $('#treeview-checkable-01').treeview('getChecked');
          console.log(treeNodes);
    };

    const handleLoadTree =() =>{
        reqwest({
            url: '/vstore/menu/queryTree',
            method: 'post',
            contentType: 'application/json',
            type: 'json',
            data:  {}
        }).then(data => {
            let  $checkableTree = $('#treeview-checkable-01').treeview({
                data: data ,
                levels: 99,
                expandIcon: 'glyphicon plus-square',
                collapseIcon: 'glyphicon dash-square',
                nodeIcon: 'glyphicon file-earmark',
                checkedIcon: 'glyphicon file-earmark-check',
                uncheckedIcon: 'glyphicon  file-earmark',
                selectedBackColor: '#bae7ff',
                expanded:true,
                showIcon: false,
                showCheckbox: true,
                onNodeSelected: function(event, node) {
                    $checkableTree.treeview('getChecked');
                    $checkableTree2=$checkableTree;
                },
                onNodeChecked: function(event, node) {
                    console.log(node);
                    console.log('<p>' + node.text + ' was checked</p>');

                },
                onNodeUnchecked: function (event, node) {
                    console.log('<p>' + node.text + ' was unchecked</p>');

                }
            });

        });
    };

    const toolbar=[{
        title: '新增用户',
        type:'modal',
        handler:function () {
            handleCreateModalVisible(true);
        }
    },{
        title: '编辑用户',
        type:'modal',
        handler:function () {
            handleUpdateModalVisible(true);
            let val;
            $(tableRef.current).find("input[type=checkbox]") .each(function () {
                 if($(this).prop("checked")){
                     val=$(this).val();
                 }
            });
            setId({id:val});
        }
    },{
        title: '删除用户',
        type:'modal',
        handler:function () {

            let val;
            $(tableRef.current).find("input[type=checkbox]") .each(function () {
                if($(this).prop("checked")){
                    val=$(this).val();
                }
            });
            setId({id:val});

            reqwest({
                url: '/vstore/user/deleteUser',
                method: 'post',
                contentType: 'application/json',
                type: 'json',
                data: JSON.stringify({id:val})
            }).then(data => {
                fetch({});
            });
        }
    },{
        title: '分配角色',
        type:'modal',
        handler:function () {

            let val;
            $(tableRef.current).find("input[type=checkbox]") .each(function () {
                if($(this).prop("checked")){
                    val=$(this).val();
                }
            });
            console.log(val);
            setId({id:val});


            console.log(id["id"]);
            console.log(id.id);
            handleRoleModalVisible(true);
        }
    },{
        title: '取消分配',
        type:'modal',
        handler:function () {

            let val;
            $(tableRef.current).find("input[type=checkbox]") .each(function () {
                if($(this).prop("checked")){
                    val=$(this).val();
                }
            });

            setId({id:val});
            handleDeleteRoleModalVisible(true);
        }
    } ];




    return (
        <BasicLayout title={"系统管理->用户管理"} >
            <SearchForm  columns={columns} onSearch={handleSearch} dataSource={formData} />
            <Toolbar toolbar={toolbar} tableRef={tableRef} />
            <Table loading={loading} load={fetch} pageBar={true} dataSource={tableData} columns={columns} tableRef={tableRef} />

            {
                roleModalVisible === true &&
                <RoleDialogLayout
                    userId={id }
                    modalVisible={roleModalVisible}
                    ifCancel={false}
                    onCancel={() => {
                        handleRoleModalVisible(false);
                    }}

                />
            }

            {
                deleteRoleModalVisible === true &&
                <RoleDialogLayout
                    ifCancel={true}
                    userId={id }
                    modalVisible={deleteRoleModalVisible}
                    onCancel={() => {
                        handleDeleteRoleModalVisible(false);
                    }}

                />
            }


            {
                createModalVisible === true &&
                <DialogForm id="addModal"
                            modalVisible={createModalVisible}
                            onCancel={() => {
                                handleCreateModalVisible(false);
                            }}
                            columns={columns}
                            afterSubmit={
                                () => {
                                    handleSearch({});
                                }
                            }
                            url="/vstore/user/addUser"
                />
            }
            {
                updateModalVisible ===true &&
                <DialogForm id="updateModal"
                            onCancel={() => {
                                handleUpdateModalVisible(false);
                            }}
                            columns={columns}
                            modalVisible={updateModalVisible}
                            afterSubmit={
                            () => {
                                handleSearch({});
                            }
                            }
                            params={id}
                            loadFormParams={id}
                            loadFormUrl="/vstore/user/queryUserById"
                            url="/vstore/user/updateUser"
                />
            }
        </BasicLayout>

    );
};

export default IndexLayout
