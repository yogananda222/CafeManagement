import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Admin } from '../Model/admin';
import { CafeManagementService } from '../service/cafe-managment-service';


@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent implements OnInit {
  
  admin: Admin = new Admin(0, "", "", "")
 // admins: Admin = JSON.parse(sessionStorage.getItem("admin"))
  constructor(private resturantmanagmentservice: CafeManagementService, private route: Router, public activateRoute: ActivatedRoute) { }
  ngOnInit(): void {  
    this.Getlogin();
  }
  Getlogin(): void {

    this.resturantmanagmentservice.getadminlogin(this.admin).subscribe(data => {
      alert("Login Successfully"),
        console.log("login response" + data)

      sessionStorage.setItem("admin", JSON.stringify(data))

      this.route.navigateByUrl("/homepage")

    },
      error => alert("Sorry Please Enter correct Username And Password"));

  }
  onSubmit() {
    this.route.navigateByUrl("/homepage");

  }
}
