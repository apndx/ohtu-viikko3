package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

        Querybuilder query = new Querybuilder();
//        Matcher all = query.build();
//
//        System.out.println("List of all players:");
//        for (Player player : stats.matches(all)) {
//            System.out.println(player);
//        }

        Matcher team = query.playsIn("NYR").build();

        System.out.println(" ");
        System.out.println("NYR-players:");
        for (Player player : stats.matches(team)) {
            System.out.println(player);
        }

        System.out.println(" ");
        System.out.println("Team NYR, at least 10 but less than 25 goals");

        Matcher many = query.playsIn("NYR")
                .hasAtLeast(10, "goals")
                .hasFewerThan(25, "goals").build();

        for (Player player : stats.matches(many)) {
            System.out.println(player);
        }

//        System.out.println("And-lista");
//        Matcher m = new And(new HasAtLeast(10, "goals"),
//                new HasAtLeast(10, "assists"),
//                new PlaysIn("PHI")
//        );
//
//        for (Player player : stats.matches(m)) {
//            System.out.println(player);
//        }
//
//        System.out.println(" ");
//        System.out.println("Not-lista");
//        Matcher not = new Not(new HasAtLeast(1, "goals"));
//        for (Player player : stats.matches(not)) {
//            System.out.println(player);
//        }
//
//        System.out.println(" ");
//        System.out.println("Or-lista");
//
//        Matcher or = new Or(new HasAtLeast(40, "goals"),
//                new HasAtLeast(60, "assists"),
//                new HasAtLeast(85, "points")
//        );
//
//        for (Player player : stats.matches(or)) {
//            System.out.println(player);
//        }
//
//        System.out.println(" ");
//        System.out.println("Fewer than -lista");
//
//        Matcher fewer = new HasFewerThan(1, "goals");
//
//        for (Player player : stats.matches(fewer)) {
//            System.out.println(player);
//        }
//
//        System.out.println(" ");
//        System.out.println("All-lista");
//        Matcher all = new All();
//        for (Player player : stats.matches(all)) {
//            System.out.println(player);
//        }
    }
}
