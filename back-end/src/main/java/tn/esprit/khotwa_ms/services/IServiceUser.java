package tn.esprit.khotwa_ms.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.khotwa_ms.entity.Users;

import java.io.IOException;
import java.util.List;

public interface IServiceUser {
    public Users addUser(Users user);
    public Users updateUser(Integer id, Users user) throws IOException;
    public List<Users> getAllUsers();
    public Users getUserById(Integer id);
    public void deleteById(Integer id);
    public Users signup(Users user, MultipartFile image) throws IOException;

}
