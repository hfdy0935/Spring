interface MsgType {
    sender: string
    receiver: string
    content: string
    sendTime: string
}

class Message implements MsgType {
    public sender: string;
    public receiver: string;
    public content: string;
    public sendTime: string;

    constructor(sender: string, receiver: string, content: string, sendTime: string) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.sendTime = sendTime;
    }
}