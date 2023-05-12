import axios from "axios";

const BASE_URL = "http://192.168.49.2:32000/seller";


class SellerService {

    
saveSeller(data) {
    return axios.post(BASE_URL+"/register_seller", data);
}

loginSeller(data) {
    return axios.post(BASE_URL+"/login", data);
}

addOrders(data, config) {
    return axios.post(BASE_URL+"/add_orders", data, config);
}

scrapList(config) {
    return axios.get(BASE_URL+"/my_orders",config);
}

scrapListItem(scrapId,config) {
   return axios.get(BASE_URL+"/getScrapItemList/" + scrapId,config);
}

scrapListDelete (scrapId,config) {
    return axios.delete(`${BASE_URL}/deleteScrapList/${scrapId}`,config);
}


}

export default new SellerService();