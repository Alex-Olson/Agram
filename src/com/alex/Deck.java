package com.alex;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {


    private String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
    //jack = 11, queen = 12, king = 13, ace = 14
    private int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    private LinkedList<Card> deck = new LinkedList<>();

    public Deck(){
        for (String suit : suits){
            for (int value : values){
                deck.add(new Card(suit, value));
            }
        }
        //shuffle the deck after all the cards are made
        Collections.shuffle(deck);
    }

    //agram doesn't play with jacks, queens, kings, 2s, or the ace of spades.
    public void makeAgramDeck(){
            deck.removeIf(card -> card.getValue() == 11
                    || card.getValue() == 12
                    || card.getValue() == 13
                    || card.getValue() == 2
                    || card.toString().equals("Ace of Spades"));

        Collections.shuffle(deck);
    }

    public void drawCard(LinkedList<Card> hand){
        hand.push(deck.pop());
    }

    public Card drawCard(){
        return deck.pop();
    }

    public int cardsLeft(){
        return deck.size();
    }













}
