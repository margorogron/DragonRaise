package com.example.dragonraise;

public class Dragon {
    double nastroi = 1;
    double son = 1;
    double eat = 1;
    double gigiena = 1;
    boolean sleeping = false;
    int x = 800;
    int h = 700;
    int k = 600;
    int l = 500;
    int level = 1;
    double lvl_ang=0;
    public void checkLv(double lvl){
        if(lvl >= 360){
            this.level++;
            this.lvl_ang=0;
        }
    }
}
