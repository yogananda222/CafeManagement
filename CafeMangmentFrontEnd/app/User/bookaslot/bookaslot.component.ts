import { Component } from '@angular/core';
import { User } from '../Model/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-bookaslot',
  templateUrl: './bookaslot.component.html',
  styleUrl: './bookaslot.component.css'
})
export class BookaslotComponent {

  user: User;
  showProfile: boolean = false;
  imageIndex: number = 0;
  searchQuery: string = '';
  isDropdownOpen: boolean;
  username: string;

  bookingDetails = {
    name: '',
    email: '',
    phone: '',
    date: '',
    time: '',
    guests: 1
  };
  successMessage: string;

  constructor(private router: Router) {
   
  }

  onSubmit() {
    // Handle form submission logic here
    this.bookingDetails.guests = this.bookingDetails.guests < 1 ? 1 : this.bookingDetails.guests;

    // Validate the date and time
    const selectedDateTime = new Date(`${this.bookingDetails.date} ${this.bookingDetails.time}`);
    const currentDate = new Date();

    if (selectedDateTime <= currentDate) {
      // Date or time is in the past
      console.log('Selected date or time is in the past.');
      return;
    }

    console.log('Booking details submitted:', this.bookingDetails);

    // Add your logic to send the booking details to the server or perform other actions
    this.successMessage = 'Congratulations! Your table has been booked.';

    setTimeout(() => {
      this.router.navigate(['/homepage']); // Replace '/' with the actual path to your homepage
    }, 3000);
  }


  redirectToHelp() {
    this.router.navigate(['/help']);
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
}

