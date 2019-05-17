package designPatterns.structural.composite.menu;


public class MenuTestDrive {
	public static void main(String args[]) {
		MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
		MenuComponent dinerMenu =  new Menu("DINER MENU", "Lunch");
		MenuComponent cafeMenu = new Menu("CAFE MENU", "Dinner");
		MenuComponent dessertMenu = new Menu("DESSERT MENU", "Dessert of course!");
		MenuComponent coffeeMenu = new Menu("COFFEE MENU", "Stuff to go with your afternoon coffee");  
		
		MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
  
		allMenus.add(pancakeHouseMenu);
		allMenus.add(dinerMenu);
		allMenus.add(cafeMenu);
  
		pancakeHouseMenu.add(new MenuItem("K&B's Pancake Breakfast", "Pancakes with scrambled eggs, and toast",	true,2.99));
		pancakeHouseMenu.add(new MenuItem("Regular Pancake Breakfast","Pancakes with fried eggs, sausage",false,2.99));
		
		dinerMenu.add(new MenuItem(	"Vegetarian BLT","(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99));
		dinerMenu.add(new MenuItem(	"BLT", "Bacon with lettuce & tomato on whole what", false, 2.99));		 	
   
		dinerMenu.add(dessertMenu);
  
		dessertMenu.add(new MenuItem("Apple Pie","Apple pie with a flakey crust, topped with vanilla icecream", true, 1.59));
  		dessertMenu.add(new MenuItem("Cheesecake","Creamy New York cheesecake, with a chocolate graham crust", true, 1.99));
		
		cafeMenu.add(new MenuItem("Veggie Burger and Air Fries", "Veggie burger on a whole wheat bun, lettuce, tomato, and fries", true, 3.99));
		
		cafeMenu.add(coffeeMenu);

		coffeeMenu.add(new MenuItem( "Coffee Cake",	"Crumbly cake topped with cinnamon and walnuts", true, 1.59));
		coffeeMenu.add(new MenuItem( "Bagel", "Flavors include sesame, poppyseed, cinnamon raisin, pumpkin", false,	0.69));
		 
		Waitress waitress = new Waitress(allMenus);
   
		waitress.printMenu();
	}
}
