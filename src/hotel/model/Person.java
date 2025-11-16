package hotel.model;

import java.io.Serializable;
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int customerCounter = 1;
    private String id;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Person(String id, String name,String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public static String generateCustomerId() {
        return "C" + customerCounter++;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getAddress() {
        return address;
    }
}
