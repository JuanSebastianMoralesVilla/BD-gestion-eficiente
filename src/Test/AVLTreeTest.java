package Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data_structures.AVLTree;
import data_structures.Node;

class AVLTreeTest {

	// ------------------------------
	// Associations
	// ------------------------------

	private Node<Integer, String> nodo1;
	private AVLTree<Integer, String> myTree;

	// ------------------------------
	// Setups
	// ------------------------------

	public void setup1() {
		myTree = new AVLTree<Integer, String>();
		nodo1 = new Node<Integer, String>(1, "1234");
	}

	public void setup2() {
		myTree = new AVLTree<Integer, String>();
		nodo1 = new Node<Integer, String>(1, "1234");

	}

	public void setup3() {
		myTree = new AVLTree<Integer, String>();
		nodo1 = new Node<Integer, String>(1, "01");

	}

	public void setup4() {
		myTree = new AVLTree<Integer, String>();
		myTree.insert(2, "02");
		myTree.insert(1, "01");

		myTree.insert(5, "05");
	}

	public void setup5() {
		myTree = new AVLTree<Integer, String>();
		myTree.insert(2, "02");

		myTree.insert(5, "05");
	}

	public void setup6() {
		myTree = new AVLTree<Integer, String>();

		myTree.insert(1, "01");
		myTree.insert(2, "02");
		myTree.insert(3, "03");
		myTree.insert(4, "04");
		myTree.insert(5, "05");

	}

	public void setup7() {
		myTree = new AVLTree<Integer, String>();

		myTree.insert(1, "01");
		myTree.insert(2, "02");
		myTree.insert(4, "04");
		myTree.insert(5, "05");

	}

	@Test
	void CreateTreeTest() {
		setup1();
		assertNotNull("El objeto no se creo correctamente", myTree);
	}

	// ------------------------------
	// Insert Test
	// ------------------------------

	@Test
	void insertTest1() {
		setup1();

		myTree.insert(1, "1234");
		assertTrue("El nodo no fue insertado correctamente",
				(myTree.getRoot().getKey() == 1) && myTree.getRoot().getElement().equals("1234"));
	}

	@Test
	void insertTest2() {
		setup4();
		myTree.insert(7, "07");
		assertTrue("El nodo no se inserto correctamente por la derecha",
				(myTree.getRoot().getRight().getRight().getKey() == 7)
						&& myTree.getRoot().getRight().getRight().getElement().equals("07"));

	}

	@Test
	void inserTest3() {
		setup5();
		myTree.insert(1, "01");
		assertTrue("El nodo no se inserto correctamente por la izquierda",
				(myTree.getRoot().getLeft().getKey() == 1) && myTree.getRoot().getLeft().getElement().equals("01"));

	}

	@Test
	void insertTest4() {
		// insertar desbalanceo por la ziquierda cuando el subarbol el fb del  subarbol es -1, 1 , 0.
	}

	@Test
	void insertTest5() {
		// insertar desbalanceo por la derecha cuando el subarbol el fb del  subarbol es -1, 1 , 0.

	}

	// ------------------------------
	// Search Test
	// ------------------------------

	@Test
	void searchTest1() {

		setup6();

		String nodeSearch= myTree.searchValue(1);
		assertTrue("El nodo no se encontro en el arbol", (nodeSearch.equals("01")));
		
		/*
		nodo1 = myTree.searchValue(3);
		assertTrue("El nodo no se encontro en el arbol", (nodo1.getKey() == 3 && nodo1.getElement().equals("03")));
		nodo1 = myTree.searchValue(5);
		assertTrue("El nodo no se encontro en el arbol", (nodo1.getKey() == 5 && nodo1.getElement().equals("05")));
		*/
	}

	@Test
	void searchTest2() {

		setup7();

		assertEquals(null, myTree.searchValue(3), "El nodo no existe ");

	}

	// ------------------------------
	// Delete Test
	// ------------------------------

	@Test
	void deleteTest1() {
		setup6();
		assertTrue(myTree.deleteValue(2), "el nodo raiz no fue eliminado correctamente");
		assertNull(myTree.searchValue(2));

	}

	@Test
	void deleteTest2() {
		setup6();
		assertTrue(myTree.deleteValue(5), "el ultimo nodo de la derecha no fue eliminado e");
		assertNull(myTree.searchValue(5));

	}

	@Test
	void deleteTest3() {
		setup6();
		assertTrue(myTree.deleteValue(2), "el nodo de la raiz no fue eliminado");
		assertNull(myTree.searchValue(2));

	}

	@Test
	void deleteTest4() {
		setup6();
		assertTrue(myTree.deleteValue(3), "el nodo no fue fue eliminado");
		assertNull(myTree.searchValue(3));

	}

	@Test
	void deleteTest5() {
		setup6();
		assertTrue(myTree.deleteValue(4), "el nodo no fue eliminado");
		assertNull(myTree.searchValue(4));

	}
	

}
