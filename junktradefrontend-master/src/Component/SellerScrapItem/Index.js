import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import SellerServices from '../../Services/SellerServices';
import { useDispatch, useSelector } from 'react-redux';
import { actionCreators }  from '../../State/Index.js';
import { bindActionCreators } from 'redux';

function Index() {
    
    const [scrapList, setScrapList] = useState([]);
    const location=useLocation();
    const navigate=useNavigate();
    const id=location.state;
    let jwt = null;
    jwt = localStorage.getItem('sellerauthenticate');
    jwt = "Bearer " + jwt;
    const config = {
        headers: {
            'Authorization': jwt
        }
    };
    const dispatch=useDispatch();
    const actions=bindActionCreators(actionCreators,dispatch);
    const status=useSelector(state=>state.sellerStatus);
    const Email=useSelector(state=>state.sellerEmail);
    const fetchData = () => {
        SellerServices.scrapListItem(id,config).then((response) => {
          console.log(response.data);
          let temp=[];
          response.data.map((e,index)=>{
           temp.push(e);
            console.log(temp[index]);
           })
          
            setScrapList(temp);
            console.log(scrapList);
        }).catch((error) => { 
          console.log(error);
        });
        
        }
        
        useEffect(() => {
        
            fetchData();
            
        }, []);

  return (
    <div className='admin-container'>
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
    <div className='container'>
        
        <table className="table">
  <thead className="thead-dark">
    <tr>
      <th scope="col">Scrap Item</th>
      <th scope="col">Item Rate</th>
      <th scope="col">Quantity</th>
    </tr>
  </thead>
  <tbody>
   { scrapList.map((e) =>
    <tr>
      <td>{e.item}</td>
      <td>{e.rate}</td>
      <td>{e.quantity}</td>
     </tr>
    )}
  </tbody>
</table>

</div>


</div>
  )
}

export default Index