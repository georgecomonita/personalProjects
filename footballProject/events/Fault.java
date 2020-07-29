package module2.footballproject.events;

import module2.footballproject.participants.field.Player;

public class Fault extends BaseEvent implements Replayable {
    public Fault(int minute, Player playerCausingTheEvent) {
        super(minute, playerCausingTheEvent);
    }

    @Override
    public void doReplay() {
        System.out.println("Horrible fault caused by " + getPlayerCausingTheEvent().getLastName());
    }

    @Override
    public String toString() {
        return "In the " + super.getMinute() + " minute " + super.getPlayerCausingTheEvent().getLastName() + " has made a big fault !";
    }
}
