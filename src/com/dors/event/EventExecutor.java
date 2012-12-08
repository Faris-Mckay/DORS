/**
 *  This file is part of DORS API.
 * 
 *  DORS API - Java is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  DORS API - Java is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with  DORS API - Java.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.dors.event;

import com.dors.Job;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Faris
 */
public class EventExecutor {
    
    public static List<Event> activeEvents = new ArrayList();
    public static List<Event> incomingEvents = new ArrayList();
    public static List<Event> idleEvents = new ArrayList();
    
    /**
     * Loops through all of the registered Events and executes all of which are due for an execution 
     */
    public void executeEvents(){
        if(activeEvents.isEmpty())
            return;
        for(Event event : activeEvents){
            if(event.execute() == Job.EXECUTOR_FINALIZE){
                event.finalise();
            }
        }
        syncIncomingEvents();
    }
    
    /**
     * Loops through a list containing the new events to be scheduled and synchronizes
     * them into the active events list following a single execution of the current list
     */
    public void syncIncomingEvents(){
        if(incomingEvents.isEmpty())
            return;
        activeEvents.addAll(incomingEvents);
        incomingEvents.clear();
    }

}
