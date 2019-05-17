package designPatterns.structural.facade.ex1;

public class Client {

	public static void main(String[] args) throws Exception{
		StoreKeeper keeper1 = new StoreKeeper();
		RawMaterialGoods rawMaterialGoods = keeper1.getRawMaterialGoods();
		System.out.println(rawMaterialGoods.getClassName());

		StoreKeeper2 keeper2 = new StoreKeeper2();
		Goods goods2=keeper2.getGoods("Raw");
		System.out.println(goods2.getClassName());
		
	}

}
