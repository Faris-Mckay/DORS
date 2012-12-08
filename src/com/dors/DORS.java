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

import com.dors.task.TaskController;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DORS ** Date or Registered Scheduler ** 
 * 
 * is an open source product freely distributed for use in any kind of java application
 * it acts as a logic implementation mechanism that handles all logic processing on a date based
 * scheduler. It can be used as a means of reducing load from your application and code time so 
 * the developers can instead focus on the core of their application rather than the controlled
 * execution of data.
 * 
 * @author Faris
 */
public class DORS {
    
    private static volatile boolean started;
    
    /**
     * Begins the application 
     */
    public static void main(String[] args){
        try {
            new DORS().start();
        } catch (TooManyListenersException ex) {
            Logger.getLogger(DORS.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /**
     * @return the started
     */
    public static boolean isStarted() {
        return started;
    }

    /**
     * @param aStarted the started to set
     */
    public static void setStarted(boolean aStarted) {
        started = aStarted;
    }
    
    /**
     * Begins the listener and starts the task management
     * @throws TooManyListenersException 
     */
    public void start() throws TooManyListenersException {
        if (isStarted()) {
            throw new TooManyListenersException();
        }
        setStarted(true);
        Thread engine = new Thread() {
            @Override
            public void run() {
                Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                Listener.getInstance().listen();
                while (true) {
                    TaskController.sortTasks();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DORS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        engine.start();
    }

}
