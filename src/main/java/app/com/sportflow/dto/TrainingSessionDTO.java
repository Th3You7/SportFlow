package app.com.sportflow.dto;

import app.com.sportflow.enums.ClubDomain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TrainingSessionDTO {
    private long sessionId;
    private String sessionName;
    private String sessionDescription;
    private ClubDomain sessionDomain;
    private long trainerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TrainingSessionDTO(long sessionId, String sessionName, String sessionDescription, ClubDomain sessionDomain, long trainerId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionDescription = sessionDescription;
        this.sessionDomain = sessionDomain;
        this.trainerId = trainerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public TrainingSessionDTO(String sessionName, String sessionDescription, ClubDomain sessionDomain, long trainerId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sessionName = sessionName;
        this.sessionDescription = sessionDescription;
        this.sessionDomain = sessionDomain;
        this.trainerId = trainerId;
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

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
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
