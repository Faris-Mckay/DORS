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

import com.dors.job.event.ext.RecurringEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faris
 */
public class MaintainedEventExecution {

   public MaintainedEventExecution(RecurringEvent event){
       this.event = event;
   }
   
   private RecurringEvent event;
   
   /**
    * Begins the execution cycle
    * WARNING: This is permanent
    */
   public void startExecution(){
       Thread execution = new Thread(){
           @Override
           public void run(){
               while(true){
                   try {
                       event.execute();
                       Thread.sleep(event.getRecurrancyRate());
                   } catch (InterruptedException ex) {
                       Logger.getLogger(MaintainedEventExecution.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
           }
       };
       execution.start();
   }
          
}
