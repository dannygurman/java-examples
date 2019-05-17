package designPatterns.structural.facade.ex1;

public class StoreKeeper2 {

	public Goods getGoods(String goodsType)throws Exception { 

		if (goodsType.equals("Finished")) {
			FinishedGoodsStore store = new FinishedGoodsStore();
			FinishedGoods finishedGoods = (FinishedGoods)store.getGoods();
			return finishedGoods;
		}
		else if (goodsType.equals("Raw")) {
			RawMaterialStore store = new RawMaterialStore();
			RawMaterialGoods rawMaterialGoods = (RawMaterialGoods)store.getGoods();
			return rawMaterialGoods;
		}
		else{
			throw new Exception("None familiar GOOD");
		}
	}

}
