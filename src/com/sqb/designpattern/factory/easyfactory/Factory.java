package com.sqb.designpattern.factory.easyfactory;

// π§≥ß¿‡ 
public class Factory {
    public BMW createBMW(int type) {
        switch (type) {
        case 320:
            return new BMW320();
        case 532:
            return new BMW523();
            default:
                break;
        }
        return null;
    }
}
