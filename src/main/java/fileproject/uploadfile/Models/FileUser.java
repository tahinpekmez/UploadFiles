package fileproject.uploadfile.Models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@RequiredArgsConstructor
@Table(name = "user")
public class FileUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public FileUser(String username, String password){
        this.username = username;
        this.password = password;

    }

}