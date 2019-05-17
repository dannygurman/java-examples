package designPatterns.structural.facade.ex1;

public class RawMaterialStore implements Store {

	public Goods getGoods() {
		RawMaterialGoods rawMaterialedGoods = new RawMaterialGoods();
		return rawMaterialedGoods;
	} 
}