import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Http, Headers } from '@angular/http';
import { map } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Message } from './models/message';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private baseURL: string = "https://api.dialogflow.com/v1/query";

  private token: string = environment.token;

  conversation = new BehaviorSubject<Message[]>([]);

  constructor(private http: Http) { }


  public getResponse(userInput: string) {
    const userMessage = new Message(userInput, 'user', 'assets/images/user.png');
    this.update(userMessage);

    let data = {
      query: userInput,
      lang: 'en',
      sessionId: '12345'
    }

    return this.http
      .post(`${this.baseURL}`, data, { headers: this.getHeaders() }).pipe(
        map(response => response.json())).subscribe(res => {
          const botMessage = new Message(res.result.speech, 'bot', 'assets/images/bot.png');
          this.update(botMessage);
        });
  }


  getHeaders() {
    let headers = new Headers();
    headers.append('Authorization', `Bearer ${this.token}`);
    return headers;
  }

  update(msg: Message) {
    this.conversation.next([msg]);
  }

}
