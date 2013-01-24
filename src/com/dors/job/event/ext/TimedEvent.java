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
package com.dors.event.impl;

import com.dors.event.Event;

/**
 *
 * @author Faris
 */
public abstract class TimedEvent extends Event {
    
    public TimedEvent(TimeType type, int lengthOfEvent, String name){
        super(name);
        setType(type);
        setLengthOfEvent(lengthOfEvent);        
    }
    
    private TimeType type;
    private int lengthOfEvent;

    /**
     * @return the type
     */
    public TimeType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    private void setType(TimeType type) {
        this.type = type;
    }

    /**
     * @return the lengthOfEvent
     */
    public int getLengthOfEvent() {
        return lengthOfEvent;
    }

    /**
     * @param lengthOfEvent the lengthOfEvent to set
     */
    private void setLengthOfEvent(int lengthOfEvent) {
        this.lengthOfEvent = lengthOfEvent;
    }
    
    enum TimeType {
        MILLISECONDS, SECONDS, MINIUTES, HOURS ,DAYS
    }

}
