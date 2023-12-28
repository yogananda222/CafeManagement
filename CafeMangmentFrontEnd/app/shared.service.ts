import { Injectable } from '@angular/core';
import { User } from './Model/user';
@Injectable({
  providedIn: 'root'
})
export class SharedService {
 
  

  constructor() { }

  private totalAmount: number = 0;

  setTotalAmount(amount: number): void {
    this.totalAmount = amount;
  }

  getTotalAmount(): number {
    return this.totalAmount;
  }
}
