import { Injectable } from "@angular/core";
import { Http, Headers } from "@angular/http";
import { map } from "rxjs/operators";
import { BehaviorSubject } from "rxjs/internal/BehaviorSubject";
import { Message } from "./models/message";

@Injectable({
  providedIn: "root"
})
export class ChatService {
  private convId: string = "";

  conversation = new BehaviorSubject<Message[]>([]);

  constructor(private http: Http) {}

  public getResponse(userInput: string) {
    const userMessage = new Message(
      userInput,
      "user",
      "assets/images/user.png"
    );
    if (userInput) {
      this.update(userMessage);
    }

    let data = {
      inputText: userInput,
      conversationId: this.convId
    };

    return this.http
      .post("/chat", data)
      .pipe(map(response => response.json()))
      .subscribe(res => {
        const botMessage = new Message(
          res.outputText,
          "bot",
          "assets/images/bot.png"
        );
        this.convId = res.conversationId;
        this.update(botMessage);
      });
  }

  update(msg: Message) {
    this.conversation.next([msg]);
  }
}
