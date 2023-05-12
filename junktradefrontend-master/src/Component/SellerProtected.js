import React, { useEffect } from 'react'
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

export default function ({ Component }) {
    const navigate=useNavigate();
    const status=useSelector(state=>state.sellerStatus)
    useEffect(()=>{
        if(status===false){
          navigate('/seller/login');
        }
    });
  return (
    <div>
        <Component/>
    </div>
  )
}
