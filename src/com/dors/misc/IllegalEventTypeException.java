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

/**
 *
 * @author Faris
 */
public class IllegalEventTypeException extends Exception {
    
    
    /**
     * Constructs a IllegalEventTypeException with no detail message.
     * A detail message is a String that describes this particular exception.
     */

    public IllegalEventTypeException() {
        super();
    }

    /**
     * Constructs a IllegalEventTypeException with the specified detail message.
     * A detail message is a String that describes this particular exception.
     * @param s the detail message
     */

    public IllegalEventTypeException(String s) {
        super(s);
    }

}
