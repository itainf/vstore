import reqwest from 'reqwest';
reqwest.ajaxSetup({
    dataFilter: function (response, type) {
        const  formJson=JSON.parse(response);
        if( formJson.resCode===403){
            window.location="/login.html";
        }
        return response;
    }
});
