import Index from './Index'
import RenderedComponent1 from '../SellerDashboard/Index.js';
import RenderedComponent2 from '../SellerHistory/Index.js';
import {fireEvent, render, screen} from '@testing-library/react'
import { BrowserRouter as Router } from 'react-router-dom';
import { Provider } from 'react-redux';
import { store } from '../../State/Store.js';

describe("Test Index",()=>{
    test("render with 3 buttons",async()=>{
        render(<Provider store={store}><Router><Index/></Router></Provider>)
        const btnList=await screen.findAllByRole("button");
        expect(btnList).toHaveLength(3);
    })

    test("sell button click", async()=>{
        const { queryByTestId }=render(
            <Provider store={store}>
            <Router>
                <Index/>
                <RenderedComponent1/>
            </Router>
            </Provider>
        )
        const sell=screen.getByRole("button",{name:"Sell Scrap"})
        fireEvent.click(sell)
        expect(queryByTestId('seller-dashboard')).toBeInTheDocument();
    })

    test("history button click", ()=>{
        const { queryByTestId }=render(
            <Provider store={store}>
            <Router>
                <Index/>
                <RenderedComponent2/>
            </Router>
            </Provider>
        )
        const history=screen.getByRole("button",{name:"Scrap History"})
        fireEvent.click(history)
        expect(queryByTestId('scrap-history')).toBeInTheDocument();
    })  
})