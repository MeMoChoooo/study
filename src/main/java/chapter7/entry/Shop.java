package chapter7.entry;

public class Shop<T> {
    private T goods;

    public Shop(T goods){
        this.goods = goods;
    }

    public void setGoods(T goods){
        this.goods = goods;
    }

    public T getGoods(){
        return goods;
    }
}
