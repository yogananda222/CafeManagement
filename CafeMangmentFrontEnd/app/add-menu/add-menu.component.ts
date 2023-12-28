import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Food } from '../Model/food';
import { Admin } from '../Model/admin';
import { CafeManagementService } from '../service/cafe-managment-service';

@Component({
  selector: 'app-add-menu',
  templateUrl: './add-menu.component.html',
  styleUrls: ['./add-menu.component.css']
})
export class AddMenuComponent {
  menu: Food = new Food(0, "", 0, "");
  isEditable!: boolean;
  admin!: Admin;

  constructor(
    private resturantmanagemntservice: CafeManagementService,
    private router: Router,
    private activateRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Subscribe to route parameters to get updates when they change
    this.activateRoute.params.subscribe(params => {
      // Extract the 'id' parameter from the route
      const foodId = parseFloat(params['id']);

      if (foodId > 0) {
        this.isEditable = true;
        // Call the method to fetch menu details by ID
        this.getMenuById(foodId);
      }
    });

    // Retrieve the 'admin' from sessionStorage
   // this.admin = JSON.parse(sessionStorage.getItem('admin'));

    // Check session and navigate if needed
    this.checkSessionAndNavigate();
  }

  onSubmit() {
    console.log('isEditable:', this.isEditable);
    console.log('Item Id', this.menu);
  
    if (this.isEditable) {
      this.resturantmanagemntservice.updateFood(this.menu).subscribe(data => {
        console.log(data);
        alert('The Menu Item is updated');
        this.router.navigateByUrl('/add-menu');
      });
    } else {
      this.resturantmanagemntservice.SaveMenu(this.menu).subscribe(data => {
        console.log(data);
        alert('The Menu Item is Added');
        this.router.navigateByUrl('/add-menu');
      });
    }
  }

  getMenuById(itemId: number) {
    this.resturantmanagemntservice.getmenubyid(itemId).subscribe(data => {
      this.menu = data;
      console.log(this.menu);
    });
  }

  checkSessionAndNavigate() {
    if (!this.admin) {
      this.router.navigateByUrl('/admin/login');
    }
  }
}
