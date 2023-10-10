package pl.jdacewicz.socialmediaserver.reaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.jdacewicz.socialmediaserver.user.UserFacade;

@Service
@RequiredArgsConstructor
class ReactionUserService implements ReactionUserFacade {

    private final UserFacade userFacade;
    private final ReactionFacade reactionFacade;
    private final ReactionMapper reactionMapper;

    @Override
    public ReactionUser createReactionUser(int reactionId) {
        var loggedUser = userFacade.getLoggedUser();
        var reaction = reactionFacade.getReactionById(reactionId);
        return reactionMapper.mapToReactionUser(reaction, loggedUser);
    }
}
