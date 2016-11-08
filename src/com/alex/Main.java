package com.alex;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    //this is a program that plays agram : http://www.bicyclecards.com/how-to-play/agram/
    static Scanner stringScanner = new Scanner(System.in);
    static Deck agramDeck;
    static Card activeCard;
    static Random rng;

    public static void main(String[] args) {

        rng = new Random();

        //make a modified deck to play with
        agramDeck = new Deck();
        agramDeck.makeAgramDeck();

        //create all the player objects needed (this game supports 2-5 players)
        int numberOfPlayers = 5;
        Player[] players = new Player[numberOfPlayers];

        for (int i = 0; i < numberOfPlayers; i++) {
            if (i == 0) {
                players[i] = new Player("Player 1", false);
            } else {
                players[i] = new Player("CPU Player " + i, true);
            }
        }

        //give each player 6 cards to play
        for (int i = 0; i < 6; i++) {
            for (Player p : players)
                p.addToHand(agramDeck.drawCard());
        }


        //gameplay loop
        //the game no matter what will last six turns; i'll use a for loop for the game turns
        //i'll set the human to the "round winner" so they can start round 1; after that the winner will start
        players[0].setRoundWinner(true);
        for (int i = 0; i < 6; i++) {

            System.out.println("Turn " + (i + 1));

            //determine order of turns for round
            for (Player p : players){
                p.setPlayedTurn(false);
            }
            activeCard = null;

            boolean winnerPlayedTurn = false;
            int playerTurnsTaken = 0;
            while (playerTurnsTaken < players.length) {
                for (Player p : players) {
                    //round winners go first, so skip all players before the round winner
                    if (p.isRoundWinner() && !p.isPlayedTurn()) {
                        //round winner turn
                        playerTurn(p);
                        playerTurnsTaken++;
                        p.setPlayedTurn(true);
                        winnerPlayedTurn = true;
                        p.setRoundWinner(false);
                    } else if (!p.isRoundWinner() && !p.isPlayedTurn() && winnerPlayedTurn) {
                        playerTurn(p);
                        playerTurnsTaken++;
                        p.setPlayedTurn(true);
                    }
                }
            }

            //region determine winner of round
            Card winningCard = activeCard;
            for (Player p : players) {
                if (p.getPlayedCard().getSuit().equals(winningCard.getSuit()) && p.getPlayedCard().getValue() > winningCard.getValue()) {
                    winningCard = p.getPlayedCard();
                }
            }
            for (Player p : players) {
                if (p.getPlayedCard().equals(winningCard)) {
                    p.setRoundWinner(true);
                    if (i == 5){
                        System.out.println(p.getName() + " wins the game! Congratulations!!");
                    } else {
                        System.out.println(p.getName() + " wins the round!");
                    }
                }
            }
            //endregion
        }

    }

    public static void playerTurn(Player p){
        if (p.isCpu()){
            cpuTurn(p);
        } else {
            humanTurn(p);
        }
    }

    public static void humanTurn(Player p){
        // show active card and your hand

        // choose a card to play
        System.out.println("Cards in " + p.getName() + "'s hand: ");
        for (Card c : p.getHand()) {
            System.out.print(c.toString() + "  ");
        }
        System.out.println();

        Boolean validPlay = false;
        while (!validPlay) {
            System.out.println("Please play a card from your hand.");
            String playerCardString = stringScanner.nextLine();

            //check if the card is a valid play
            //if it is a valid play, set it to the active card and remove the card from hand
            for (int j = 0; j < p.getHand().size(); j++) {
                if (p.getHand().get(j).toString().equalsIgnoreCase(playerCardString) &&
                        (activeCard == null || p.suitCardsInHand(activeCard.getSuit()) == 0 || p.getHand().get(j).getSuit().equalsIgnoreCase(activeCard.getSuit()))) {
                    p.setPlayedCard(p.getHand().get(j));
                    if (activeCard == null){
                        activeCard = p.getPlayedCard();
                    }
                    System.out.println(p.getName() + " plays " + p.getPlayedCard().toString());
                    validPlay = true;
                    break;

                }
            }
            if (!validPlay) {
                System.out.println("Invalid play.");
            }
        }
    }

    public static void cpuTurn(Player p){
        if (activeCard == null || p.suitCardsInHand(activeCard.getSuit()) == 0) {
            //if the cpu is the one setting the active card or doesn't have a card that matches suit...
            playWeakCard(p);
            if (activeCard == null){
                activeCard = p.getPlayedCard();
            }
        } else if (p.suitCardsInHand(activeCard.getSuit()) == 1) {
            //if cpu has one card of the suit, they are forced to play it.
            for (Card c : p.getHand()) {
                if (c.getSuit().equals(activeCard.getSuit())) {
                    p.setPlayedCard(c);
                    break;
                }
            }
        } else {
            //if you have two or more of the matching suit, play the weakest one to save the better value ones for later.
            LinkedList<Card> cardsOfSuit = new LinkedList<Card>();
            Card selectedCard = null;
            //add all the matching suit cards to a list
            for (Card c : p.getHand()) {
                if (c.getSuit().equals(activeCard.getSuit())) {
                    cardsOfSuit.add(c);
                }
            }
            //find the weakest value suited card
            for (Card c : cardsOfSuit) {
                if (selectedCard == null) {
                    selectedCard = c;
                } else if (selectedCard.getValue() > c.getValue()) {
                    selectedCard = c;
                }
            }
            p.setPlayedCard(selectedCard);
        }
        System.out.println(p.getName() + " plays " + p.getPlayedCard().toString());
    }

    public static void playWeakCard(Player p){
        LinkedList<Card> cards = new LinkedList<Card>();
        Card selectedCard = null;

        // first remove any cards that have a matching suit to another in hand to save for later on in the game
        for (Card c : p.getHand()) {
            if (p.suitCardsInHand(c.getSuit()) < 2) {
                cards.add(c);
            }
        }

        //play a weaker card to save better ones for later. if your hand has only suited pairs, just play the weakest card out of all of them
        if (cards.size() == 0){
            for (Card card : p.getHand()) {
                if (selectedCard == null) {
                    selectedCard = card;
                } else if (selectedCard.getValue() > card.getValue()) {
                    selectedCard = card;
                }
            }
        } else {
            //if you have single suited cards, pick the weakest one out of those
            for (Card card : cards){
                if (selectedCard == null) {
                    selectedCard = card;
                } else if (selectedCard.getValue() > card.getValue()) {
                    selectedCard = card;
                }

            }
        }
        p.setPlayedCard(selectedCard);
    }

}
