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
/* Primitive-type-only definitions (values) */
/*		 
 * Copyright (C) 2002-2012 Sebastiano Vigna 
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
package it.unimi.dsi.fastutil.bytes;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanCollections;
import it.unimi.dsi.fastutil.booleans.BooleanSets;
import java.util.Map;
/** A class providing static methods and objects that do useful things with type-specific maps.
 *
 * @see it.unimi.dsi.fastutil.Maps
 * @see java.util.Collections
 */
public class Byte2BooleanMaps {
 private Byte2BooleanMaps() {}
 /** An immutable class representing an empty type-specific map.
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */
 public static class EmptyMap extends Byte2BooleanFunctions.EmptyFunction implements Byte2BooleanMap , java.io.Serializable, Cloneable {
  public static final long serialVersionUID = -7046029254386353129L;
  protected EmptyMap() {}
  public boolean containsValue( final boolean v ) { return false; }
  public void putAll( final Map<? extends Byte, ? extends Boolean> m ) { throw new UnsupportedOperationException(); }
  @SuppressWarnings("unchecked")
  public ObjectSet<Byte2BooleanMap.Entry > byte2BooleanEntrySet() { return ObjectSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public ByteSet keySet() { return ByteSets.EMPTY_SET; }
  @SuppressWarnings("unchecked")
  public BooleanCollection values() { return BooleanSets.EMPTY_SET; }
  public boolean containsValue( final Object ov ) { return false; }
        private Object readResolve() { return EMPTY_MAP; }
  public Object clone() { return EMPTY_MAP; }
  public boolean isEmpty() { return true; }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<Byte, Boolean>> entrySet() { return (ObjectSet)byte2BooleanEntrySet(); }

  public int hashCode() { return 0; }

  public boolean equals( final Object o ) {
   if ( ! ( o instanceof Map ) ) return false;

   return ((Map<?,?>)o).isEmpty();
  }

  public String toString() { return "{}"; }
 }



 /** An empty type-specific map (immutable). It is serializable and cloneable. */

 @SuppressWarnings("rawtypes")
 public static final EmptyMap EMPTY_MAP = new EmptyMap();


 /** An immutable class representing a type-specific singleton map.	 
	 *
	 * <P>This class may be useful to implement your own in case you subclass
	 * a type-specific map.
	 */

 public static class Singleton extends Byte2BooleanFunctions.Singleton implements Byte2BooleanMap , java.io.Serializable, Cloneable {

  public static final long serialVersionUID = -7046029254386353129L;

  protected transient volatile ObjectSet<Byte2BooleanMap.Entry > entries;
  protected transient volatile ByteSet keys;
  protected transient volatile BooleanCollection values;

  protected Singleton( final byte key, final boolean value ) {
   super( key, value );
  }

  public boolean containsValue( final boolean v ) { return ( (value) == (v) ); }

  public boolean containsValue( final Object ov ) { return ( (((((Boolean)(ov)).booleanValue()))) == (value) ); }


  public void putAll( final Map<? extends Byte, ? extends Boolean> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Byte2BooleanMap.Entry > byte2BooleanEntrySet() { if ( entries == null ) entries = ObjectSets.singleton( (Byte2BooleanMap.Entry )new SingletonEntry() ); return entries; }
  public ByteSet keySet() { if ( keys == null ) keys = ByteSets.singleton( key ); return keys; }
  public BooleanCollection values() { if ( values == null ) values = BooleanSets.singleton( value ); return values; }

  protected class SingletonEntry implements Byte2BooleanMap.Entry , Map.Entry<Byte,Boolean> {
   public Byte getKey() { return (Byte.valueOf(Singleton.this.key)); }
   public Boolean getValue() { return (Boolean.valueOf(Singleton.this.value)); }


   public byte getByteKey() { return Singleton.this.key; }



   public boolean getBooleanValue() { return Singleton.this.value; }
   public boolean setValue( final boolean value ) { throw new UnsupportedOperationException(); }


   public Boolean setValue( final Boolean value ) { throw new UnsupportedOperationException(); }

   public boolean equals( final Object o ) {
    if (!(o instanceof Map.Entry)) return false;
    Map.Entry<?,?> e = (Map.Entry<?,?>)o;

    return ( (Singleton.this.key) == (((((Byte)(e.getKey())).byteValue()))) ) && ( (Singleton.this.value) == (((((Boolean)(e.getValue())).booleanValue()))) );
   }

   public int hashCode() { return (Singleton.this.key) ^ (Singleton.this.value ? 1231 : 1237); }
   public String toString() { return Singleton.this.key + "->" + Singleton.this.value; }
  }

  public boolean isEmpty() { return false; }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public ObjectSet<Map.Entry<Byte, Boolean>> entrySet() { return (ObjectSet)byte2BooleanEntrySet(); }

  public int hashCode() { return (key) ^ (value ? 1231 : 1237); }

  public boolean equals( final Object o ) {
   if ( o == this ) return true;
   if ( ! ( o instanceof Map ) ) return false;

   Map<?,?> m = (Map<?,?>)o;
   if ( m.size() != 1 ) return false;
   return entrySet().iterator().next().equals( m.entrySet().iterator().next() );
  }

  public String toString() { return "{" + key + "=>" + value + "}"; }
 }

 /** Returns a type-specific immutable map containing only the specified pair. The returned map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned map.
	 * @param value the only value of the returned map.
	 * @return a type-specific immutable map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Byte2BooleanMap singleton( final byte key, boolean value ) {
  return new Singleton ( key, value );
 }



 /** Returns a type-specific immutable map containing only the specified pair. The returned map is serializable and cloneable.
	 *
	 * <P>Note that albeit the returned map is immutable, its default return value may be changed.
	 *
	 * @param key the only key of the returned map.
	 * @param value the only value of the returned map.
	 * @return a type-specific immutable map containing just the pair <code>&lt;key,value></code>.
	 */

 public static Byte2BooleanMap singleton( final Byte key, final Boolean value ) {
  return new Singleton ( ((key).byteValue()), ((value).booleanValue()) );
 }




 /** A synchronized wrapper class for maps. */

 public static class SynchronizedMap extends Byte2BooleanFunctions.SynchronizedFunction implements Byte2BooleanMap , java.io.Serializable {

  public static final long serialVersionUID = -7046029254386353129L;

  protected final Byte2BooleanMap map;

  protected transient volatile ObjectSet<Byte2BooleanMap.Entry > entries;
  protected transient volatile ByteSet keys;
  protected transient volatile BooleanCollection values;

  protected SynchronizedMap( final Byte2BooleanMap m, final Object sync ) {
   super( m, sync );
   this.map = m;
  }

  protected SynchronizedMap( final Byte2BooleanMap m ) {
   super( m );
   this.map = m;
  }

  public int size() { synchronized( sync ) { return map.size(); } }
  public boolean containsKey( final byte k ) { synchronized( sync ) { return map.containsKey( k ); } }
  public boolean containsValue( final boolean v ) { synchronized( sync ) { return map.containsValue( v ); } }

  public boolean defaultReturnValue() { synchronized( sync ) { return map.defaultReturnValue(); } }
  public void defaultReturnValue( final boolean defRetValue ) { synchronized( sync ) { map.defaultReturnValue( defRetValue ); } }

  public boolean put( final byte k, final boolean v ) { synchronized( sync ) { return map.put( k, v ); } }

  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { synchronized( sync ) { map.putAll( c ); } }
  public void putAll( final Map<? extends Byte, ? extends Boolean> m ) { synchronized( sync ) { map.putAll( m ); } }

  public ObjectSet<Byte2BooleanMap.Entry > byte2BooleanEntrySet() { if ( entries == null ) entries = ObjectSets.synchronize( map.byte2BooleanEntrySet(), sync ); return entries; }
  public ByteSet keySet() { if ( keys == null ) keys = ByteSets.synchronize( map.keySet(), sync ); return keys; }
  public BooleanCollection values() { if ( values == null ) return BooleanCollections.synchronize( map.values(), sync ); return values; }

  public void clear() { synchronized( sync ) { map.clear(); } }
  public String toString() { synchronized( sync ) { return map.toString(); } }


  public Boolean put( final Byte k, final Boolean v ) { synchronized( sync ) { return map.put( k, v ); } }



  public boolean remove( final byte k ) { synchronized( sync ) { return map.remove( k ); } }
  public boolean get( final byte k ) { synchronized( sync ) { return map.get( k ); } }
  public boolean containsKey( final Object ok ) { synchronized( sync ) { return map.containsKey( ok ); } }



  public boolean containsValue( final Object ov ) { synchronized( sync ) { return map.containsValue( ov ); } }







  public boolean isEmpty() { synchronized( sync ) { return map.isEmpty(); } }
  public ObjectSet<Map.Entry<Byte, Boolean>> entrySet() { synchronized( sync ) { return map.entrySet(); } }

  public int hashCode() { synchronized( sync ) { return map.hashCode(); } }
  public boolean equals( final Object o ) { synchronized( sync ) { return map.equals( o ); } }
 }

 /** Returns a synchronized type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */
 public static Byte2BooleanMap synchronize( final Byte2BooleanMap m ) { return new SynchronizedMap ( m ); }

 /** Returns a synchronized type-specific map backed by the given type-specific map, using an assigned object to synchronize.
	 *
	 * @param m the map to be wrapped in a synchronized map.
	 * @param sync an object that will be used to synchronize the access to the map.
	 * @return a synchronized view of the specified map.
	 * @see java.util.Collections#synchronizedMap(Map)
	 */

 public static Byte2BooleanMap synchronize( final Byte2BooleanMap m, final Object sync ) { return new SynchronizedMap ( m, sync ); }



 /** An unmodifiable wrapper class for maps. */

 public static class UnmodifiableMap extends Byte2BooleanFunctions.UnmodifiableFunction implements Byte2BooleanMap , java.io.Serializable {

  public static final long serialVersionUID = -7046029254386353129L;

  protected final Byte2BooleanMap map;

  protected transient volatile ObjectSet<Byte2BooleanMap.Entry > entries;
  protected transient volatile ByteSet keys;
  protected transient volatile BooleanCollection values;

  protected UnmodifiableMap( final Byte2BooleanMap m ) {
   super( m );
   this.map = m;
  }

  public int size() { return map.size(); }
  public boolean containsKey( final byte k ) { return map.containsKey( k ); }
  public boolean containsValue( final boolean v ) { return map.containsValue( v ); }

  public boolean defaultReturnValue() { throw new UnsupportedOperationException(); }
  public void defaultReturnValue( final boolean defRetValue ) { throw new UnsupportedOperationException(); }

  public boolean put( final byte k, final boolean v ) { throw new UnsupportedOperationException(); }

  //public void putAll( final MAP KEY_VALUE_EXTENDS_GENERIC c ) { throw new UnsupportedOperationException(); }
  public void putAll( final Map<? extends Byte, ? extends Boolean> m ) { throw new UnsupportedOperationException(); }

  public ObjectSet<Byte2BooleanMap.Entry > byte2BooleanEntrySet() { if ( entries == null ) entries = ObjectSets.unmodifiable( map.byte2BooleanEntrySet() ); return entries; }
  public ByteSet keySet() { if ( keys == null ) keys = ByteSets.unmodifiable( map.keySet() ); return keys; }
  public BooleanCollection values() { if ( values == null ) return BooleanCollections.unmodifiable( map.values() ); return values; }

  public void clear() { throw new UnsupportedOperationException(); }
  public String toString() { return map.toString(); }


  public Boolean put( final Byte k, final Boolean v ) { throw new UnsupportedOperationException(); }



  public boolean remove( final byte k ) { throw new UnsupportedOperationException(); }
  public boolean get( final byte k ) { return map.get( k ); }
  public boolean containsKey( final Object ok ) { return map.containsKey( ok ); }



  public boolean containsValue( final Object ov ) { return map.containsValue( ov ); }







  public boolean isEmpty() { return map.isEmpty(); }
  public ObjectSet<Map.Entry<Byte, Boolean>> entrySet() { return ObjectSets.unmodifiable( map.entrySet() ); }
 }

 /** Returns an unmodifiable type-specific map backed by the given type-specific map.
	 *
	 * @param m the map to be wrapped in an unmodifiable map.
	 * @return an unmodifiable view of the specified map.
	 * @see java.util.Collections#unmodifiableMap(Map)
	 */
 public static Byte2BooleanMap unmodifiable( final Byte2BooleanMap m ) { return new UnmodifiableMap ( m ); }

}
