package designPatterns.behavioral.command.ex2;

/**
 * Created by Gurman on 22/04/2016.
 */
//Concrete classes implementing the Order interface.

    public class SellStock implements Order {
        private Stock abcStock;

        public SellStock(Stock abcStock){
            this.abcStock = abcStock;
        }

        public void execute() {
            abcStock.sell();
        }

}
