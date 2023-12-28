import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CafeManagementService } from '../service/cafe-managment-service';
import { Admin } from '../Model/admin';

@Component({
  selector: 'app-admin-viewmenu',
  templateUrl: './admin-view-menu.component.html',
  styleUrls: ['./admin-view-menu.component.css']
})

export class AdminViewmenuComponent {
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
  addMenu():void
{
  this.router.navigateByUrl("/homepage");
}
logout() {
  if (sessionStorage.getItem("admin")) {
    sessionStorage.clear()
    localStorage.clear()
    alert("Logout Successfully")
    this.router.navigateByUrl("/homepage")
  }
  else {
    alert("No user loged in")
  }
}
showMenu() {
  this.router.navigate(['/homepage']);
}
checkSessionAndNavigate() {
  if (!this.admin) {
    this.router.navigateByUrl("/homepage");
  }
}
  
  }
  
