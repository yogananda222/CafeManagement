import { Component } from '@angular/core';
import { CafeManagementService } from '../service/cafe-managment-service';
import { ActivatedRoute, Router } from '@angular/router';
import { Admin } from '../Model/admin';

@Component({
  selector: 'app-customerview',
  templateUrl: './customerview.component.html',
  styleUrl: './customerview.component.css'
})
export class CustomerviewComponent {

customer : any;
  hasSearchName!: boolean;
  searchName!: string;
  admin!: Admin;
  p: number = 1;
count: number = 5;
items = Array.from({ length: 1000 }).map((_, index) => `Item #${index + 1}`);

constructor(private resturantManageService:CafeManagementService,public router:Router, private activeRoute:ActivatedRoute) {  }
ngOnInit(): void {
  this.activeRoute.paramMap.subscribe(() => this.getAllCustomer());
  
  // Check if "admin" exists in sessionStorage before parsing
  const adminData = sessionStorage.getItem("admin");
  if (adminData) {
    this.admin = JSON.parse(adminData);
  }

  this.checkSessionAndNavigate();
}


    getAllCustomer()
  {
    this.hasSearchName = this.activeRoute.snapshot.paramMap.has("username");
       if(this.hasSearchName)
       {this.searchName = this.activeRoute.snapshot.paramMap.get("username") ?? '';
        console.log(this.searchName)
        this.resturantManageService.getCustomerByUsername(this.searchName).subscribe(data=>{
        console.log(data);
        this.customer= data;
        })
      }
      else{
      this.resturantManageService.getAllCustomer().subscribe(data=>{
        console.log(data);
        this.customer=data;
      });
    }
    }

    deleteCustomer(id:number):void{
      console.log(id);
      if(confirm("Do you want to delete ?")){
        this.resturantManageService.deleteCustomer(id).subscribe(data=>{
          console.log(data);
          this.getAllCustomer();
        })
      };
    }
    updateCustomer(id:number)
  {
    this.router.navigateByUrl("/updateCustomer/"+id);
  
  }
  viewOrder():void{
    this.router.navigateByUrl("/orderview");
  }
  addMenu(): void{
    this.router.navigateByUrl("/add-menu");
  }
  addUser(): void{
    this.router.navigateByUrl("/signup");
  }
  logout() {
    if (sessionStorage.getItem("admin")) {
      sessionStorage.clear()
      localStorage.clear()
      alert("Logout Successfully")
      this.router.navigateByUrl("/adminlogin")
    }
    else {
      alert("No user loged in")
    }
  }
  Back()
  {
    this.router.navigateByUrl("/homepage");
  }
  checkSessionAndNavigate() {
    if (!this.admin) {
      this.router.navigateByUrl("/adminlogin");
    }
  }
}
