export class  User{
    username: string ;
    email : string;
    userPhone: string;
    userpassword: string;
    userId: number;
  
    constructor(username: string, 
      userPhone:string, userpassword:string , email : string, userId: number) {
            this.username= username;
           
            this.email= email;
          
            this.userPhone = userPhone;
            this.userpassword = userpassword;
          
            this.userId= userId;
  
    }
  
  }
  