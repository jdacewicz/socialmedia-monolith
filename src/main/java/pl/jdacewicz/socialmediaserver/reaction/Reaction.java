package pl.jdacewicz.socialmediaserver.reaction;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_reactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(value = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
public class Reaction {

    final static String MAIN_REACTIONS_DIRECTORY_URL = "data/reactions";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String imageName;

    public String getImageUrl() {
        return getDirectoryUrl() + "/" + this.imageName;
    }

    public String getDirectoryUrl() {
        return MAIN_REACTIONS_DIRECTORY_URL + "/" + this.id;
    }
}
