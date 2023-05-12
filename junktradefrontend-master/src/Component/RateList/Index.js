import React from 'react'
import './Index.css'
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
  return (
    <div className='ratelist-container'>
        <nav className="navbar navbar-dark bg-dark sell">
            <span className='text-white logo'>Junk Trade</span>
        </nav>
        <div className='container'>
        <label style={{marginTop:'10px', marginBottom:'10px'}}>Category List</label>
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
                    <label className="form-check-label" htmlFor="flexCheckDefault">
                      {elem.item} {elem.price}{elem.qty}
                    </label>
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
                    <label className="form-check-label" for="flexCheckDefault">
                      {elem.item} {elem.price}{elem.qty}
                    </label>
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
                    <label className="form-check-label" for="flexCheckDefault">
                      {elem.item} {elem.price}{elem.qty}
                    </label>
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
                    <label className="form-check-label" for="flexCheckDefault">
                      {elem.item} {elem.price}{elem.qty}
                    </label>
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
                    <label className="form-check-label" for="flexCheckDefault">
                      {elem.item} {elem.price}{elem.qty}
                    </label>
                  </div>
                  )
                }
              </div>
            </div>
          </div>
        </div>
        </div>
    </div>
  )
}
