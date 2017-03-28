package akarshb.heartconnoisseur;

/**
 * Created by Dell on 28-03-2017.
 */

public class Parameters {
    public String param1,param2;
    public Parameters(String param1,String param2){
        this.setParam1(param1);
        this.setParam2(param2);
    }
    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

}

