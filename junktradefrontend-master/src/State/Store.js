import { applyMiddleware, createStore } from "redux";
import reducers from './Reducers/Index.js'
import thunk from 'redux-thunk'


export const store=createStore(reducers, {}, applyMiddleware(thunk));
