package app.com.sportflow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "trainerId")
public class Trainer extends User {
    @OneToMany(mappedBy = "trainer")
    private Set<TrainingSession> trainingSessions;

    public Trainer() {}

    public Set<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(Set<TrainingSession> trainingSessions) {
        this.trainingSessions = trainingSessions;
    }
}
