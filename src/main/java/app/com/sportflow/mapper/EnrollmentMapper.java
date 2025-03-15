package app.com.sportflow.mapper;

import app.com.sportflow.dao.EnrollmentDAO;
import app.com.sportflow.dto.EnrollmentDTO;
import app.com.sportflow.entity.Enrollment;

public class EnrollmentMapper {

    public static Enrollment toEnrollmentEntity(EnrollmentDTO enrollmentDTO) {
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        return enrollmentDAO.getEnrollment(enrollmentDTO.getEnrollmentId());
    }

    public static EnrollmentDTO toEnrollmentDTO(Enrollment enrollment) {
        return new EnrollmentDTO(
                enrollment.getEnrollmentId(),
                enrollment.getEnrollmentDate(),
                enrollment.getStatus(),
                UserMapper.toUserDTO(enrollment.getMember()),
                TrainingSessionMapper.toTrainingSessionDTO(enrollment.getSession())
        );
    }
}
