package cg.tcarespb.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Builder
@Entity
@Table(name = "photoes")
@Accessors(chain = true)
public class Photo {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Boolean deleted = false;

    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_folder")
    private String fileFolder;
    @Column(name = "file_url")
    private String url;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "cloud_id")
    private String cloudId;

}
