export class Message {
  content: string;
  sentBy: string;
  avatar: string;
  constructor(content: string, sentBy: string, avatar: string) {
    this.content = content;
    this.sentBy = sentBy;
    this.avatar = avatar;
  }
}
