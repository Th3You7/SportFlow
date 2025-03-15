package app.com.sportflow.dto;

import app.com.sportflow.entity.Member;
import app.com.sportflow.enums.EnrollmentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EnrollmentDTO {
    private long enrollmentId;
    private LocalDateTime enrollmentDate;
    private EnrollmentStatus enrollmentStatus;
    private UserDTO member;
    private TrainingSessionDTO trainingSession;

    public EnrollmentDTO(long enrollmentId, LocalDateTime enrollmentDate, EnrollmentStatus enrollmentStatus, UserDTO member, TrainingSessionDTO trainingSession) {
        this.enrollmentId = enrollmentId;
        this.enrollmentDate = enrollmentDate;
        this.enrollmentStatus = enrollmentStatus;
        this.member = member;
        this.trainingSession = trainingSession;
    }

    public EnrollmentDTO(LocalDateTime enrollmentDate, EnrollmentStatus enrollmentStatus, UserDTO member, TrainingSessionDTO trainingSession) {
        this.enrollmentDate = enrollmentDate;
        this.enrollmentStatus = enrollmentStatus;
        this.member = member;
        this.trainingSession = trainingSession;
    }

    public long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public UserDTO getMember() {
        return member;
    }

    public void setMember(UserDTO member) {
        this.member = member;
    }

    public TrainingSessionDTO getTrainingSession() {
        return trainingSession;
    }

    public void setTrainingSession(TrainingSessionDTO trainingSession) {
        this.trainingSession = trainingSession;
    }
}
