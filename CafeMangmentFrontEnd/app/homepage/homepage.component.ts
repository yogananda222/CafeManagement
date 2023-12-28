import { Component } from '@angular/core';
import { CafeManagementService } from '../service/cafe-managment-service';
import { Admin } from '../Model/admin';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {
  menu:any;
  hasSearchName: boolean = false;
  admin!: Admin;
  p: number = 1;
  count: number = 4;
  searchName!: string | null;
    constructor(private resturantManageService: CafeManagementService,public router:Router, private activeRoute:ActivatedRoute) { }
    ngOnInit(): void 
    {
      this.activeRoute.paramMap.subscribe(()=>this.getAllMenu());
    //  this.activeRoute.paramMap.subscribe(()=>this.admin=JSON.parse(sessionStorage.getItem("admin")))
      this.checkSessionAndNavigate();
    }
    getAllMenu() {
      this.hasSearchName = this.activeRoute.snapshot.paramMap.has("fname");
    
      if (this.hasSearchName) {
        this.searchName = this.activeRoute.snapshot.paramMap.get("fname");
    
        if (this.searchName !== null) {
          console.log(this.searchName);
          this.resturantManageService.getMenuByFname(this.searchName).subscribe(data => {
            console.log(data);
            this.menu = data;
          });
        } else {
          console.error("Invalid searchName value:", this.searchName);
        }
      } else {
        this.resturantManageService.getAllMenu().subscribe(data => {
          console.log(data);
          this.menu = data;
        });
      }
    }
  
    deleteMenu(id:number):void{
      console.log(id);
      if(confirm("Do you want to delete ?")){
        this.resturantManageService.deleteMenu(id).subscribe(data=>{
          console.log(data);
          this.getAllMenu();
        })
      };
    }
  
    updateMenu(id:number)
  {
    this.router.navigateByUrl("/updateMenu/"+id);
  
  }
  viewCustomer():void
{
  this.router.navigateByUrl("/customerview");
}
viewOrder():void{
  this.router.navigateByUrl("/orderview");
}
addMenu(): void{
  this.router.navigateByUrl("/add-menu");
}
logout() {
  if (sessionStorage.getItem("admin")) {
    sessionStorage.clear()
    localStorage.clear()
    alert("Logout Successfully")
    this.router.navigateByUrl("/")
  }
  else {
    alert("No user loged in")
  }
}
foodlist()
{
  this.router.navigateByUrl("/foodList")
}
checkSessionAndNavigate() {
  if (!this.admin) {
    this.router.navigateByUrl("/homepage");
  }
}
  
  }
  
