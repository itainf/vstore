import React from 'react';
import  Style  from './index.css';
import PropTypes from 'prop-types'

const Table = props =>  {

    if(!props.dataSource){
        props.dataSource={};
    }
    if(!props.dataSource.list){
        props.dataSource.list=[]
    }


    const {
        loading,
    } = props;

    let pageNum=  props.dataSource.pageNum;
    let totalPages=  props.dataSource.pages;
    let prePageNum=pageNum-3>0?pageNum-3:1;
    let nextPageNum=pageNum+3<=totalPages?pageNum+3:totalPages;
    //总页数

    let page=[];

    for(let i=prePageNum;i<=nextPageNum;i++){

        if(i!==pageNum){
            page.push(
                <li  key={i} className="page-item">
                    <a className="page-link" href="#" onClick= {e => {
                        e.preventDefault();
                        props.load(JSON.stringify({}),i )
                    }}>{i}
                    </a>

                </li>
            )
        }else {
            page.push(
                <li  key={i} className="page-item active" aria-current="page">
              <span className="page-link">
                {i}
                <span className="sr-only">(current)</span>
              </span>
             </li>
            )
        }
    }




    return (
        <div ref={props.tableRef} className=" bg-white     mb-1   pb-1  " >

            <div className={Style.tableWrapper}>

                <div className={Style.spinNestedLoading}>

                    {  loading ===true &&

                        <div className={Style.spinDot}>
                        <div className="d-flex justify-content-center position-absolute">
                        <div className="spinner-border text-primary " role="status">
                        <span className="sr-only">Loading...</span>
                        </div>
                        </div>
                        </div>
                    }


                <div className={ loading ===true ? `${ Style.spinContainer}  ${ Style.spinBlur} ` :''} >


                <table className="table  table-sm  ">
                    <thead className=" thead-light  ">
                    <tr>
                        <th   scope="col"> <input type="checkbox" aria-label="Checkbox for following text input"/></th>
                            {
                            props.columns.map( (item,index) => (  <th key={index} scope="col">{item.title}</th>))
                        }
                    </tr>
                    </thead>
                    <tbody>
                        {
                            props.dataSource.list.map((item,index) => (
                                    <tr key={index}

                                        onClick= {e => {

                                            const checked=$(e.target).parent().find("input[type='checkbox']").is(':checked');
                                            console.log(checked)  ;
                                        }

                                        }>
                                        <td> <input type="checkbox" aria-label="Checkbox for following text input" value={item["id"]}  /> </td>
                                        {
                                            props.columns.map( (childItem,index) => (
                                               childItem.valueEnum  ?
                                                   <td key={index}>{  item[childItem.dataIndex] ? childItem.valueEnum[item[childItem.dataIndex] ]["text"] :""}   </td> :
                                                   <td key={index}>{   item[ childItem.dataIndex ] }    </td>


                                            ))
                                        }
                                    </tr>
                                )
                            )
                        }
                    </tbody>
                </table>
                { (props.pageBar  ) &&
                <nav aria-label="Page navigation example ">
                    <ul className="pagination justify-content-end  mb-0">

                        {
                            (props.dataSource.prePage !== 0) &&



                            <li className="page-item">
                                <a className="page-link" href="#" aria-label="Previous" onClick= {e => {
                                    e.preventDefault();
                                    props.load(JSON.stringify({}), pageNum -1 );
                                }} >
                                    <span aria-hidden="true">上一页</span>
                                </a>
                            </li>
                        }

                        {page}

                        {
                            (props.dataSource.nextPage !==0 )  &&
                            <li className="page-item">
                            <a className="page-link" href="#" aria-label="Next" onClick= {e => {
                                e.preventDefault();
                                props.load(JSON.stringify({}), pageNum +1 )
                            }} >
                            <span aria-hidden="true">下一页</span>
                            </a>
                            </li>
                        }


                    </ul>
                </nav>
                }
                </div>
                </div>
            </div>


        </div>
    );
};


Table.propTypes = {
    props:PropTypes.shape({
      prePage: PropTypes.Number,
      nextPage:PropTypes.Number,
      pageBar:PropTypes.bool,
      tableRef: PropTypes.element,
      dataSource:PropTypes.array
    })
};

export default Table






