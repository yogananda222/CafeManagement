
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../Model/user';
import { CafeManagementService } from '../service/cafe-management-service';
import { Payment } from '../Model/payment';
import { OrderService } from '../Model/order.service';
import { SharedService } from '../shared.service';import { state } from '@angular/animations';
{}

@Component({
  selector: 'app-itempage',
  templateUrl: './itempage.component.html',
  styleUrls: ['./itempage.component.css']
})
export class ItempageComponent implements OnInit {

  deliveryAddresses: string[] = ['Address'];  // Add this line
  showProfile: boolean = false;
   searchQuery: any[]=[]; 
  isDropdownOpen: boolean;
  username: string;
  user:User;
  order: OrderService;
  generatedOrderId: number;
  cust: number;
  payment: any = {
    nameOnCard: '',
    cardNumber: '',
    expYear: '',
    cvv: '',
   paidamount:"",  
    
    

    
  };
  isServiceNotAvailable: boolean;
  doorNumber: any;
  address: any;
  city: any;
  state: any;
  pinCode: any;


  constructor(private activeRoute: ActivatedRoute,private router: Router,private CafeManagementService: CafeManagementService, private sharedService: SharedService) {}
  ngOnInit(): void {
    this.generatedOrderId = Number(localStorage.getItem("generatedOrderId"));
    this.getOrderByOrderId();
    this.activeRoute.paramMap.subscribe(()=>this.user=JSON.parse(sessionStorage.getItem("customer")));
    this.cust=this.user.userid
    this.checkSessionAndNavigate();

    this.payment.paidAmount = this.sharedService.getTotalAmount();

  }
  
  onSubmit() {
    this.CafeManagementService.addPayment(this.payment, this.generatedOrderId, this.cust).subscribe(
      () => {
        
        console.log(this.payment);
        console.log('Payment added successfully');
        alert("Payment added successfully")
        localStorage.clear()
        this.router.navigateByUrl("/cart")
      },
      (error) => {
        console.error('Error adding payment', error); 
      }
    );
  }

  getOrderByOrderId() {
    this.CafeManagementService.getorderbyid(this.generatedOrderId).subscribe(
      (data) => {
        console.log(data); // Log the order object
        this.order = data;
        this.payment.paidAmount = this.order.totalPrice;
      },
      (error) => {
        console.error('Error fetching order', error);
      }
    );
  }
  
  

  logout() {
    if (sessionStorage.getItem("user")) {
      sessionStorage.clear()
      localStorage.clear()
      alert("Hope will see you again ! Have a nice day :)")
      this.router.navigateByUrl("/log-in")
    }
    else {
      alert("No user loged in")
    }
   }
   checkSessionAndNavigate() {
    if (!this.user) {
      this.router.navigateByUrl("/log-in");
    }
  }

  toggleProfile() {
    this.showProfile = !this.showProfile;
  }

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

    
  redirectToBookTable() {
    this.router.navigate(['/book-table']);
  }

  redirectToHelp() {
    this.router.navigate(['/help']);
  }
 
  selectedDeliveryAddress: string = '';
  validDeliveryAddresses: string[] = ['Bangalore', 'Pune'];

  deliveryAddressForm: any = {
    doorNumber: '',
    address: '',
    city: 'Bangalore' ,
    state: 'Karnatka',
    
  };
  
  onDeliveryAddressChange() {
    this.isServiceNotAvailable = false;
  }
  onDeliveryAddressSubmit() {
    // Perform validation against your data (replace with your logic)
    const isValidArea = this.validateArea(this.deliveryAddressForm.city, this.deliveryAddressForm.state);

    if (isValidArea) {
      // Continue with the delivery address submission logic
     
      console.log('Delivery address submitted successfully.');
      alert : "Delivery address saved";
    } else {
    
      this.isServiceNotAvailable = true;
     
    }
  }

  private validateArea(city: string, state: string): boolean {
    
    const validatecity= ['Bangalore ', 'Pune', ];
    const validStates = ['karnataka', 'Maharastra'];

    const formattedCity = city.trim();
    const formattedState = state.trim();

    console.log('City:', city);
    console.log('State:', state);
  
    const isValid = validatecity.includes(formattedCity) && validStates.includes(formattedState);

  
    console.log('Validation Result:', isValid);
  
    return isValid;
  }



}