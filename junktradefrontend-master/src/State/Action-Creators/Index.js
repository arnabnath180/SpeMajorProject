export const setAdminLoginStatus=(status)=>{
    return (dispatch)=>{
        dispatch({
            type:"adminstatus",
            payload:status
        })
    }

}

export const setAdminEmail=(email)=>{
    return (dispatch)=>{
        dispatch({
            type:"adminemail",
            payload:email
        })
    }

}

export const setSellerLoginStatus=(status)=>{
    return (dispatch)=>{
        dispatch({
            type:"sellerstatus",
            payload:status
        })
    }

}

export const setSellerEmail=(email)=>{
    return (dispatch)=>{
        dispatch({
            type:"selleremail",
            payload:email
        })
    }

}