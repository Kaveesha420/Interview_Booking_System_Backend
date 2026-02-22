package ecom.icet.service;

import ecom.icet.model.dto.UserDto;
import ecom.icet.model.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(String id);
    User getLeastBusyInterviewer();
    List<UserDto> getAllUsersByRole(String role);
    void deleteUser(String id);
}
