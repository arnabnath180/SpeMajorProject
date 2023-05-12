import React from 'react'
import { useNavigate } from 'react-router-dom';
import './Index.css'
import { useDispatch, useSelector } from 'react-redux';
import { actionCreators }  from '../../State/Index.js';
import { bindActionCreators } from 'redux';

export default function () {
    const dispatch=useDispatch();
    const actions=bindActionCreators(actionCreators,dispatch);
    const status=useSelector(state=>state.sellerStatus);
    const Email=useSelector(state=>state.sellerEmail);
    const navigate=useNavigate();
  return (
    <div>
        <nav className="navbar navbar-dark bg-dark sell">
            <span className='text-white logo'>Junk Trade</span>
            <div>
            <span style={{color:'white', fontSize:'20px', marginRight:'5px'}}>Welcome {Email}</span>
            <button type="button" class="btn btn-outline-light" style={{marginRight:'10px'}}
            onClick={()=>{
                localStorage.removeItem('sellerauthenticate');
                localStorage.removeItem('sellerLoginStatus');
                localStorage.removeItem('sellerEmail');
                actions.setSellerEmail(null);
                actions.setSellerLoginStatus(false);
                navigate('/');
            }}>Logout</button>
            </div>
        </nav>
        <div className='login-container'>
            <div className='card login-card shadow-lg p-3 mb-5 bg-white rounded'>
                <button type="button" class="btn btn-secondary btn-lg sellerbtn"
                onClick={()=>{navigate('/seller/dashboard')}}>Sell Scrap</button>
                <button type="button" class="btn btn-secondary btn-lg sellerbtn" 
                onClick={()=>{navigate('/seller/history')}}>Scrap History</button>
            </div>
        </div> 
    </div>
  )
}
