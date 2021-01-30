
export const  checkParent = (node,id) =>{
    if(node.parentId!==undefined){
        $('#'+id).treeview('checkNode', [ node.parentId, { silent: true } ]);
        let parentNode = $('#'+id).treeview('getParent', node);
        checkParent(parentNode,id);
    }
}

export const  unCheckParent = (node,id) => {
    if(node.parentId!==undefined){
        let parentNode = $('#'+id).treeview('getParent', node);

        let ifUnCheckParent=true;
        let brotherNode= $('#'+id).treeview('getSiblings', node);
        $.each(brotherNode, function (i, child) {
            if(child.state.checked){
                ifUnCheckParent=false;
                return;
            }

        });
        if(ifUnCheckParent){
            $('#'+id).treeview('uncheckNode', [ parentNode, { silent: true } ]);
            if(parentNode.parentId!==undefined){
                unCheckParent(parentNode,id);
            }
        }

    }
}

export const  unCheckChild = (node,id) => {
    if (node.nodes !== null) {
        $.each(node.nodes, function (i, child) {
            $('#'+id).treeview('uncheckNode', [child.nodeId, {silent: true}]);
            if (child.nodes !== null) {
                unCheckChild(child,id);
            }
        });
    }
}

export const  checkChild = (node,id) => {
    if (node.nodes !== null) {
        $.each(node.nodes, function (i, child) {
            $('#'+id).treeview('checkNode', [child.nodeId, {silent: true}]);
            if (child.nodes !== null) {
                checkChild(child,id);
            }
        });
    }
}
