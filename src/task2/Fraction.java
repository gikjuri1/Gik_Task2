package task2;

import java.lang.reflect.Proxy;

public class Fraction implements Fractionable {
    private int num;
    private int denum;
    public double cache;
    public Fraction (int num, int denum) {
        this.num=num;
        this.denum=denum;
        this.cache= 0;
    }
    @Mutator
    public void setNum(int num) {
        this.num = num;
        System.out.println("erase cache");
        this.cache=0;
    }

    @Mutator
    public void setDenum(int denum){
        this.denum=denum;
        System.out.println("erase cache");
        this.cache=0;
    }

    public int getNum() {
        return num;
    }

    public int getDenum() {
        return denum;
    }

    @Override
    @Cache
    public double doubleValue() {
        this.cache= num/denum;
        System.out.println("invoke double value, cache=" + this.cache);
        return this.cache;
    }
    public  Object getProxy()
    {
        Class cls = this.getClass();
        return  Proxy.newProxyInstance(cls.getClassLoader(),
                new Class[]{Fractionable.class},
                new FractionableInvHandler(this));
    }

}
