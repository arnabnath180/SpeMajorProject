import React, { useState } from 'react'
import AdminServices from '../../Services/AdminServices';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { bindActionCreators } from 'redux';
import { actionCreators } from '../../State/Index';

export default function () {
  const [email,setEmail]=useState('');
  const [password,setPassword]=useState('');
  const dispatch=useDispatch();
  const actions=bindActionCreators(actionCreators,dispatch);
  const status=useSelector(state=>state.adminStatus);
  const Email=useSelector(state=>state.adminEmail);
  const navigate=useNavigate();
  const handleSubmit = (e)=>{
    e.preventDefault();
    AdminServices.loginAdmin(
      {
        "email":email,
        "password":password
      }
    ).then((res)=>{
      console.log(res);
      localStorage.setItem("adminauthenticate", res.data.admintoken);
      actions.setAdminLoginStatus(true);
      actions.setAdminEmail(email);

      localStorage.setItem('adminLoginStatus',true);
      localStorage.setItem('adminEmail',email);
      navigate('/admin/dashboard');
    }).catch((error)=>{
        alert("Bad Credentials");
    })
  }
  return (
    <>
    <nav className="navbar navbar-dark bg-dark sell">
      <span className='text-white logo'>Junk Trade</span>
    </nav>
    <div className='login-container'>
      <div className='card login-card shadow-lg p-3 mb-5 bg-white rounded'>
      <form onSubmit={handleSubmit}>
        <div className='row'>
        <div className='col-sm-1'></div>
        <div className="form-group col-sm-10">
          <label htmlFor="exampleInputEmail1">Email</label>
          <input type="email" className="form-control" onChange={(e)=> {setEmail(e.target.value)}} value={email} aria-describedby="emailHelp" placeholder="Enter email" required/>
          <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div className='col-sm-1'></div>
        </div>
        <div className='row'>
        <div className='col-sm-1'></div>
        <div className="form-group col-sm-10">
          <label htmlFor="exampleInputPassword1">Password</label>
          <input type="password" value={password} className="form-control" onChange={(e)=> {setPassword(e.target.value)}} placeholder="Password" required/>
        </div>
        <div className='col-sm-1'></div>
        </div>
        <div className='row'>
        <div className='col-sm-1'></div>
        <div className='col-sm-10'>
        <button type="submit" className="btn btn-primary" style={{marginTop:'10px'}}>Submit</button>
        </div>
        <div className='col-sm-1'></div>
        </div>
      </form>
      </div>
    </div>
    </>
  )
}
