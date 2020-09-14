package com.hibernate.contactmgr.model;

import javax.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firsName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private Long phone;

    public Contact() {}
    public Contact(ContactBuilder builder){
        this.firsName= builder.firsName;
        this.lastName= builder.lastName;
        this.email = builder.email;
        this.phone= builder.phone;
    }
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
    public static class ContactBuilder{
        private String firsName;
        private String lastName;
        private String email;
        private Long phone;
        public ContactBuilder (String firsName, String lastName){
            this.firsName=firsName;
            this.lastName=lastName;
        }
        public ContactBuilder withEmail(String email){
            this.email=email;
            return this;
        }
        public ContactBuilder withPhone(Long phone){
            this.phone=phone;
            return this;
        }
        public Contact build(){
            return new Contact(this);
        }
    }
}
