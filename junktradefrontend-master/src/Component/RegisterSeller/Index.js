import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import SellerServices from '../../Services/SellerServices';
import './Index.css'
export default function () {
  const [email,setEmail]=useState('');
  const [fname,setFname]=useState('');
  const [lname,setLname]=useState('');
  const [password,setPassword]=useState('');
  const [confirmPassword,setConfirmPassword]=useState('');
  const [phNumber,setPhNumber]=useState('');
  const navigate=useNavigate();
  const handleSubmit = (e)=>{
    e.preventDefault();
    SellerServices.saveSeller({
      "email":email,
      "fname":fname,
      "lname":lname,
      "password":password,
      "phoneNumber":phNumber
    }).then((res)=>{
      console.log(res);
      setEmail('');
      setFname('');
      setLname('');
      setConfirmPassword('');
      setPassword('');
      setPhNumber('');
      alert("Successfully registered");
      navigate('/');
    })
  }



  return (
    <div className='register-container'>
    <nav className="navbar navbar-dark bg-dark sell">
      <span className='text-white logo'>Junk Trade</span>
    </nav>
    <div className='container'>
        <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label htmlFor="fname" className='register-label'>First Name</label>
                <input type="text" value={fname} className="form-control" onChange={(e)=> {setFname(e.target.value)}} placeholder="Enter first name" required/>
            </div>
            <div className="form-group">
                <label htmlFor="lname" className='register-label'>Last Name</label>
                <input type="text" value={lname} className="form-control" onChange={(e)=> {setLname(e.target.value)}} placeholder="Enter last name"/>
            </div>
            <div className="form-group">
                <label htmlFor="exampleInputEmail1" className='register-label'>Email</label>
                <input type="email" className="form-control" onChange={(e)=> {setEmail(e.target.value)}} value={email} aria-describedby="emailHelp" placeholder="Enter email" required/>
                <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div className="form-group">
                <label htmlFor="lname" className='register-label'>Phone Number</label>
                <input type="tel" pattern="[0-9]{10}" value={phNumber} className="form-control"
                onChange={(e)=> {setPhNumber(e.target.value);}} placeholder="Enter 10 digit phone number" required/>
            </div>
            <div className="form-group">
                <label htmlFor="exampleInputPassword1" className='register-label'>Password</label>
                <input type="password" value={password} className="form-control" onChange={(e)=> {setPassword(e.target.value)}} placeholder="Password" required/>
            </div>
            <div className="form-group">
                <label htmlFor="exampleInputPassword2" className='register-label'>Confirm Password</label>
                <input type="password" className="form-control" onChange={(e)=> {setConfirmPassword(e.target.value)}} value={confirmPassword} placeholder="Password" required/>
            </div>
            
            <button type="submit" className="btn btn-primary" style={{marginTop:'10px'}}>Submit</button>
        </form>
    </div>
    </div>
  )
}
