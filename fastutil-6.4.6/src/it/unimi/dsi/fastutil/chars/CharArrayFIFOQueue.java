/* Generic definitions */




/* Assertions (useful to generate conditional code) */
/* Current type and class (and size, if applicable) */
/* Value methods */
/* Interfaces (keys) */
/* Interfaces (values) */
/* Abstract implementations (keys) */
/* Abstract implementations (values) */
/* Static containers (keys) */
/* Static containers (values) */
/* Implementations */
/* Synchronized wrappers */
/* Unmodifiable wrappers */
/* Other wrappers */
/* Methods (keys) */
/* Methods (values) */
/* Methods (keys/values) */
/* Methods that have special names depending on keys (but the special names depend on values) */
/* Equality */
/* Object/Reference-only definitions (keys) */
/* Primitive-type-only definitions (keys) */
/* Object/Reference-only definitions (values) */
/*		 
 * Copyright (C) 2010-2012 Sebastiano Vigna 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package it.unimi.dsi.fastutil.chars;
import java.util.NoSuchElementException;
/** A type-specific array-based FIFO queue, supporting also deque operations.
 *
 * <P>Instances of this class represent a FIFO queue using a backing
 * array in a circular way. The array is enlarged as needed, but it is never shrunk. Use the {@link #trim()} method
 * to reduce its size, if necessary.
 *
 * <P>This class provides additional methods that implement a <em>deque</em> (double-ended queue).
 */
public class CharArrayFIFOQueue extends AbstractCharPriorityQueue {
 /** The standard initial capacity of a queue. */
 public final static int INITIAL_CAPACITY = 16;
 /** The backing array. */
 @SuppressWarnings("unchecked")
 protected char array[] = CharArrays.EMPTY_ARRAY;
 /** The current (cached) length of {@link #array}. */
 protected int length;
 /** The start position in {@link #array}. It is always strictly smaller than {@link #length}.*/
 protected int start;
 /** The end position in {@link #array}. It is always strictly smaller than {@link #length}.
	 *  Might be actually smaller than {@link #start} because {@link #array} is used cyclically. */
 protected int end;
 /** Creates a new empty queue with given capacity.
	 *
	 * @param capacity the initial capacity of this queue.
	 */
 @SuppressWarnings("unchecked")
 public CharArrayFIFOQueue( final int capacity ) {
  if ( capacity > 0 ) this.array = new char[ capacity ];
  length = array.length;
 }
 /** Creates a new empty queue with standard {@linkplain #INITIAL_CAPACITY initial capacity}.
	 */
 public CharArrayFIFOQueue() {
  this( INITIAL_CAPACITY );
 }
 /** Returns <code>null</code> (FIFO queues have no comparator). 
	 * @return <code>null</code>.
	 */
 @Override
 public CharComparator comparator() {
  return null;
 }
 @Override
 public char dequeueChar() {
  if ( start == end ) throw new NoSuchElementException();
  final char t = array[ start ];
  if ( ++start == length ) start = 0;
  return t;
 }
 /** Dequeues the {@linkplain #last() last} element from the queue.
	 *
	 * @return the dequeued element. 
	 * @throws NoSuchElementException if the queue is empty.
	 */
 public char dequeueLastChar() {
  if ( start == end ) throw new NoSuchElementException();
  if ( end == 0 ) end = length;
  final char t = array[ --end ];


  return t;
 }

 private final void expand() {
  final char[] newArray = CharArrays.grow( array, length + 1, 0 );
  System.arraycopy( array, start, newArray, 0, length - start );
  System.arraycopy( array, 0, newArray, length - start, end );
  start = 0;
  end = length;
  length = ( array = newArray ).length;
 }

 @Override
 public void enqueue( char x ) {
  array[ end++ ] = x;
  if ( end == length ) end = 0;
  if ( end == start ) expand();
 }

 /** Enqueues a new element as the {@linkplain #first() first} element (in dequeuing order) of the queue.
	 */
 public void enqueueFirst( char x ) {
  if ( start == 0 ) start = length;
  array[ --start ] = x;
  if ( end == start ) expand();
 }

 /** Returns the first element of the queue. 
	 * @return the first element of the queue.	
	 */
 public char firstChar() {
  if ( start == end ) throw new NoSuchElementException();
  return array[ start ];
 }


 /** Returns the last element of the queue. 
	 * @return the last element of the queue.	
	 */
 public char lastChar() {
  if ( start == end ) throw new NoSuchElementException();
  return array[ ( end == 0 ? length : end ) - 1 ];
 }

 @Override
 public void clear() {







  start = end = 0;
 }

 /** Trims the queue to the smallest possible size. */
 @SuppressWarnings("unchecked")
 public void trim() {
  final int size = size();
  final char[] newArray =

           new char[ size + 1 ];



  if ( start <= end ) System.arraycopy( array, start, newArray, 0, end - start );
  else {
   System.arraycopy( array, start, newArray, 0, length - start );
   System.arraycopy( array, 0, newArray, length - start, end );
  }
  start = 0;
  length = ( end = size ) + 1;
  array = newArray;
 }

 @Override
 public int size() {
  final int apparentLength = end - start;
  return apparentLength >= 0 ? apparentLength : length + apparentLength;
 }
}
