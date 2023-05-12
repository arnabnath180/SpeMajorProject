const adminStatusReducer=(state=(localStorage.getItem('adminLoginStatus')===null)?false:true
    , action)=>{
    if(action.type==="adminstatus"){
        return action.payload;
    }
    else{
        return state;
    }
}

export default adminStatusReducer;