package assignment1;
import java.io.*;

import java.util.*;

public class pokerClass {
	public handClass AIP = new handClass();
	public handClass handToBeat = new handClass();
	public deckClass deck = new deckClass();
	
	
	public List<String[]> readFile(String fileName) {
		List<String[]> games = new ArrayList<String[]>();
		String path = "C:\\Users\\angus\\Desktop\\";
		;
		path = path + fileName;
		File file = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  
		  String cards; 
		  try {
			while ((cards = br.readLine()) != null) {
				String[] split = cards.split("\\s+");
				games.add(split);
			}
			
				
			    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  return games;
	}
	
	public void constructCards(String[] cards) {
		for(int i =0;i<5;i++) {
			
			String substring = cards[i].substring(0, 1);		
			String remainder = cards[i].substring(1);
			//System.out.println(substring + remainder);
			cardClass cardToAdd = new cardClass(remainder,substring);
			AIP.addCard(cardToAdd);
		}
		for(int z = 5 ; z<10;z++) {
			String substring = cards[z].substring(0, 1);		
			String remainder = cards[z].substring(1);
			//System.out.println(substring + remainder);
			cardClass cardToAdd = new cardClass(remainder,substring);
			handToBeat.addCard(cardToAdd);
		}
		if(cards.length>10) {
			for(int t = 10;t<cards.length;t++) {
				String substring = cards[t].substring(0, 1);		
				String remainder = cards[t].substring(1);
				cardClass cardToAdd = new cardClass(remainder,substring);
				deck.addToDeck(cardToAdd);
			}
		}
		//System.out.println(deck.checkLength());
		/*
		for(cardClass card : deck.returnDeck()) {
			System.out.println("Card is [" + card.returnSuitName() + card.returnCardRank() +"]");
		}*/
		
	}
	
	public int returnHandRes(List<cardClass> cards) {
		handIdentifierClass HIC = new handIdentifierClass();
		return HIC.pokerHand(cards);
	}
	
	public  void game() {
		handIdentifierClass HIC = new handIdentifierClass();
		
		int pokerAI;
		int pokerBeat;
		List<cardClass> cardsToSwap = new ArrayList<cardClass>();
		
		pokerAI = returnHandRes(AIP.returnHand());
		pokerBeat = returnHandRes(handToBeat.returnHand());
		
		if(pokerAI==0) {
			cardsToSwap = HIC.determineOneSwap(AIP.returnHand());
			
			for(cardClass card : cardsToSwap) {
				System.out.println("Card is [" + card.returnSuitName() + card.returnCardRank() +"]");
			}
			
			AIP.swap(deck, cardsToSwap);
			
			//System.out.println(HIC.determineOneSwap(AIP.returnHand()).returnSuitName());
		}
		pokerAI = HIC.pokerHand(AIP.returnHand());
		System.out.println("POKER AI : " + pokerAI);
		System.out.println("POKER BEAT : " + pokerBeat);
		
	}
	
}
	
	


