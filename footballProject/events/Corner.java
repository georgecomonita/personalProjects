package module2.footballproject.events;


import module2.footballproject.participants.field.Player;

public class Corner extends BaseEvent {

    public Corner(int minute, Player playerCausingTheEvent) {
        super(minute, playerCausingTheEvent);
    }

    @Override
    public String toString() {
        return "In the " + super.getMinute() + " minute " + super.getPlayerCausingTheEvent().getLastName() + " takes a shot...but was deflected. It's a corner!";
    }
}
