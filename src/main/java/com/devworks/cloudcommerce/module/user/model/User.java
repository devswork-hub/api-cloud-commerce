package com.devworks.cloudcommerce.module.user.model;

import com.devworks.cloudcommerce.shared.model.BaseEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private String cpf;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_country_code")
    private String phoneCountryCode;

    @Column(name = "phone_code_area")
    private String phoneCodeArea;

    public User() {
    }

    public User(String firstName, String lastName, String email, String cpf, String phoneNumber, String phoneCountryCode, String phoneCodeArea) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.phoneCountryCode = phoneCountryCode;
        this.phoneCodeArea = phoneCodeArea;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneCountryCode() {
        return phoneCountryCode;
    }

    public void setPhoneCountryCode(String phoneCountryCode) {
        this.phoneCountryCode = phoneCountryCode;
    }

    public String getPhoneCodeArea() {
        return phoneCodeArea;
    }

    public void setPhoneCodeArea(String phoneCodeArea) {
        this.phoneCodeArea = phoneCodeArea;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(cpf, user.cpf) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(phoneCountryCode, user.phoneCountryCode) && Objects.equals(phoneCodeArea, user.phoneCodeArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, cpf, phoneNumber, phoneCountryCode, phoneCodeArea);
    }
}
