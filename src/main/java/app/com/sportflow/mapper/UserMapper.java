package app.com.sportflow.mapper;

import app.com.sportflow.dao.UserDAO;
import app.com.sportflow.dto.UserDTO;
import app.com.sportflow.entity.Member;
import app.com.sportflow.entity.Trainer;
import app.com.sportflow.entity.User;
import app.com.sportflow.enums.UserRole;

public class UserMapper {

    public static User toUserEntity(UserDTO userDTO) {
        UserDAO userDAO = new UserDAO();
        return userDAO.getUserById(userDTO.getUserId());
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                UserMapper.getUserRole(user)
        );
    }

    private static UserRole getUserRole(User user) {
        if(user instanceof Member) return UserRole.MEMBER;
        if(user instanceof Trainer) return UserRole.TRAINER;
        return UserRole.ADMIN;
    }
}
