package module2.footballproject.participants.comparator;


import module2.footballproject.participants.field.Player;

import java.util.Comparator;

public class ComparatorPlayer implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o1.getPlayerNumber() - o2.getPlayerNumber();
    }
}
