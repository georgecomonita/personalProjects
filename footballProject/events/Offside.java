package module2.footballproject.events;

import module2.footballproject.participants.field.Player;

public class Offside extends BaseEvent {
    public Offside(int minute, Player playerCausingTheEvent) {
        super(minute, playerCausingTheEvent);
    }

    @Override
    public String toString() {
        return "In the " + super.getMinute() + " minute " + super.getPlayerCausingTheEvent().getLastName() + " has been caught in offside!!";
    }
}
