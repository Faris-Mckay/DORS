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
package com.dors.job.event;

import com.dors.job.event.ext.SimpleEvent;
import com.dors.job.event.ext.DatedEvent;
import com.dors.job.Job;
import com.dors.job.event.ext.InterruptableEvent;
import com.dors.job.event.ext.RecurringEvent;
import com.dors.misc.IllegalEventTypeException;
import com.dors.util.JobList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 *
 * @author Faris
 */
public class EventExecutor {
    
    private static final EventExecutor instance = new EventExecutor();
    
    public static EventExecutor getSingleton(){
        return instance;
    }
    
    public static List<Job> activeEvents = new JobList();
    public static List<Job> incomingEvents = new JobList();
    public static List<Job> idleEvents = new JobList();
    
    public void executeEvents(){
        if(activeEvents.isEmpty())
            return;
        for(Job event : activeEvents){
            if(event instanceof InterruptableEvent && ((InterruptableEvent)event).shouldEnd() || event instanceof RecurringEvent){
                continue;
            }
            if(((DatedEvent)event).execute() == Job.EXECUTOR_FINALIZE){
                ((DatedEvent)event).finalise();
            }
        }
    }
    
    public void syncIncomingEvents(){
        if(incomingEvents.isEmpty())
            return;
        activeEvents.addAll(incomingEvents);
        incomingEvents.clear();
        checkIdleActivity();
    }
    
    /**
     * Submits a event into the incoming event queue
     * @param task
     * @throws Exception 
     */
    public void addEvent(Job event) throws Exception{
        if(!(event instanceof DatedEvent) || !(event instanceof SimpleEvent)){
            throw new IllegalEventTypeException();
        }
        if(event instanceof DatedEvent){
            if(((DatedEvent)event).getDate().before(new Date(System.currentTimeMillis()))){
                throw new DataFormatException();
            }
            incomingEvents.add(event);
        } else {
            scheduleRecurrancyEvent(((RecurringEvent)event));
        }
    }
    
    /**
     * Loops through the incoming tasks and puts them into the 
     * executory cycle or the idle task list
     */
    public void checkIdleActivity(){
        if(activeEvents.isEmpty()) {
            return;
        }
        Iterator<Job> it = activeEvents.iterator();
        while(it.hasNext()){
            DatedEvent event = (DatedEvent) it.next();
            if((event.getDate().before(new Date(System.currentTimeMillis()))) || (event instanceof InterruptableEvent && ((InterruptableEvent)event).shouldEnd())){
                idleEvents.add(event);
                it.remove();
            }
        }
    }
    
    /**
     * Checks and returns the conditions for the activity of all tasks
     * @return 
     */
    public static boolean checkActivity(){
        return activeEvents.isEmpty() && incomingEvents.isEmpty() && idleEvents.isEmpty();
    }
    
    /**
     * Takes in the simple events and begins the frequent executions
     * @param event is the simple event
     */
    private void scheduleRecurrancyEvent(RecurringEvent event) {
        new MaintainedEventExecution(event).startExecution();
    }

}
