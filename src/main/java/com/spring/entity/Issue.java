package com.spring.entity;

import javax.persistence.*;

@Entity
@Table(name = "issue")
public class Issue {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @JoinColumn(name="customer_id")
    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    private Customer customer;

    @Column(name = "status")
    private String status;

    public Issue() {
    }

    public Issue(int id, String description, Customer customer, String status) {
        this.id = id;
        this.description = description;
        this.customer = customer;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", customer=" + customer +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
