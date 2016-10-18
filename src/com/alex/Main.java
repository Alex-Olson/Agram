package com.alex;

import java.util.LinkedList;

public class Main {
    //this is a program that plays agram : http://www.bicyclecards.com/how-to-play/agram/

    public static void main(String[] args) {



        //todo: make a modified deck to play with
        Deck agramDeck = new Deck();
        agramDeck.makeAgramDeck();

//check if it pulled out everything right
        System.out.println(agramDeck.cardsLeft());

       //todo: give each player 6 cards to play
        LinkedList<Card> playerHand = new LinkedList<>();
        LinkedList<Card> cpuHand = new LinkedList<>();

        for (int i = 0; i < 6; i++){
            agramDeck.drawCard(playerHand);
            agramDeck.drawCard(cpuHand);
        }

//more checks
        System.out.println(agramDeck.cardsLeft());
        for (Card c : playerHand){
            System.out.println(c.toString());
        }

        //todo: set an active card
        Card activeCard;

        //todo: gameplay loop
        //the game no matter what will last six turns; i'll use a for loop for the game turns
        for (int i = 0; i < 6; i++){

            //todo: determine order of turns for round

            //todo: player turn
                // show active card and your hand
                // choose a card to play
                //check if the card is a valid play
                //if it is a valid play, set it to the active card and remove the card from hand


            //todo: cpu turn
            //determine logic for playing a card
            //play the card

            //todo: round scoring
            //determine winner of round
        }


        //todo: endgame




    }

    //todo: method for player turn
    //todo: method for cpu turn
}
