package com.example.dragonraise;

public class Dragon {
    double nastroi = 0;
    double son = 0;
    double eat = 0;
    double gigiena = 0;
    int x = 1000;
    int h = 700;
    int k = 600;
    int l = 850;
    int level = 0;
    int lvl_ang=0;
    public void checkLv(int lvl){
        if(lvl>=360){
            this.level++;
            this.lvl_ang=0;
        }
    }
}
