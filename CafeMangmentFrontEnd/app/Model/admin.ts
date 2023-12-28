export class Admin {
    adminId: number;
    username: string;
    adminName:string;
    adminpassword: string;
        constructor(adminId:number, adminName:string, username:string,adminpassword:string) {
            this.adminId=adminId;  
            this.adminName= adminName;
              this.username= username;
              this.adminpassword= adminpassword;
        }
    }