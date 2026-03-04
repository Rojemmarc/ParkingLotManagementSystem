package people;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import parking.*;


public class Receipt {

    private static int receiptCounter = 1000;
    private String receiptNumber;
    private Customer customer;
    private Ticket ticket;
    private double amountPaid;
    private Staff processedBy;
    private LocalDateTime paymentTime;

    // Constructor
    public Receipt(Customer customer, Ticket ticket, double amountPaid, Staff processedBy) {
        this.receiptNumber = "RCP-" + (++receiptCounter);
        this.customer = customer;
        this.ticket = ticket;
        this.amountPaid = amountPaid;
        this.processedBy = processedBy;
        this.paymentTime = LocalDateTime.now();
    }

    // Getters
    public String getReceiptNumber() {
        return receiptNumber;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public double getAmountPaid() {
        return amountPaid;
    }
    public Staff getProcessedBy() {
        return processedBy;
    }
    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }


    public void printReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("============================================");
        System.out.println("          PARKING LOT RECEIPT               ");
        System.out.println("============================================");
        System.out.println("Receipt No.   : " + receiptNumber);
        System.out.println("Payment Time  : " + paymentTime.format(formatter));
        System.out.println("--------------------------------------------");
        System.out.println("Customer Name : " + customer.getName());
        System.out.println("Customer ID   : " + customer.getCustomerId());
        System.out.println("Contact       : " + customer.getContactNumber());
        System.out.println("--------------------------------------------");
        System.out.println("Ticket No.    : " + ticket.getTicketId());
        System.out.println("Slot No.      : " + ticket.getParkingSlot().getSlotNumber());
        System.out.println("Vehicle Plate : " + ticket.getVehicle().getPlateNumber());
        System.out.println("Entry Time    : " + ticket.getEntryTime().format(formatter));
        System.out.println("Exit Time     : " + paymentTime.format(formatter));
        System.out.println("--------------------------------------------");
        System.out.printf("Amount Paid   : PHP %.2f%n", amountPaid);
        System.out.println("Processed By  : " + processedBy.getName() + " (" + processedBy.getRole() + ")");
        System.out.println("============================================");
        System.out.println("       Thank you for parking with us!       ");
        System.out.println("============================================");
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptNumber='" + receiptNumber + '\'' +
                ", customer=" + customer.getName() +
                ", amountPaid=" + amountPaid +
                ", paymentTime=" + paymentTime +
                '}';
    }
}
