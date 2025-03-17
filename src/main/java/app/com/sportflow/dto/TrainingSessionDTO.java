package app.com.sportflow.dto;

import app.com.sportflow.entity.Trainer;
import app.com.sportflow.enums.ClubDomain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TrainingSessionDTO {
    private long sessionId;
    private String sessionName;
    private String sessionDescription;
    private ClubDomain sessionDomain;
    private UserDTO trainer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TrainingSessionDTO(long sessionId, String sessionName, String sessionDescription, ClubDomain sessionDomain, UserDTO trainer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionDescription = sessionDescription;
        this.sessionDomain = sessionDomain;
        this.trainer = trainer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public TrainingSessionDTO(String sessionName, String sessionDescription, ClubDomain sessionDomain, UserDTO trainer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sessionName = sessionName;
        this.sessionDescription = sessionDescription;
        this.sessionDomain = sessionDomain;
        this.trainer = trainer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public void setSessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
    }

    public ClubDomain getSessionDomain() {
        return sessionDomain;
    }

    public void setSessionDomain(ClubDomain sessionDomain) {
        this.sessionDomain = sessionDomain;
    }

    public UserDTO getTrainer() {
        return trainer;
    }

    public void setTrainer(UserDTO trainer) {
        this.trainer = trainer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
