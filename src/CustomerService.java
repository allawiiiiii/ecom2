public class CustomerService {
    private CustomerRepo customerRepo = new CustomerRepo();

    public void registerCustomer(String name, String email) {
        customerRepo.registerCustomer(name, email);
    }

    public void updateCustomerEmail(String name, String newEmail) {
        customerRepo.updateCustomerEmail(name, newEmail);
    }

    public boolean customerExists(String name) {
        return customerRepo.customerExists(name);
    }
}
