package data_structures;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		AVLTree<Integer,String> alv = new AVLTree<>();
		alv.insert(31, "31");//1
		alv.insert(32, "32");
		alv.insert(33, "33");
		alv.insert(5, "5");
		alv.insert(24, "24");
		alv.insert(36, "36");//
		alv.insert(331, "331");//
		alv.insert(351, "351");//
		alv.insert(32, "32");//
		alv.insert(391, "391");//
		alv.insert(135, "135");
		alv.insert(035, "035");
		alv.insert(396, "396");//
		alv.insert(30, "30");//
		alv.insert(31, "31");//
		alv.insert(31, "31");//1
		alv.insert(32, "32");
		alv.insert(33, "33");
		alv.insert(5, "5");
		alv.insert(24, "24");
		alv.insert(36, "36");//
		alv.insert(331, "331");//
		alv.insert(351, "351");//
		alv.insert(32, "32");//
		alv.insert(391, "391");//
		alv.insert(135, "135");
		alv.insert(035, "035");
		alv.insert(396, "396");//
		alv.insert(30, "30");//
		alv.insert(31, "31");//
		alv.insert(31, "31");//1
		alv.insert(32, "32");
		alv.insert(33, "33");
		alv.insert(5, "5");
		alv.insert(24, "24");
		alv.insert(36, "36");//
		alv.insert(331, "331");//
		alv.insert(351, "351");//
		alv.insert(32, "32");//
		alv.insert(391, "391");//
		alv.insert(135, "135");
		alv.insert(035, "035");
		alv.insert(396, "396");//
		alv.insert(30, "30");//
		alv.insert(31, "31");//
		alv.insert(31, "31");//1
		alv.insert(32, "32");
		alv.insert(33, "33");
		alv.insert(5, "5");
		alv.insert(24, "24");
		alv.insert(36, "36");//
		alv.insert(331, "331");//
		alv.insert(351, "351");//
		alv.insert(32, "32");//
		alv.insert(391, "391");//
		alv.insert(135, "135");
		alv.insert(035, "035");
		alv.insert(396, "396");//
		alv.insert(30, "30");//
		alv.insert(31, "31");//
		alv.insert(31, "31");//1
		alv.insert(32, "32");
		alv.insert(33, "33");
		alv.insert(5, "5");
		alv.insert(24, "24");
		alv.insert(36, "36");//
		alv.insert(331, "331");//
		alv.insert(351, "351");//
		alv.insert(32, "32");//
		alv.insert(391, "391");//
		alv.insert(135, "135");
		alv.insert(035, "035");
		alv.insert(396, "396");//
		alv.insert(30, "30");//
		alv.insert(31, "31");//
		
		ArrayList<String> array = alv.sensitiveSearch(396);
		
		System.out.println(array.size());
		
		
		
	}

}
