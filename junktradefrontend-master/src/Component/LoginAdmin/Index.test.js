import Index from './Index'
import {render, screen} from '@testing-library/react'
import { Provider } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import { store } from '../../State/Store.js';

describe("Test Index",()=>{
    test("render with 1 buttons",async()=>{
        render(<Provider store={store}><Router><Index/></Router></Provider>)
        const btnList=await screen.findAllByRole("button");
        expect(btnList).toHaveLength(1);
    })

    test("check password type",()=>{
        render(<Provider store={store}><Router><Index/></Router></Provider>)
        const password=screen.getByPlaceholderText("Password");
        expect(password).toHaveAttribute("type","password");
    })

    test("check email type",()=>{
        render(<Provider store={store}><Router><Index/></Router></Provider>)
        const email=screen.getByPlaceholderText("Enter email");
        expect(email).toHaveAttribute("type","email");
    })
})