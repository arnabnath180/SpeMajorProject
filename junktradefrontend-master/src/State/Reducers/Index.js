import {combineReducers} from 'redux';
import adminEmailReducer from './AdminEmailReducer';
import adminStatusReducer from './AdminStatusReducer';
import sellerEmailReducer from './SellerEmailReducer';
import sellerStatusReducer from './SellerStatusReducer';

const reducers=combineReducers({
    adminStatus:adminStatusReducer,
    adminEmail:adminEmailReducer,
    sellerStatus:sellerStatusReducer,
    sellerEmail:sellerEmailReducer
})

export default reducers;