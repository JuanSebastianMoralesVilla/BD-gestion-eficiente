package data_structures;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		AVLTree<Integer,String> alv = new AVLTree<>();
		alv.insert(31, "31");//1
		alv.insert(72, "72");
		alv.insert(7, "7");
		alv.insert(5, "5");
		alv.insert(24, "24");
		alv.insert(36, "36");//
		alv.insert(331, "331");//
		alv.insert(351, "351");//
		alv.insert(32, "32");//
		//alv.sensitiveSearch(3);
		//System.out.println(array);
		alv.insert(391, "391");//
		alv.insert(135, "135");
		alv.insert(035, "035");
		alv.insert(396, "396");//
		alv.insert(30, "30");//
		//alv.sensitiveSearch(3);
		//.out.println(array);
		alv.deleteValue(41);
		alv.deleteValue(72);
		alv.deleteValue(7);
		alv.deleteValue(31);
		alv.sensitiveSearch(3);
		//System.out.println(array);
	}

}
