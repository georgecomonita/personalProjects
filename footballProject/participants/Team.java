package module2.footballproject.participants;


import module2.footballproject.participants.comparator.ComparatorPlayer;
import module2.footballproject.participants.field.Player;

import java.util.ArrayList;
import java.util.Collections;

public class Team {
    private String name;
    private Coach coach;
    private ArrayList<Player> team = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public void teamStart() {
        Collections.sort(team, new ComparatorPlayer());
        System.out.println(" The starting team prepared by " + this.coach.getFirstName() + " " + this.coach.getLastName() + " , " + this.getName() + " is :");
        for (Player t : team) {
            System.out.println((team.indexOf(t) + 1) + " : " + t.getFirstName() + " " + t.getLastName() + " " + t.getPlayerNumber());
        }
    }

    public ArrayList<Player> getTeam() {
        return team;
    }

    public boolean addPlayer(Player p) {
        if (this.team.contains(p)) {
            System.out.println("This player allready is in the team. Add a different player");
            return false;
        } else {
            team.add(p);
            return true;
        }
    }
}
