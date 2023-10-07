package pl.jdacewicz.socialmediaserver.reaction;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_reactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PACKAGE)
public class Reaction {

    final static String MAIN_REACTIONS_DIRECTORY_URL = "data/reactions";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String imageName;

    @OneToMany(mappedBy = "reaction")
    private List<ReactionUser> reactionUsers;

    public Reaction(String name, String imageName) {
        this.name = name;
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return getDirectoryUrl() + "/" + this.imageName;
    }

    public String getDirectoryUrl() {
        return MAIN_REACTIONS_DIRECTORY_URL + "/" + this.id;
    }
}
