package app.com.sportflow.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "memberId")
public class Member extends User {

    @OneToMany(mappedBy = "member")
    private Set<Enrollment> enrollments;

    public Member() {}

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
