package module2.footballproject.events;


import module2.footballproject.participants.field.Player;

public class Goal extends BaseEvent implements Replayable {
    public Goal() {
    }

    public Goal(int minute, Player playerCausingTheEvent) {
        super(minute, playerCausingTheEvent);
    }

    @Override
    public void doReplay() {
        System.out.println("We rewatch an amazing goal scored by " + getPlayerCausingTheEvent().getLastName());
    }

    @Override
    public String toString() {
        return "In the " + super.getMinute() + " minute " + super.getPlayerCausingTheEvent().getLastName() + " " + super.getPlayerCausingTheEvent().getFirstName() + " has scored a beautiful goal";
    }
}
