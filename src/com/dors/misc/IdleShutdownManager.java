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
package com.dors.misc;

import com.dors.DORS;
import com.dors.event.EventExecutor;
import com.dors.task.TaskController;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class controls the idle period of the API, when not in use DORS should shutdown completely to maximize
 * CPU availability elsewhere, and once a new task has been received it may be started once again
 * 
 * @author Faris
 */
public class IdleShutdownManager {
    
    public static IdleShutdownManager getSingleton(){
        return manager;
    }

    private static final IdleShutdownManager manager = new IdleShutdownManager();
    
    /**
     * Checks the usage of events and tasks active in DORS
     * @returns if their is no activity
     */
    public boolean checkAPIUsage(){
        return TaskController.checkActivity() && EventExecutor.checkActivity();
    }
    
    /**
     * Handles the checking, starting and stopping of the API when needed
     * @param bypass 
     */
    public void DORSIdleExecution(boolean bypass){
        if(bypass){
            if(DORS.isStarted()) {
                return;
            }
            try {
                new DORS().start();
                return;
            } catch (TooManyListenersException ex) {
                Logger.getLogger(IdleShutdownManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(checkAPIUsage()){
            System.exit(0);
        } else {
            DORSIdleExecution(true);
        }
    }
    

}
