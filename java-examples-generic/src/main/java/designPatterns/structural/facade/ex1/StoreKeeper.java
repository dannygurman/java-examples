package designPatterns.structural.facade.ex1;

public class StoreKeeper {

	public RawMaterialGoods getRawMaterialGoods() {
		RawMaterialStore store = new RawMaterialStore();
		RawMaterialGoods rawMaterialGoods = (RawMaterialGoods)store.getGoods();
		return rawMaterialGoods;
	} 


	public FinishedGoods getFinishedGoods() {
		FinishedGoodsStore store = new FinishedGoodsStore();
		FinishedGoods finishedGoods = (FinishedGoods)store.getGoods();
		return finishedGoods;
	}

}
