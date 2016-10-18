package com.alex;

import sun.awt.image.ImageWatched;

import java.util.Collections;
import java.util.LinkedList;

public class Deck {


    private String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
    private String[] values = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    private LinkedList<Card> deck = new LinkedList<>();

    public Deck(){
        for (String suit : suits){
            for (String value : values){
                deck.add(new Card(suit, value));
            }
        }
        //shuffle the deck after all the cards are made
        Collections.shuffle(deck);
    }

    public void makeAgramDeck(){
            deck.removeIf(card -> card.getValue() == "Jack"
                    || card.getValue() == "Queen"
                    || card.getValue() == "King"
                    || card.getValue() == "2"
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
