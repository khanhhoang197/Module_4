class App{
    static DOMAIN_SERVER = "http://localhost:8080";
    static CUSTOMER_API = this.DOMAIN_SERVER + "/api/customers";
    static DEPOSIT_API = this.DOMAIN_SERVER + "/api/deposits";
    static WITHDRAW_API = this.DOMAIN_SERVER + "/api/withdraws";
    static TRANSFER_API = this.DOMAIN_SERVER + "/api/transfers";

}

class Customer{
    constructor (id, name, email, phone, address, balance, deleted){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
        this.address = address;
        this.deleted = deleted;
    }
}
class Withdraw {
    constructor(id, customerId, transactionAmount) {
        this.id = id;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }
}

class Deposit {
    constructor(id, customerId, transactionAmount) {
        this.id = id;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
    }
}

class Transfer {
    constructor(id, senderId, transferAmount, fees, feesAmount, transactionAmount, recipientId) {
        this.id = id;
        this.senderId = senderId;
        this.transferAmount = transferAmount;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
        this.recipientId = recipientId;
    }
}