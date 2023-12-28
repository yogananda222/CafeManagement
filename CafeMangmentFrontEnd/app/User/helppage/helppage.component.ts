import { ChangeDetectorRef, Component } from '@angular/core';

@Component({
  selector: 'app-helppage',
  templateUrl: './helppage.component.html',
  styleUrls: ['./helppage.component.css'] 
})
export class HelppageComponent {



  

  faq: { question: string; answer: string; showAnswer: boolean }[] = [
    { question: 'How do I place an order?', 
    answer: 'To place an order, If you are an exsiting user, please do login and select the item and qty then you will get the palce order option. Go ahead with placing an order. Or else, I kindly reques to pelase signup and follow the same process.  ', 
    showAnswer: false },
    { question: 'What payment methods are accepted?', answer: 'I would like to inform you that, As of now we are accepting card. We are on process of giving option of UPI. We are promising to our customers , soon you all will get UPI payment option. Thank you for being member of TRIBLEND Cafe.', showAnswer: false },
    { question: 'Is there a refund policy?', answer: 'We really sorry to infrom this to you, we do not have any refund policy', showAnswer: false },
    { question: 'I want to reserve a table', answer: 'We are  glad to hear that! Please click on Book a Table option in our website. It will land you on the page to book a table in you cafe.  ', showAnswer: false },
    { question: 'How to contact customer support?', answer: 'I kindly request you to connect our support team by calling on 9494075192. We will be happy to help you. ', showAnswer: false },
    // ... add more questions and answers as needed ... 'I want to reserve a table'
  ];
  constructor(private cdr: ChangeDetectorRef) {}

  // helppage.component.ts

toggleAnswer(index: number) {
  console.log('Toggle Answer Clicked:', index);
  this.faq[index].showAnswer = !this.faq[index].showAnswer;
  console.log('FAQ Item:', this.faq[index]);
  this.cdr.detectChanges();
}


}
