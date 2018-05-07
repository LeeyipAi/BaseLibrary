package jmm.baselibrary.bean;

public enum LoadStatus {
    LOADING(0),
    REFRESH(1),
    MORE(2);

    private int value;

    LoadStatus(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
