import { Component } from '@angular/core';

import { Router } from '@angular/router';

@Component({
  selector: 'app-cartpage',
  templateUrl: './cartpage.component.html',
  styleUrls: ['./cartpage.component.css']
})
export class CartpageComponent {

  constructor(private router: Router) { }

  ngOnInit(): void {
    // Automatically navigate to the homepage after 3 seconds (adjust as needed)
    setTimeout(() => {
      this.router.navigate(['/homepage']); // Replace '/' with the actual path to your homepage
    }, 3000);
  }

}
