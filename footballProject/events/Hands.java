package module2.footballproject.events;


import module2.footballproject.participants.field.Player;

public class Hands extends BaseEvent {
    public Hands(int minute, Player playerWichDoes) {
        super(minute, playerWichDoes);
    }

    @Override
    public String toString() {
        return "In the " + super.getMinute() + " minute " + super.getPlayerCausingTheEvent().getLastName() + " has put the hand on the ball. Freekick!!";
    }
}
