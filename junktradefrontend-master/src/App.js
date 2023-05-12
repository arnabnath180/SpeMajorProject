import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './Component/HomeScreen/Index.js';
import Register from './Component/RegisterSeller/Index.js';
import LoginSeller from './Component/LoginSeller/Index.js';
import AdminLogin from './Component/LoginAdmin/Index.js';
import SellerDashboard from './Component/SellerDashboard/Index.js';
import AdminDashboard from './Component/AdminDashboard/Index.js';
import ScrapItemList from './Component/ScrapItemList/Index.js';
import RateList from './Component/RateList/Index.js';
import SellerHome from './Component/SellerHome/Index.js';
import SellerHistory from './Component/SellerHistory/Index.js';
import SellerScrapItem from './Component/SellerScrapItem/Index.js';
import SellerProtected from './Component/SellerProtected';
import AdminProtected from './Component/AdminProtected';

function App() {
  return (
    <>
      <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>}></Route>
        <Route path='/register' element={<Register/>}></Route>
        <Route path='/seller/login' element={<LoginSeller/>}></Route>
        <Route path='/admin/login' element={<AdminLogin/>}></Route>
        <Route path='/seller/dashboard' element={<SellerProtected Component={SellerDashboard}/>}></Route>
        <Route path='/admin/dashboard' element={<AdminProtected Component={AdminDashboard}/>}></Route>
        <Route path='/admin/scrapItem' element={<AdminProtected Component={ScrapItemList}/>} ></Route>
        <Route path='/ratelist' element={<RateList/>} ></Route>
        <Route path='/seller/home' element={<SellerProtected Component={SellerHome}/>} ></Route>
        <Route path='/seller/history' element={<SellerProtected Component={SellerHistory}/>} ></Route>
        <Route path='/seller/scrapItem' element={<SellerProtected Component={SellerScrapItem}/>} ></Route>
      </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
