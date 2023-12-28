import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ElementRef, ViewChild } from '@angular/core';
import { User } from '../Model/user';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
})
export class HomepageComponent {

  user: User;
  showProfile: boolean = true;
  username: string= '';
  imageIndex: number = 0;
  searchQuery: string = '';
  isDropdownOpen: boolean;
 
  showPopup: boolean = true;
  total: string = '';
  cartItems: any[] = [];
  selectedQuantity: number = 1;
  selectedItem: any[] =[];
  cartItemsInPopup: any[] = [];
  @ViewChild('popupOverlay') popupOverlay!: ElementRef;
  randomItemName: string;
  items: any[] =[]; 
  selectedItems: any[] = []; // Array of selected items
  selectedQuantities: number[] = []; // Array of corresponding quantities
  //showPopup: boolean = false;

 


  images: string[] = ['/assets/images/Burger1.png', '/assets/images/Pizza.png', '/assets/images/dessert5.png', '/assets/images/sandwich2.png', '/assets/images/DoughnutsMain.png', '/assets/images/icecream4.png'];

  specialItems = [
    { name: 'Chicken Burger', price: '₹240', image: '/assets/images/burger.png', quantity: 0, isAddedToCart: false },
    { name: ' Meat Pizza', price: ' ₹250', image: '/assets/images/pizza3.png', quantity: 0, isAddedToCart: false },
   // { name: ' Meat Pizza', price: ' ₹250', image: '/assets/images/pizza3.png', quantity: 0, isAddedToCart: false },
    { name: ' Strawberry Doughnut', price: '₹120', image: '/assets/images/loginimage2.png', quantity: 0, isAddedToCart: false },
    { name: 'Sandwich', price: '₹90', image: '/assets/images/sandwich1.png', quantity: 0, isAddedToCart: false },
    { name: ' Choclate Ice-cream', price: '₹120', image: '/assets/images/icecream4.png', quantity: 0, isAddedToCart: false },
   
  ];

  regularItems = [
    { name: 'VEG BURGER', price: '₹90', image: '/assets/images/regularburger.png', quantity: 0, isAddedToCart: false },
    { name: 'CAPPUCCINO', price: '₹150', image: '/assets/images/regularcoffee.png', quantity: 0, isAddedToCart: false },
    { name: 'CORQUTTE', price: '₹120', image: '/assets/images/regularcorquette.png', quantity: 0, isAddedToCart: false },
    { name: 'ONION RINGS', price: '₹70', image: '/assets/images/regularonionrings.png', quantity: 0, isAddedToCart: false },
    { name: 'VEG SALAD', price: '₹180', image: '/assets/images/regularsaladveg.png', quantity: 0, isAddedToCart: false },
   
    
  ];
  calculateTotalForSelectedItems: any;
  
  constructor(private router: Router, private sharedService: SharedService) {
   
  }

 // ngOnInit(): void {
   // console.log('Initializing HomepageComponent');
    //this.username = sessionStorage.getItem('username') || '';
    //console.log('Retrieved username:', this.username);
  //}

  updateTotalAmount(): void {
    const totalAmount = this.calculateTotalForSelectedItems();
    this.sharedService.setTotalAmount(totalAmount);
  }
  addToCart(item: any): void {
    const existingItem = this.cartItemsInPopup.find(
      (cartItemsInPopup) => cartItemsInPopup.name === item.name
    );
  
    if (existingItem) {
      existingItem.quantity++;
    } else {
      this.cartItemsInPopup.push({
        name: item.name,
        price: item.price,
        quantity: 1,
      });
      this.showPopup = this.cartItemsInPopup.length > 0;// Set showPopup to true when adding an item
    }
  }
  
  
  removeItemFromPopup(item: any): void {
    // Remove the item from the popup
    const index = this.cartItemsInPopup.findIndex((cartItem) => cartItem.name === item.name);
    if (index !== -1) {
      this.cartItemsInPopup.splice(index, 1);
    }
  }
  
  decreaseQuantityInPopup(item:any): void {
    if (item.quantity > 1) {
      item.quantity--;
    }
  }
  
  increaseQuantityInPopup(item: any): void {
    item.quantity++;
  }
  
  calculateTotalInPopup(): string {
    const total = this.cartItemsInPopup.reduce((acc: number, item: { quantity: number; price: string; }) => {
      const itemTotal = Number(item.quantity) * parseFloat(item.price.replace('₹', ''));
      console.log(`Item: ${item.price}, Quantity: ${item.quantity}, Item Total: ${itemTotal}`);
      return acc + itemTotal;
    }, 0);
    console.log(`Total: ${total.toFixed(2)}`);
    return total.toFixed(2);
  }
  
  

  redirectToBookTable() {
    this.router.navigate(['/book-table']);
  }

  redirectToHelp() {
    this.router.navigate(['/help']);
  }

  redirectToCart() {
    this.router.navigate(['/cart']);
  }

  increaseQuantity(item: any) {
    item.quantity++;
  }

  decreaseQuantity(item: any) {
    if (item.quantity > 0) {
      item.quantity--;
    }
  }

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
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
  

  navigateImage(direction: string) {
    const totalImages = this.images.length;
    const itemsPerPage = 4; // Adjust as needed
    const totalPages = Math.ceil(totalImages / itemsPerPage);

    if (direction === 'forward' && this.imageIndex < totalPages - 1) {
      this.imageIndex++;
    } else if (direction === 'backward' && this.imageIndex > 0) {
      this.imageIndex--;
    }
  }

  // Function to show the quantity popup
  showQuantityPopup(item: any): void {
    this.selectedItem = item;
    this.showPopup = true;
  }

  openPopup(): void {
    this.showPopup = true;
    
  }

  // Function to close the quantity popup
  closePopup(): void {
    this.showPopup = false;
    
  }


  placeOrder(): void {
    // Implement your order placement logic here
    console.log('Placing order...');
    // Close the pop-up after placing the order
    this.closePopup();
   
  }
 

  calculateTotal(item?: any, quantity?: number): string {
    if (item && quantity !== undefined) {
      const totalPrice = (
        parseFloat(item.price.replace('$', '')) * quantity
      ).toFixed(2);
      return `$${totalPrice}`;
    }
    return '$0.00';
  }
  

  // Function to redirect to the payment page
  redirectToPayment(): void {
    // Implement redirection to the payment page here
    console.log('Redirecting to payment page');
  }

}

