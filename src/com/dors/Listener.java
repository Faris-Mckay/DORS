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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faris
 */
public class Listener {
    
    ExecutorService submissionService = Executors.newSingleThreadExecutor();
    
    private static final Listener instance = new Listener();
    
    /**
     * Returns the single active Listener instance
     * @return 
     */
    public static Listener getInstance(){
        return instance;
    }
    
    /**
     * On going method, constantly checking for incoming tasks and sorts them
     */
    public void listen(){
        submissionService.submit(new Runnable(){
            @Override
            public void run(){
                Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                Thread.currentThread().setDaemon(true);
                while(true){
                    TaskController.checkNewTasks();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DORS.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

}
