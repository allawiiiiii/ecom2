public class CustomerService {
    private CustomerRepo customerRepo;

    public CustomerService() {
        // Initialize the repository
        customerRepo = new CustomerRepo();
    }


    public void registerCustomer(Customer customer) {
        // Delegate the insertion to the repository layer
        customerRepo.registerCustomer(customer);
    }


    public void updateCustomerEmail(String name, String newEmail) {
        customerRepo.updateCustomerEmail(name, newEmail);
    }


    public boolean customerExists(String name) {
        return customerRepo.customerExists(name);
    }
}
