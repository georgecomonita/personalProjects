package module2.footballproject.participants.field;


import module2.footballproject.participants.BaseParticipant;

public class Player extends BaseParticipant {
    private String team;
    private int playerNumber;
    private int foulsCommited = 0;
    private int yellowCards = 0;

    public Player(String firstName, String lastName, int age, String team, int playNumber) {
        super(firstName, lastName, age);
        this.team = team;
        this.playerNumber = playNumber;
    }

    public Player() {
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getFoulsCommited() {
        return foulsCommited;
    }

    public void setFoulsCommited(int foulsCommited) {
        this.foulsCommited = foulsCommited;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    @Override
    public String toString() {
        return super.getFirstName() + " " + super.getLastName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Player)) {
            return false;

        }
        Player p = (Player) obj;
        return super.getFirstName().equals(p.getFirstName()) && super.getLastName().equals(p.getLastName());
    }

}
