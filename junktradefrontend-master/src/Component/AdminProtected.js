import React, { useEffect } from 'react'
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

export default function ({ Component }) {
    const navigate=useNavigate();
    const status=useSelector(state=>state.adminStatus)
    useEffect(()=>{
        if(status===false){
          navigate('/admin/login');
        }
    });

  return (
    <div>
      <Component/>
    </div>
  )
}
