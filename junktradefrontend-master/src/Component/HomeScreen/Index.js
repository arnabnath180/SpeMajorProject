import React from 'react'
import './Index.css'
import Modal from "react-bootstrap/Modal";
import { useNavigate } from 'react-router-dom';

export default function () {
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = React.useState(false);

  const showModal = () => {
    setIsOpen(true);
  };

  const hideModal = () => {
    setIsOpen(false);
  };
  return (
    <div className='body'>
        <nav className="navbar navbar-expand-lg navbar-light bg-light homenav">
            <span className='text-white logo'>Junk Trade</span>
            <div className="custom-nav-buttons" id="navbarSupportedContent">
                <button type="button" className="btn btn-outline-light border-white btn1"
                onClick={()=>{navigate('/ratelist')}}>Check Rate List</button>
                <button type="button" className="btn btn-light" onClick={showModal}>Sell Scrap</button>
            </div>
        </nav>
        <div className='text'>Got Scrap?<br/>Sell it to us.</div>
        <Modal show={isOpen} onHide={hideModal} contentClassName="custom-modal-style">
          <Modal.Body>
            <button type="button" className="btn btn-outline-success border-success btn-lg"
            onClick={()=>{navigate('/register')}}>Register</button>
            <button type="button" className="btn btn-success btn-lg" onClick={()=>{navigate('/seller/login')}}>Login</button>
          </Modal.Body>
        </Modal>
    </div>
  )
}
