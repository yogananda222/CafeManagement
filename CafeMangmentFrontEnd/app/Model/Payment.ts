export class payment {
    paymentId: number;
    totalPrice: number;
    orderId: number;
    nameOnCard: string;
    cardNumber: string;
    expYear: string;
    cvv: number;
    paidDate: Date;
    paidAmount: number;
    user: string;

    constructor(
        paymentId: number,totalPrice: number,orderId: number,nameOnCard: string,cardNumber: string,expYear: string,cvv: number,paidDate: Date,paidAmount: number,user: string) {
            this.paymentId= paymentId;
            this.totalPrice= totalPrice;
            this.orderId= orderId;
            this.nameOnCard= nameOnCard;
            this.cardNumber= cardNumber;
            this.expYear= expYear;
            this.cvv= cvv;
            this.paidDate= paidDate;
            this.paidAmount= paidAmount;
            this.user=user
        };
}
