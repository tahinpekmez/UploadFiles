package fileproject.uploadfile.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "filedb")
public class File {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "FileID", columnDefinition = "varchar(255)")
    private UUID id;

    @Column(name = "FileName")
    private String name;

    @Column(name = "FileExtension")
    private String type;

    @Lob
    private byte[] data;

    public File(String name, String type, byte[] data){

        this.data = data;
        this.name = name;
        this.type = type;
    }
}
