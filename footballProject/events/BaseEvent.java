package module2.footballproject.events;


import module2.footballproject.participants.field.Player;

public abstract class BaseEvent {
    private int minute;
    private Player playerCausingTheEvent;

    public BaseEvent(int minute, Player playerCausingTheEvent) {
        this.minute = minute;
        this.playerCausingTheEvent = playerCausingTheEvent;
    }

    public BaseEvent() {
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Player getPlayerCausingTheEvent() {
        return playerCausingTheEvent;
    }

    public void setPlayerCausingTheEvent(Player playerCausingTheEvent) {
        this.playerCausingTheEvent = playerCausingTheEvent;
    }
}
