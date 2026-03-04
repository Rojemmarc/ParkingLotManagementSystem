package people;

import parking.*;


public class Staff {

    public enum Role {
        ATTENDANT,
        CASHIER,
        SUPERVISOR
    }

    private String staffId;
    private String name;
    private Role role;
    private String shift;

    // Constructor
    public Staff(String staffId, String name, Role role, String shift) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.shift = shift;
    }

    // Getters
    public String getStaffId() {
        return staffId;
    }
    public String getName() {
        return name;
    }
    public Role getRole() {
        return role;
    }
    public String getShift() {
        return shift;
    }

    // Setters
    public void setRole(Role role) {
        this.role = role;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }


    public Receipt issueReceipt(Customer customer, Ticket ticket, double amountPaid) {
        System.out.println("[Staff] " + name + " (" + role + ") is issuing a receipt to " + customer.getName());
        return new Receipt(customer, ticket, amountPaid, this);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", shift='" + shift + '\'' +
                '}';
    }
}

