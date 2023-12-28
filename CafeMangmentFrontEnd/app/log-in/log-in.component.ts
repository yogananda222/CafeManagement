import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../Model/user';
import { CafeManagementService } from '../service/cafe-managment-service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User("", "", "", "", 0);

 // users: User = JSON.parse(sessionStorage.getItem("user"))
  constructor(private cafeManagementService: CafeManagementService, private route: Router, public activateRoute: ActivatedRoute) { }

  ngOnInit(): void
   {
    this.loginuser();
  }

  loginuser(): void {
      this.cafeManagementService.loginuser(this.user).subscribe(
        data => {
          alert('Nice to see you again!');
          console.log('login response' + data);
          sessionStorage.setItem("user", JSON.stringify(data));
          this.route.navigateByUrl("/homepage");
        },
        error => alert('Invalid Username or Password')
      );
    
  }

  onSubmit() {
    this.route.navigateByUrl("/homepage");
  }

  Onsingup(){
    this.route.navigateByUrl("/signup")
  }
}
