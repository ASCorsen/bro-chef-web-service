package nl.broscience.Brochef.web.service.dto;

import nl.broscience.Brochef.web.service.models.Customer;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class CustomerDto {

    private Long id;
    @NotNull(message = "Please enter a Firstname")
    private String firstName;
    @NotNull(message = "Please enter a Lastname")
    private String lastName;
    @NotNull(message = "Please enter a Gender 'Male','Female','Other' ")
    private String gender;
    private int weight;
    private int height;
    @Past
    private LocalDate dob;
    private boolean isVegan;
    private boolean isVegetarian;

    public CustomerDto() {
    }

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.gender = customer.getGender();
        this.weight = customer.getWeight();
        this.height = customer.getHeight();
        this.dob = customer.getDob();
        this.isVegan = customer.isVegan();
        this.isVegetarian = customer.isVegetarian();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }
}
