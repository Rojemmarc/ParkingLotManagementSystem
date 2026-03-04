package people;

public class Customer {

    private String customerId;
    private String name;
    private String contactNumber;
    private String email;


    public Customer(String customerId, String name, String contactNumber, String email) {
        this.customerId = customerId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() {  return email; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
