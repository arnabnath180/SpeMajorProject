const adminEmailReducer=(state=localStorage.getItem('adminEmail')
    , action)=>{
    if(action.type==="adminemail"){
        return action.payload;
    }
    else{
        return state;
    }
}

export default adminEmailReducer;