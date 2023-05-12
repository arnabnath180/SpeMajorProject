const sellerStatusReducer=(state=(localStorage.getItem('sellerLoginStatus')===null)?false:true
, action)=>{
    if(action.type==="sellerstatus"){
        return action.payload;
    }
    else{
        return state;
    }
}

export default sellerStatusReducer;