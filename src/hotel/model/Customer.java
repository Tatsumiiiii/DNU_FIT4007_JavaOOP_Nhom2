package hotel.model;

public class Customer extends Person{

    public Customer(String name , String phone , String email ,String address){
        super(generateCustomerId(),name, phone, email,address);
    }
}

