import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomepageComponent } from './welcomepage/welcomepage.component';
import { LoginComponent } from './log-in/log-in.component';
import { signupcomponent} from './signup/signup.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AddMenuComponent } from './add-menu/add-menu.component';
import { CustomerviewComponent } from './customerview/customerview.component';
import { AdminViewmenuComponent } from './admin-view-menu/admin-view-menu.component';
import { OrderviewComponent } from './orderview/orderview.component';
import { ItempageComponent } from './User/itmepage/itmepage.component';
import { HelppageComponent } from './User/help-page/help-page.component';
import { CartpageComponent } from './User/cartpage/cartpage.component';
import { BookslotComponent } from './User/bookslot/bookslot.component';
const routes: Routes = [
  {path:'', component: WelcomepageComponent },
  {path:'log-in', component: LoginComponent},
  {path:'signup', component: signupcomponent},
  {path:'adminlogin', component: AdminloginComponent},
  {path:'admin-view-menu', component: AdminViewmenuComponent},
  {path:'homepage', component: HomepageComponent},
  {path:'add-menu', component: AddMenuComponent},
  {path:'customerview', component: CustomerviewComponent},
  {path: 'orderview', component: OrderviewComponent},
  {path: 'itempage', component: ItempageComponent},
  {path: 'help-page', component: HelppageComponent},
  {path: 'homepage', component: HomepageComponent},
  {path: 'cartpage', component : CartpageComponent},
  {path: 'bookslot', component: BookslotComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

