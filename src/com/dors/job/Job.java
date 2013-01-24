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

/**
 *
 * @author Faris
 */
public abstract class Job implements Runnable{
    
    public Job(String name){
        this.name = name;
    }
        
    public static final int EXECUTOR_STOP = -1, EXECUTOR_FINALIZE = 1;
    
    private String name;
    private int executions;
    
    /**
     * Adds a count to the execution
     */
    public void incrementExecutions(){
        ++executions;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the executions
     */
    public int getExecutions() {
        return executions;
    }
    
    
}
