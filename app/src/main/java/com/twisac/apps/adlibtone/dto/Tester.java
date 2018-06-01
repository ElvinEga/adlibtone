package com.twisac.apps.adlibtone.dto;

import java.util.Arrays;
import java.util.List;

/**
 * Created by beast on 5/23/18.
 */

public class Tester {

    private String book;
    private List<String> players;

    public Tester() {

      players = Arrays.asList("hendry", "manu", "messi", "cr7");

        for (String player : players) {
            if (player.equals("manu")) {
                System.out.print("The player is manu");
            }
        }

     /*   players
                .stream()
                .filter(player -> player.equals("messi"))
                .forEach(player ->  System.out.print("Messi is in the squad") );

    }*/
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void test(){

    }
}
