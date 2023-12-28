import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../Model/user';
import { Admin } from '../Model/admin';
import { Food } from '../Model/food';
import { OrderService } from '../Model/order';

@Injectable({
  providedIn: 'root'
})
export class CafeManagementService {
 

  private userlisturl="http://localhost:8080/user/list";

  private foodlisturl="http://localhost:8080/food/list";

  private ordersviewurl="http://localhost:8080/orders/vieworders";

  private paymentviewurl="http://localhost:8080/payment"; 

  private addfooodurl="http://localhost:8080/food/addfood";

  private updatefoodurl="http://localhost:8080/food/update";

  private deletefoodurl="http://localhost:8080/f ood/delete";

  private adminloginurl="http://localhost:8080/admin/login";

  private usersignupurl="http://localhost:8080/user/signup";

  private userloginurl="http://localhost:8080/user/login";

  private gettinguserbyidurl="http://localhost:8080/user";

  private updateuserbyidurl="http://localhost:8080/user/update";

  private userdeletebyidurl="http://localhost:8080/user/delete";

  private getorderbyIdurl="http://localhost:8080/orders";

  private getorderbyuseridurl="http://localhost:8080/orders/user";

  private createorderbyproductidurl="http://localhost:8080/orders/create";

  private deleteorderbyidurl="http://localhost:8080/orders/delete";

  private foodsaveURl = "http://localhost:8080/food/find";  
  
  private viewuserbyusernameurl = "http://localhost:8080/user/username";

  private viewuserurl = "http://localhost:8080/user/list";

  private addpaymenturl ="http://localhost:8080/payment";


  constructor(private http: HttpClient) { }

  
     //SignUP-User
    
    saveUser(user: User): Observable<User> {
      const httpOptions = {
      headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'auth-token',
      'Access-Control-Allow-Origin': '*'
    })
  };
  return this.http.post<User>(this.usersignupurl, user, httpOptions);
}

    
    //Login-user
    loginuser(user: User): Observable<User> {
      console.log(user);
      return this.http.post<User>(`${this.userloginurl}`, user);
    }
  
    //GetUserById
    getuserbyid(uid: number):Observable<User>  {
      const uidUrl = this.gettinguserbyidurl + "/" + uid;
      return this.http.get<User>(uidUrl);
    }
  
    updateuser(user: User ): Observable<User> {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'auth-token',
          'Access-Control-Allow-Origin': '*'
        })
      };
      return this.http.put<User>(this.updateuserbyidurl +`/${user}`, user, httpOptions);
    }

    //delete user by id


deleteCustomer(id: number) {
   
  const httpOptions = {
    headers : new HttpHeaders({
        'Content-Type' : 'application/json',
        'Authorization' : 'auth-token',
        'Access-Control-Allow-Origin' : '*'
    })
  };
  return  this.http.delete<User>(this.userdeletebyidurl+`/${id}`,httpOptions);
}
  
    //Login-Admin
  getadminlogin(admin: Admin): Observable<Admin> {
    console.log(admin);
    return this.http.post<Admin>(`${this.adminloginurl}`, admin);
  }
  //Get customer by username
getCustomerByUsername(username:String):Observable<User>  {
  const uidUrl = this.viewuserbyusernameurl + "/" + username;
  return this.http.get<User>(uidUrl);
}
//add payments code
addPayment(payment: any, orderId: number, customerId: number): Observable<any> {
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const url = `${this.addpaymenturl}/${orderId}/${customerId}`;
  return this.http.post(url, payment, { headers });
}

  //add food
  SaveMenu(food:Food):Observable<Food>
  {
    const httpOptions = {
      headers : new HttpHeaders({
          'Content-Type' : 'application/json',
          'Authorization' : 'auth-token',
          'Access-Control-Allow-Origin' : '*'
      })
    };
    return  this.http.post<Food>(this.addfooodurl,food,httpOptions);
  }
  //Update Food
  updateFood(food: Food): Observable<Food> {
    const httpOptions = {
      headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'auth-token',
      'Access-Control-Allow-Origin': '*'
    })
  };
    return this.http.put<Food>(this.updatefoodurl, food,httpOptions);
  }
  
  //delete food by ID
  deleteMenu(id: number) {
   
    const httpOptions = {
      headers : new HttpHeaders({
          'Content-Type' : 'application/json',
          'Authorization' : 'auth-token',
          'Access-Control-Allow-Origin' : '*'
      })
    };
    return  this.http.delete<Food>(this.deletefoodurl+`/${id}`,httpOptions);
  }
  getAllMenu(): Observable<Food[]> {
    return this.http.get<Food[]>(this.foodlisturl);
  }
  
  getAllOrder(): Observable<OrderService[]> {
    return this.http.get<OrderService[]>(this.ordersviewurl);
  }
  //get all customers
getAllCustomer():Observable<any>
{
 return this.http.get(this.viewuserurl);
}
  
  getorderbyid(uid: number): Observable<OrderService> {
    // Assuming OrderService is the correct type here
    const uidUrl = this.getorderbyIdurl + "/" + uid;
    return this.http.get<OrderService>(uidUrl);
  }
  
  // Repeat the same pattern for other methods using proper types
  
//Get order by user id 
getorderbycustomerid(uid: number):Observable<OrderService>  {
  const uidUrl = this.getorderbyuseridurl+ "/" + uid;
  return this.http.get<OrderService>(uidUrl);
}

//delete Order by order id. 
deleteOrder(id: number): Observable<OrderService> {
  const headers = new HttpHeaders({ 'Authorization': 'auth-token' }); 
  const url = `${this.deleteorderbyidurl}/${id}`;
  return this.http.delete<OrderService>(url, { headers });
}
//Food by name
getMenuByFname(fname:any):Observable<Food>
  {
    const searchURL =   "http://localhost:8080/food/foodName"+"/"+ fname;
    return  this.http.get<Food>(searchURL);
  }
//Food by id
  getmenubyid(uid: number):Observable<Food>  {
    const uidUrl = this.foodsaveURl + "/" + uid;
    return this.http.get<Food>(uidUrl);
  }


  
  }