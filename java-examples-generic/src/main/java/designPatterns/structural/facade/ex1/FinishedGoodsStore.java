package designPatterns.structural.facade.ex1;

public class FinishedGoodsStore implements Store {

	public Goods getGoods() {
		FinishedGoods finishedGoods = new FinishedGoods();
		return finishedGoods;
	} 
}// End of class 
