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
package com.dors.job.event.ext;

import com.dors.job.Job;

/**
 *
 * @author Faris
 */
public abstract class SimpleEvent extends Job {
    
    public SimpleEvent(String name){
        super(name);
    }
    
    /**
     * The main type of execution, treat this as the main application logic
     * @return EXECUTOR_STOP, or EXECUTOR_FINALIZE to depict the next execution phase
     * of this submitted event
     */
    public abstract int execute();
    
    /**
     * Can be called optionally upon the end of
     * the tasks execution cycle, with executor finalize
     */
    public abstract void finalise();

}
