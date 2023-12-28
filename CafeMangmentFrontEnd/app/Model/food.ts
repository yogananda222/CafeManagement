
export class Food {
    foodId:number;
    foodName:string;
    foodPrice:number;
    img : string
    constructor(foodId:number, foodName:string, foodPrice:number , img : string) {
          this.foodId= foodId;
          this.foodName= foodName;
          this.foodPrice= foodPrice;
          this.img=img
    }
}
