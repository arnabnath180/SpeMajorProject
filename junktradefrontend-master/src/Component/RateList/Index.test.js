import Index from './Index'
import {render, screen} from '@testing-library/react'


describe("Test Index",()=>{
    test("render with 5 buttons",async()=>{
        render(<Index/>)
        const btnList=await screen.findAllByRole("button");
        expect(btnList).toHaveLength(5);
    })
})