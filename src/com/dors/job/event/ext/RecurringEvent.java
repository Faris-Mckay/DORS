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

/**
 *
 * @author Faris
 */
public abstract class RecurringEvent extends SimpleEvent {

    public RecurringEvent(long frequency, String name){
        super(name);
        setRecurrancyRate(frequency);
    }
    
    private long recurrancyRate;

    /**
     * @return the recurrancyRate
     */
    public long getRecurrancyRate() {
        return recurrancyRate;
    }

    /**
     * @param recurrancyRate the recurrancyRate to set
     */
    private void setRecurrancyRate(long recurrancyRate) {
        this.recurrancyRate = recurrancyRate;
    }

}
