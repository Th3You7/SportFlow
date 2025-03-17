package app.com.sportflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Admin extends User {
    public Admin() { }
}
