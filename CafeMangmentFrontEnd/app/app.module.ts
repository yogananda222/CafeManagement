import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { WelcomepageComponent } from './welcomepage/welcomepage.component';
import { LoginComponent } from './log-in/log-in.component';
import { signupcomponent} from './signup/signup.component';
import { AdminloginComponent } from './adminlogin/adminlogin.component';
import { RouterModule } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { AddMenuComponent } from './add-menu/add-menu.component';
import { CustomerviewComponent } from './customerview/customerview.component';
import { AdminViewmenuComponent } from './admin-view-menu/admin-view-menu.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { OrderviewComponent } from './orderview/orderview.component';
import { ItempageComponent } from './User/itmepage/itmepage.component';
import { PaymentComponent } from './User/payment/payment.component';
import { CartpageComponent } from './User/cartpage/cartpage.component';
import { BookslotComponent } from './User/bookslot/bookslot.component';

@NgModule({
  declarations: [AppComponent, WelcomepageComponent, LoginComponent, signupcomponent, AdminloginComponent, HomepageComponent,  AddMenuComponent, CustomerviewComponent,AdminViewmenuComponent, OrderviewComponent, ItempageComponent, PaymentComponent, CartpageComponent, BookslotComponent]
  ,imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    NgxPaginationModule ,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
