import { Component, OnInit } from "@angular/core";
import { Message } from "../models/message";
import { Observable } from "rxjs";
import { ChatService } from "../chat.service";
import { scan } from "rxjs/operators";

@Component({
  selector: "app-chat-dialog",
  templateUrl: "./chat-dialog.component.html",
  styleUrls: ["./chat-dialog.component.scss"]
})
export class ChatDialogComponent implements OnInit {
  messages: Observable<Message[]>;
  formValue: string;

  constructor(public chat: ChatService) {}

  ngOnInit() {
    this.messages = this.chat.conversation
      .asObservable()
      .pipe(scan((acc, val) => acc.concat(val)));
    this.chat.getResponse("");
  }

  sendMessage() {
    this.chat.getResponse(this.formValue);
    this.formValue = "";
  }
}
