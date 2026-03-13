package event;

import Charactor.*;

public class Event {

    public static boolean checkHit(Girl girl, Wave wave, int girlSize, int waveHeight){

        if(girl.x + girlSize > wave.x && girl.x < wave.x){

            if(girl.y + girlSize >= wave.y - waveHeight){
                return true;
            }

        }

        return false;
    }

    public static void gameStop(Wave[] wave, Environment[] env){

        for(Wave w : wave){
            w.timeMove.stop();
        }

        for(Environment e : env){
            e.stop();
        }

    }

}
