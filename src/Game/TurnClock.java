package Game;

import java.util.*;

/**
 * Created by Matthew on 28/04/2014.
 */
public class TurnClock {

    private static int currentTurn = 0;

    private static HashMap<Integer, ArrayList<Actor>> schedule = new HashMap<Integer, ArrayList<Actor>>();

    public static void scheduleTurn(int interval, Actor actor) {
        if (schedule.containsKey(currentTurn + interval)) {
            schedule.get(currentTurn + interval).add(actor);
        } else {
            schedule.put(currentTurn + interval, new ArrayList<Actor>());
            schedule.get(currentTurn + interval).add(actor);
        }
    }

    public static void nextTurn() {
        currentTurn++;
        boolean playerTurn = false;
        List<Actor> toDoThisTurn = schedule.get(currentTurn);

        if (toDoThisTurn != null && toDoThisTurn.size() > 0) {
            //System.out.println("Turn " + currentTurn + " is starting: [TurnClock.nextTurn");
            for (Actor a : toDoThisTurn) {
                //System.out.println(a.getName() + " is taking their turn [TurnClock.nextTurn]");
                a.doTurn();
                if (a.name.equals("Player")) {
                    playerTurn = true;
                }
            }
        } else {
            //no moves scheduled for this turn
            //System.out.println("No moves scheduled for turn " + currentTurn + " [TurnClock.nextTurn]");
        }

        //remove all past turns
        cleanSchedule();

        if(!playerTurn){
            nextTurn();
        }
    }

    private static void cleanSchedule(){
        //this block removes all turns before the current turn to keep the schedule from overflowing
        Iterator it = schedule.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pairs = (Map.Entry) it.next();
            if((Integer)pairs.getKey() < currentTurn){
                it.remove();
            }
        }
    }

    public static void cancelFutureTurns(Actor actor){
        //this block removes all future turns
        int toRemove = -1;
        for(Map.Entry<Integer, ArrayList<Actor>> entry : schedule.entrySet()){
            if(entry.getValue().contains(actor)){
                toRemove = entry.getKey();
            }
        }

        if(toRemove != -1 && toRemove != currentTurn){
            schedule.get(toRemove).remove(actor);
        }
    }


    public static int getCurrentTurn(){
        return currentTurn;
    }

    public static void cancelAllMonsterTurns(){
        //call this when the scene is changed to stop monsters in the old scene continuing to move

        int nextPlayerTurn = -1;
        ArrayList<Actor> temp = new ArrayList<Actor>();
        for(Map.Entry<Integer, ArrayList<Actor>> entry : schedule.entrySet()){
            for(Actor a : entry.getValue()){
                if(a.getName().equals("Player")){
                    nextPlayerTurn = entry.getKey();
                    temp.add(a);
                }
            }
        }

        schedule.clear();
        schedule.put(nextPlayerTurn,temp);
    }

}
