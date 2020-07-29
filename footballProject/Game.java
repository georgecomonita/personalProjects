package module2.footballproject;


import module2.footballproject.events.BaseEvent;
import module2.footballproject.events.Fault;
import module2.footballproject.participants.Refree;
import module2.footballproject.participants.Team;

import java.util.ArrayList;

public class Game {
    private Team homeTeam;
    private Team awayTeam;
    private Refree refree;
    private ArrayList<BaseEvent> baseEvents = new ArrayList<>();
    private int homeTeamScore = 0;
    private int awayTeamScore = 0;

    public Game(Team homeTeam, Team awayTeam, Refree refree) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.refree = refree;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Refree getRefree() {
        return refree;
    }

    public void setRefree(Refree refree) {
        this.refree = refree;
    }

    public ArrayList<BaseEvent> getBaseEvents() {
        return baseEvents;
    }

    public void setBaseEvents(ArrayList<BaseEvent> baseEvents) {
        this.baseEvents = baseEvents;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public void printGameStats() {
        for (BaseEvent g : baseEvents) {
            System.out.println(g);
        }

        System.out.println("--------------------------------------------------------------------------");
        // print the game score
        System.out.println(this.homeTeam.getName() + "- " + this.awayTeam.getName() + " : " + this.homeTeamScore + " - " + this.awayTeamScore);
        System.out.println("--------------------------------------------------------------------------");

        // print the top Scorer of the game
        /*ArrayList<Goal> goals = new ArrayList<>();
        for(BaseEvent g: baseEvents) {
            if (g instanceof Goal) {
                goals.add((Goal) g);
            }
        }
        Will revise this solution once we learn about maps
        */

        System.out.println("--------------------------------------------------------------------------");
        int nrOfFouls = 0;
        for (BaseEvent game : baseEvents) {
            if (game instanceof Fault) {
                nrOfFouls++;
            }
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(" In this game there were " + nrOfFouls + " fouls");
        System.out.println("--------------------------------------------------------------------------");
    }

    public void addEvent(BaseEvent g) {
        this.baseEvents.add(g);
    }


}

