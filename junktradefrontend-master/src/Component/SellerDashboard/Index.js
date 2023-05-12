import React, { useState } from 'react'
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import TimePicker from 'react-bootstrap-time-picker';

import './Index.css';
import SellerServices from '../../Services/SellerServices';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { actionCreators }  from '../../State/Index.js';
import { bindActionCreators } from 'redux';

export default function () {
  let categories=[
    {'category':'Mix Waste',
      'items':[{
        'item':'CPU',
        'price':150,
        'qty':'/PCs'
        },
        {
        'item':'Fridge',
        'price':500,
        'qty':'/PCs'
        },
        {
        'item':'Microwave Oven',
        'price':200,
        'qty':'/PCs'
        }
      ]
    },
    {'category':'Paper',
    'items':[{
      'item':'Old Newspaper',
      'price':15,
      'qty':'/kg'
      },
      {
      'item':'Kraft',
      'price':5,
      'qty':'/kg'
      },
      {
      'item':'Books',
      'price':10,
      'qty':'/kg'
      }
    ]
    },
    {'category':'Old Clothes',
    'items':[{
      'item':'Mix Clothes',
      'price':5,
      'qty':'/kg'
      }
    ]
    },
    {'category':'Metals',
    'items':[{
      'item':'Iron',
      'price':20,
      'qty':'/kg'
      },
      {
      'item':'Tin',
      'price':15,
      'qty':'/kg'
      },
      {
      'item':'Steel',
      'price':30,
      'qty':'/kg'
      }
    ]
    },
    {'category':'Plastic',
    'items':[{
      'item':'Mix Plastic',
      'price':7,
      'qty':'/kg'
      },
      {
      'item':'Tyre',
      'price':5,
      'qty':'/kg'
      }
    ]
    }
  ];
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
  const navigate=useNavigate();
  const [date, setDate] = useState();
  const [mixWaste,setMixWaste]=useState([false,false,false]);
  const [paper,setPaper]=useState([false,false,false]);
  const [oldClothes,setOldClothes]=useState([false]);
  const [metals,setMetals]=useState([false,false,false]);
  const [plastic,setPlastic]=useState([false,false]);
  const [mixWasteOrder,setMixWasteOrder]=useState([0,0,0]);
  const [paperOrder,setPaperOrder]=useState([0,0,0]);
  const [oldClothesOrder,setOldClothesOrder]=useState([0]);
  const [metalsOrder,setMetalsOrder]=useState([0,0,0]);
  const [plasticOrder,setPlasticOrder]=useState([0,0]);
  const [time,setTime]=useState();
  const [addressLine,setAddressLine]=useState();
  const [state,setState]=useState();
  const [district,setDistrict]=useState();
  const [pinCode,setPincode]=useState();
  
  
 



  const handleSubmit=async(e)=>{
    e.preventDefault();
    let order={};
    const today = new Date();
    order["pickupDate"]=date;
    console.log(today);
    console.log(date);
    console.log(date<today);
    if(date<=today){
      alert("Date should be greater than current date");
    }
    else{
    order["pickupTime"]=time;
    order["addressLine"]=addressLine;
    order["district"]=district;
    order["state"]=state;
    order["pinCode"]=pinCode;
    order['scrapCategory']="Mix Waste";
    order['scrapItemList']=[];
    mixWasteOrder.map((elem, index)=>{
      if(elem!=0){
        let item={};
        item["item"]=categories[0].items[index].item;
        item["rate"]=categories[0].items[index].price;
        item["quantity"]=elem;
        order["scrapItemList"].push(item);
      }
    });

    if(order['scrapItemList'].length !== 0){
      let res=await SellerServices.addOrders(order, config);
      console.log(res);
    };

    order['scrapCategory']="Paper";
    order['scrapItemList']=[];
    paperOrder.map((elem, index)=>{
      if(elem!=0){
        let item={};
        item["item"]=categories[0].items[index].item;
        item["rate"]=categories[0].items[index].price;
        item["quantity"]=elem;
        order["scrapItemList"].push(item);
      }
    });

    if(order['scrapItemList'].length !== 0){
      let res=await SellerServices.addOrders(order, config);
      console.log(res);
    };

    order['scrapCategory']="Old Clothes";
    order['scrapItemList']=[];
    oldClothesOrder.map((elem, index)=>{
      if(elem!=0){
        let item={};
        item["item"]=categories[0].items[index].item;
        item["rate"]=categories[0].items[index].price;
        item["quantity"]=elem;
        order["scrapItemList"].push(item);
      }
    });

    if(order['scrapItemList'].length !== 0){
      let res=await SellerServices.addOrders(order, config);
      console.log(res);
    };

    order['scrapCategory']="Metals";
    order['scrapItemList']=[];
    metalsOrder.map((elem, index)=>{
      if(elem!=0){
        let item={};
        item["item"]=categories[0].items[index].item;
        item["rate"]=categories[0].items[index].price;
        item["quantity"]=elem;
        order["scrapItemList"].push(item);
      }
    });

    if(order['scrapItemList'].length !== 0){
      let res=await SellerServices.addOrders(order, config);
      console.log(res);
    };

    order['scrapCategory']="Plastic";
    order['scrapItemList']=[];
    plasticOrder.map((elem, index)=>{
      if(elem!=0){
        let item={};
        item["item"]=categories[0].items[index].item;
        item["rate"]=categories[0].items[index].price;
        item["quantity"]=elem;
        order["scrapItemList"].push(item);
      }
    });

    if(order['scrapItemList'].length !== 0){
      let res=await SellerServices.addOrders(order, config);
      console.log(res);
    };

    alert("Order Successfull");
    }
    
  }
  return (
    <div className='dashboard-container' data-testid="seller-dashboard">
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
      <form onSubmit={handleSubmit}>
        <div className="form-group">
        <label className='seller-label'>Select Category</label>
        <div className="accordion accordion-flush" id="accordionFlushExample">
          <div className="accordion-item">
            <h2 className="accordion-header" id="flush-headingOne">
              <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                Mix Waste
              </button>
            </h2>
            <div id="flush-collapseOne" className="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
              <div className="accordion-body">
                {
                  categories[0].items.map((elem, index)=>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id={`checkbox-${index}`}
                    onChange={(e)=>{
                      if(e.target.checked){let temp=[...mixWaste];temp[index]=true;setMixWaste(temp);}
                      else {let temp=[...mixWaste];temp[index]=false;setMixWaste(temp);
                            temp=[...mixWasteOrder];temp[index]=0;setMixWasteOrder(temp);}
                    }}/>
                    <label className="form-check-label" htmlFor="flexCheckDefault">
                      {elem.item} {elem.price}{elem.qty}
                    </label>
                    {(mixWaste[index])?
                    <div>
                    <label className='seller-label'>Enter Quantity</label>
                    <input className="form-control" type="number" placeholder="Enter quantity" htmlFor={`checkbox-${index}`}
                    value={mixWasteOrder[index]} onChange={(e)=>{
                      let temp=[...mixWasteOrder];temp[index]=e.target.value;setMixWasteOrder(temp);
                    }} required/>
                    </div>
                    :''}
                  </div>
                  )
                }
              </div>
            </div>
          </div>
          <div className="accordion-item">
            <h2 className="accordion-header" id="flush-headingTwo">
              <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                Paper
              </button>
            </h2>
            <div id="flush-collapseTwo" className="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
              <div className="accordion-body">
              {
                  categories[1].items.map((elem, index)=>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id={`checkbox-${index}`}
                    onChange={(e)=>{
                      if(e.target.checked){let temp=[...paper];temp[index]=true;setPaper(temp);}
                      else {let temp=[...paper];temp[index]=false;setPaper(temp);
                            temp=[...paperOrder];temp[index]=0;setPaperOrder(temp);}
                    }}/>
                    <label className="form-check-label" for="flexCheckDefault">
                      {elem.item} {elem.price}{elem.qty}
                    </label>
                    {(paper[index])?
                    <div>
                    <label className='seller-label'>Enter Quantity</label>
                    <input className="form-control" type="number" placeholder="Enter quantity" htmlFor={`checkbox-${index}`}
                    value={paperOrder[index]} onChange={(e)=>{
                      let temp=[...paperOrder];temp[index]=e.target.value;setPaperOrder(temp);
                    }} required/>
                    </div>
                    :''}
                  </div>
                  )
                }
              </div>
            </div>
          </div>
          <div className="accordion-item">
            <h2 className="accordion-header" id="flush-headingThree">
              <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                Old Clothes
              </button>
            </h2>
            <div id="flush-collapseThree" className="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
              <div className="accordion-body">
                {
                  categories[2].items.map((elem, index)=>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id={`checkbox-${index}`}
                    onChange={(e)=>{
                      if(e.target.checked){let temp=[...oldClothes];temp[index]=true;setOldClothes(temp);}
                      else {let temp=[...oldClothes];temp[index]=false;setOldClothes(temp);
                            temp=[...oldClothesOrder];temp[index]=0;setOldClothesOrder(temp);}
                    }}/>
                    <label className="form-check-label" for="flexCheckDefault">
                      {elem.item} {elem.price}{elem.qty}
                    </label>
                    {(oldClothes[index])?
                    <div>
                    <label className='seller-label'>Enter Quantity</label>
                    <input className="form-control" type="number" placeholder="Enter quantity" htmlFor={`checkbox-${index}`}
                    value={oldClothesOrder[index]} onChange={(e)=>{
                      let temp=[...oldClothesOrder];temp[index]=e.target.value;setOldClothesOrder(temp);
                    }} required/>
                    </div>
                    :''}
                  </div>
                  )
                }
              </div>
            </div>
          </div>
          <div className="accordion-item">
            <h2 className="accordion-header" id="flush-headingFour">
              <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFour" aria-expanded="false" aria-controls="flush-collapseFour">
                Metals
              </button>
            </h2>
            <div id="flush-collapseFour" className="accordion-collapse collapse" aria-labelledby="flush-headingFour" data-bs-parent="#accordionFlushExample">
              <div className="accordion-body">
                {
                  categories[3].items.map((elem, index)=>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id={`checkbox-${index}`}
                    onChange={(e)=>{
                      if(e.target.checked){let temp=[...metals];temp[index]=true;setMetals(temp);}
                      else {let temp=[...metals];temp[index]=false;setMetals(temp);
                          temp=[...metalsOrder];temp[index]=0;setMetalsOrder(temp);}
                    }}/>
                    <label className="form-check-label" for="flexCheckDefault">
                      {elem.item} {elem.price}{elem.qty}
                    </label>
                    {(metals[index])?
                    <div>
                    <label className='seller-label'>Enter Quantity</label>
                    <input className="form-control" type="number" placeholder="Enter quantity" htmlFor={`checkbox-${index}`}
                    value={metalsOrder[index]} onChange={(e)=>{
                      let temp=[...metalsOrder];temp[index]=e.target.value;setMetalsOrder(temp);
                    }} required/>
                    </div>
                    :''}
                  </div>
                  )
                }
              </div>
            </div>
          </div>
          <div className="accordion-item">
            <h2 className="accordion-header" id="flush-headingFive">
              <button className="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFive" aria-expanded="false" aria-controls="flush-collapseFive">
                Plastic
              </button>
            </h2>
            <div id="flush-collapseFive" className="accordion-collapse collapse" aria-labelledby="flush-headingFive" data-bs-parent="#accordionFlushExample">
              <div className="accordion-body">
                {
                  categories[4].items.map((elem, index)=>
                  <div className="form-check">
                    <input className="form-check-input" type="checkbox" value="" id={`checkbox-${index}`}
                    onChange={(e)=>{
                      if(e.target.checked){let temp=[...plastic];temp[index]=true;setPlastic(temp);}
                      else {let temp=[...plastic];temp[index]=false;setPlastic(temp);
                          temp=[...plasticOrder];temp[index]=0;setPlasticOrder(temp);}
                    }}/>
                    <label className="form-check-label" for="flexCheckDefault">
                      {elem.item} {elem.price}{elem.qty}
                    </label>
                    {(plastic[index])?
                    <div>
                    <label className='seller-label'>Enter Quantity</label>
                    <input className="form-control" type="number" placeholder="Enter quantity" htmlFor={`checkbox-${index}`}
                    value={plasticOrder[index]} onChange={(e)=>{
                      let temp=[...plasticOrder];temp[index]=e.target.value;setPlasticOrder(temp);
                    }} required/>
                    </div>
                    :''}
                  </div>
                  )
                }
              </div>
            </div>
          </div>
        </div>
        <label className='seller-label'>Address Line</label>
        <input className="form-control" type="text" placeholder="Enter Address Line" value={addressLine}
        onChange={(e)=>{setAddressLine(e.target.value)}} required/>
        <label className='seller-label'>State</label>
        <input className="form-control" type="text" placeholder="Enter State" value={state}
        onChange={(e)=>{setState(e.target.value)}} required/>
        <label className='seller-label'>District</label>
        <input className="form-control" type="text" placeholder="Enter District" value={district}
        onChange={(e)=>{setDistrict(e.target.value)}} required/>
        <label className='seller-label'>Pincode</label>
        <input className="form-control" type="text" placeholder="Enter Pincode" value={pinCode}
        onChange={(e)=>{setPincode(e.target.value)}} required/>
        <label className='seller-label'>Pickup Date</label>
        <div className="date-picker-container">
          <DatePicker selected={date} onChange={(date) => setDate(date)} className="date-picker" required/>
        </div>
        <label className='seller-label'>Pickup Time</label>
        <TimePicker start="10:00" end="18:00" step={30} value={time} format={24}
        onChange={(e)=>{
          setTime(e)}}/>
        



        <div>
          <button type="submit" className="btn btn-primary proceed" style={{marginBottom:'10px'}}>Proceed</button>
        </div>
        </div>
      </form>
    </div>
    </div>
  )
}
