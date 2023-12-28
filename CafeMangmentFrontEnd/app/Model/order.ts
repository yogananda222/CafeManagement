import { Time } from "@angular/common";

export class OrderService {
    totalPrice: number;
    quantity: number;
    user: string 
    orderDate: Date;
    orderTime: Time;
    menu: string;
    status: string;
     orderId: number; 

    constructor( totalPrice: number, orderId: number ,user: string ,orderDate: Date, orderTime: Time, menu: string, quantity: number,status: string){

        this.totalPrice= totalPrice;
        this.quantity= quantity;
        this.user= user;
        this.orderDate= orderDate;
        this.orderTime = orderTime;
        this.menu= menu;
        this.status= status;
        this.orderId= orderId
    }
   
}
