const sellerEmailReducer=(state=localStorage.getItem('sellerEmail')
, action)=>{
    if(action.type==="selleremail"){
        return action.payload;
    }
    else{
        return state;
    }
}

export default sellerEmailReducer;