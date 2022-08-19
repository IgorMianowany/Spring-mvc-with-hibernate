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

    @Column(name = "user_username")
    private String customer;

    @Column(name = "status")
    private String status;

    public Issue() {
    }

    public Issue(int id, String description, String customer, String status) {
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
