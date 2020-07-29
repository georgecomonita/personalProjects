package module2.footballproject;

import module2.footballproject.participants.Coach;
import module2.footballproject.participants.Refree;
import module2.footballproject.participants.Team;
import module2.footballproject.participants.field.Player;

import java.util.ArrayList;
import java.util.Random;

public class GameSimulator {
    public static void main(String[] args) {

        // create the game and initialize the events in the game
   Game myGame = setupGame();

        // simulate the game and the random events which occur
        for (int i = 1; i <= 90; i++) {
            simulateGame(myGame, i);
        }

        if (myGame.getAwayTeamScore() == myGame.getHomeTeamScore()) {
            System.out.println("The match will need extra time.");
            for (int i = 91; i <= 120; i++) {
                simulateGame(myGame, i);
            }
        }

        System.out.println(" ===========================================================");
        myGame.printGameStats();

        replayEvents(myGame.getBaseEvents());
    }

    private static void simulateGame(Game game1, int i) {
        // Fault Event
        if (isEventTriggered(0.1)) {
            if (isHomeTeamEvent()) {
                game1.addEvent(new Fault(i, game1.getHomeTeam().getTeam().get(getRandomPlayer())));
            } else {
                game1.addEvent(new Fault(i, game1.getAwayTeam().getTeam().get(getRandomPlayer())));
            }
        }

        // Corner Event
        if (isEventTriggered(0.2)) {
            if (isHomeTeamEvent()) {
                game1.addEvent(new Corner(i, game1.getHomeTeam().getTeam().get(getRandomPlayer())));
            } else {
                game1.addEvent(new Corner(i, game1.getAwayTeam().getTeam().get(getRandomPlayer())));
            }
        }

        // Hands Event
        if (isEventTriggered(0.1)) {
            if (isHomeTeamEvent()) {
                game1.addEvent(new Hands(i, game1.getHomeTeam().getTeam().get(getRandomPlayer())));
            } else {
                game1.addEvent(new Hands(i, game1.getAwayTeam().getTeam().get(getRandomPlayer())));
            }
        }

        // Goal Event
        if (isEventTriggered(0.04)) {
            if (isHomeTeamEvent()) {
                game1.addEvent(new Goal(i, game1.getHomeTeam().getTeam().get(getRandomPlayer())));
                game1.setHomeTeamScore(game1.getHomeTeamScore() + 1);
            } else {
                game1.addEvent(new Goal(i, game1.getAwayTeam().getTeam().get(getRandomPlayer())));
                game1.setAwayTeamScore(game1.getAwayTeamScore() + 1);
            }
        }

        // Offside Event
        if (isEventTriggered(0.08)) {
            if (isHomeTeamEvent()) {
                game1.addEvent(new Offside(i, game1.getHomeTeam().getTeam().get(getRandomPlayer())));
            } else {
                game1.addEvent(new Offside(i, game1.getAwayTeam().getTeam().get(getRandomPlayer())));
            }
        }
    }

    public static Game setupGame() {
        // Create the players for the first team
        Player pl1 = new Player("Denis", "Rusu", 27, "Politehnica Iasi", 12);
        Player pl2 = new Player("Cosmin", "Frasinescu", 28, "Politehnica Iasi", 23);
        Player pl3 = new Player("Marius", "Mihalachi", 32, "Politehnica Iasi", 4);
        Player pl4 = new Player("Rodny Lopes", "Cabral", 27, "Politehnica Iasi", 2);
        Player pl5 = new Player("Andrei", "Radu", 31, "Politehnica Iasi", 3);
        Player pl6 = new Player("Narcis Iulian", "Badic", 25, "Politehnica Iasi", 5);
        Player pl7 = new Player("Doru", "Popadiuc", 32, "Politehnica Iasi", 7);
        Player pl8 = new Player(" Louis", "Platini", 24, "Politehnica Iasi", 20);
        Player pl9 = new Player("Nicandro", "Breeveld", 29, "Politehnica Iasi", 86);
        Player pl10 = new Player("Andrei", "Cristea", 34, "Politehnica Iasi", 10);
        Player pl11 = new Player("Adrian", "Balan", 26, "Politehnica Iasi", 11);

        // create the first team then add the players
        Team poli = new Team("Politehnica Iasi");
        poli.addPlayer(pl1);
        poli.addPlayer(pl2);
        poli.addPlayer(pl3);
        poli.addPlayer(pl4);
        poli.addPlayer(pl5);
        poli.addPlayer(pl6);
        poli.addPlayer(pl7);
        poli.addPlayer(pl8);
        poli.addPlayer(pl9);
        poli.addPlayer(pl10);
        poli.addPlayer(pl11);

        // create the coach and display the starting team
        Coach tc1 = new Coach("Mihai", "Teja", 45);
        poli.setCoach(tc1);
        poli.teamStart();
        System.out.println(" ===========================================================");

        // create the players for the second team
        Player pl21 = new Player("Cristian", "Balgradean", 31, "FCSB ", 34);
        Player pl22 = new Player("Bogdan", "Planic", 27, "FCSB ", 16);
        Player pl23 = new Player("Ionut", "Pantiru", 23, "FCSB ", 3);
        Player pl24 = new Player("Aristidis", "Soiledis", 28, "FCSB ", 18);
        Player pl25 = new Player("Ovidiu", "Popescu", 25, "FCSB ", 23);
        Player pl26 = new Player("Florin", "Tanasa", 24, "FCSB ", 10);
        Player pl27 = new Player("Olimpiu", "Morutan", 20, "FCSB ", 11);
        Player pl28 = new Player("Dragos", "Nedelcu", 22, "FCSB ", 6);
        Player pl29 = new Player("Florinel", "Coman", 21, "FCSB ", 7);
        Player pl30 = new Player("Dennis", "Mann", 21, "FCSB ", 98);
        Player pl31 = new Player("Valentin", "Cretu", 30, "FCSB ", 2);

        // create the second team and add the players
        Team fcsb = new Team("FCSB");
        fcsb.addPlayer(pl21);
        fcsb.addPlayer(pl22);
        fcsb.addPlayer(pl23);
        fcsb.addPlayer(pl24);
        fcsb.addPlayer(pl25);
        fcsb.addPlayer(pl26);
        fcsb.addPlayer(pl27);
        fcsb.addPlayer(pl28);
        fcsb.addPlayer(pl29);
        fcsb.addPlayer(pl30);
        fcsb.addPlayer(pl31);

        // create the second coach and print the starting second team
        Coach tc2 = new Coach("Bogdan", "Vintila", 44);
        fcsb.setCoach(tc2);
        fcsb.teamStart();
        System.out.println(" ===========================================================");
        Refree ref = new Refree("Cristi", "Paun", 45);

        // create the game and initialize the events in the game
        return new Game(poli, fcsb, ref);
    }

    public static boolean isEventTriggered(double percentage) {
        Random random = new Random();
        return percentage > random.nextDouble();
    }

    public static int getRandomPlayer() {
        Random random = new Random();
        return random.nextInt(11);
    }

    public static boolean isHomeTeamEvent() {
        return isEventTriggered(0.5);
    }

    public static void replayEvents(ArrayList<BaseEvent> allEvents) {
        for (BaseEvent event : allEvents) {
            if (event instanceof Replayable) {
                ((Replayable) event).doReplay();
            }
        }
    }
}
