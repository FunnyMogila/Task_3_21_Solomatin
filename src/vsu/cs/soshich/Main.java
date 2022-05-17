package vsu.cs.soshich;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TransferQueue;

public class Main
{
    public static void main(String[] args)
    {
        Queue<String> player1 = new LinkedList<>();
        Queue<String> player2 = new LinkedList<>();

        ArrayList<String> deck = new ArrayList<>();
        ArrayList<Integer> deckCards = new ArrayList<>();

        doADeck(deck);
        randomCard(player1, deck, deckCards);
        randomCard(player2, deck, deckCards);

        while (player1.size() < 36 && player2.size() < 36)
        {
            String pl1Card = player1.poll();
            String pl2Card = player2.poll();

            System.out.println("Player 1        Player 2");
            System.out.println(pl1Card + "              " + pl2Card);

            if (valueOfCard(pl1Card) > valueOfCard(pl2Card))
            {
                player1.add(pl1Card);
                player1.add(pl2Card);
                System.out.println("Первый игрок выйграл раунд!");
            }
            if (valueOfCard(pl1Card) < valueOfCard(pl2Card))
            {
                player2.add(pl1Card);
                player2.add(pl2Card);
                System.out.println("Второй игрок выйграл раунд!");
            }
            if (valueOfCard(pl1Card) == valueOfCard(pl2Card))
            {
                ArrayList<String> cardsForConflict = new ArrayList<>();
                cardsForConflict.add(pl1Card);
                cardsForConflict.add(pl2Card);

                while (true)
                {
                    if (player1.size() > 0 && player2.size() > 0)
                    {
                        pl1Card = player1.poll();
                        pl2Card = player2.poll();

                        if (valueOfCard(pl1Card) > valueOfCard(pl2Card))
                        {
                            player1.add(pl1Card);
                            player1.add(pl2Card);
                            for (int i = 0; i < cardsForConflict.size(); i++)
                            {
                                player1.add(cardsForConflict.get(i));
                            }
                            System.out.println("Первый игрок выйграл это спор!");
                            break;
                        }
                        if (valueOfCard(pl1Card) < valueOfCard(pl2Card))
                        {
                            player2.add(pl1Card);
                            player2.add(pl2Card);
                            for (int i = 0; i < cardsForConflict.size(); i++)
                            {
                                player2.add(cardsForConflict.get(i));
                            }
                            System.out.println("Второй игрок выйграл это спор!");
                            break;
                        }
                        if (valueOfCard(pl1Card) == valueOfCard(pl2Card))
                        {
                            cardsForConflict.add(pl1Card);
                            cardsForConflict.add(pl2Card);
                        }
                    }
                    else
                    {
                        if (player1.size() == 0)
                        {
                            for (int i = 0; i < cardsForConflict.size(); i++)
                            {
                                player1.add(cardsForConflict.get(i));

                            }
                            System.out.println("Первый игрок выйграл это спор!");
                            break;
                        }
                        if (player2.size() == 0)
                        {
                            for (int i = 0; i < cardsForConflict.size(); i++)
                            {
                                player2.add(cardsForConflict.get(i));
                            }
                            System.out.println("Второй игрок выиграл этот спор!");
                            break;
                        }
                    }
                }
            }
        }
        if (player1.size() == 36)
        {
            System.out.println("Первый игрок выиграл!");
        }
        else
        {
            System.out.println("Второй игрок выиграл!");
        }
    }

    private static void randomCard(Queue<String> queue, ArrayList<String> deck, ArrayList<Integer> deckCards)
    {
        while (queue.size() < deck.size() / 2)
        {
            int numberOfCard = (int)(Math.random() * 36);
            boolean flag = false;
            for (int i = 0; i < deckCards.size(); i++)
            {
                if (numberOfCard == deckCards.get(i))
                {
                    flag = true;
                    break;
                }
            }
            if (!flag)
            {
                queue.add(deck.get(numberOfCard));
                deckCards.add(numberOfCard);
            }
        }
    }

    private static void doADeck(ArrayList<String> deck)
    {
        // diamonds - бубни - d
        // hearts - черви - h
        // clubs - крести - c
        // spades - пики - s
        // jack - валет - j
        // queen - дама - q
        // king - король - k
        // ace - туз - a
        for (int i = 0; i < 4; i++)
        {
            String suit = "d";
            if (i == 1)
                suit = "h";
            if (i == 2)
                suit = "c";
            if (i == 3)
                suit = "s";
            for (int j = 6; j < 11; j++)
            {
                deck.add(suit + j);
            }
            for (int j = 0; j < 4; j++)
            {
                String card = "j";
                if (j == 1)
                    card = "q";
                if (j == 2)
                    card = "k";
                if (j == 3)
                    card = "a";
                deck.add(suit + card);
            }
        }
    }

    private static int valueOfCard(String card)
    {
        int size = card.length();
        if (card.charAt(size - 1) == '6')
            return 6;
        if (card.charAt(size - 1) == '7')
            return 7;
        if (card.charAt(size - 1) == '8')
            return 8;
        if (card.charAt(size - 1) == '9')
            return 9;
        if (card.charAt(size - 1) == '0')
            return 10;
        if (card.charAt(size - 1) == 'j')
            return 11;
        if (card.charAt(size - 1) == 'q')
            return 12;
        if (card.charAt(size - 1) == 'k')
            return 13;
        if (card.charAt(size - 1) == 'a')
            return 14;
        return 0;
    }
}
