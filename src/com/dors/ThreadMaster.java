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
package com.dors;

import com.dors.event.impl.InterruptableEvent;
import com.dors.misc.IllegalEventTypeException;
import com.dors.task.Task;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Faris
 */
public class ThreadMaster {
    
    private static final ThreadMaster instance = new ThreadMaster();
    
    private static Map<Task, Integer> taskIDs = new HashMap();
    
    /**
     * Returns the single active ThreadMaster instance
     * @return 
     */
    public static ThreadMaster getInstance(){
        return instance;
    }
    
    ExecutorService workerThreads = Executors.newCachedThreadPool();
    
    /**
     * Pushes a task onto an available worker thread and registers the task 
     * @param task 
     */
    public void assignTask(Task task){
        taskIDs.put(task, getNewID());
        workerThreads.submit(task);
    }
    
    /**
     * Haults execution of an already active Event 
     * @param e the event
     * @throws IllegalEventTypeException when the given event is not interruptable
     */
    public void interuptEvent(InterruptableEvent e) throws IllegalEventTypeException{
        if(!(e instanceof InterruptableEvent) || (e.shouldEnd())) {
            throw new IllegalEventTypeException();
        }
        e.interupt();
        taskIDs.remove(getKeyByTask(e));
    }
    
    /**
     * Returns the key associated with the given task
     * @param task is the Job to be identified
     * @return the key of the Job
     */
    private Integer getKeyByTask(Job task){
        for(Task tasks : taskIDs.keySet()){
            if(tasks == task){
                return taskIDs.get(task);
            }
        }
        return null;
    }
    
    /**
     * Finds an unused key for use with registering new jobs
     * @returns the unused key
     */
    private Integer getNewID() {
        int key = 0;
        while(taskIDs.containsValue(key)){
            key++;
        }
        return key;
    }

}
