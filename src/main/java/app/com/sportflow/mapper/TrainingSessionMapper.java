package app.com.sportflow.mapper;

import app.com.sportflow.dao.TrainingSessionDAO;
import app.com.sportflow.dto.TrainingSessionDTO;
import app.com.sportflow.entity.TrainingSession;

public class TrainingSessionMapper {
    public static TrainingSession toTrainingSessionEntity(TrainingSessionDTO trainingSessionDTO) {
        TrainingSessionDAO trainingSessionDAO = new TrainingSessionDAO();
        return trainingSessionDAO.getSession(trainingSessionDTO.getSessionId());
    }

    public static TrainingSessionDTO toTrainingSessionDTO(TrainingSession trainingSession) {
        return new TrainingSessionDTO(
                trainingSession.getSessionId(),
                trainingSession.getSessionName(),
                trainingSession.getSessionDescription(),
                trainingSession.getDomain(),
                trainingSession.getTrainer().getUserId(),
                trainingSession.getCreatedAt(),
                trainingSession.getUpdatedAt()

        );
    }
}
