package com.br.slice.helper;

import java.io.Serializable;
import java.util.Comparator;

import scala.Tuple2;

public class TupleComparator implements Comparator<Tuple2<String, Integer>> ,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static TupleComparator INSTANCE = new TupleComparator();
	
	@Override
	public int compare(Tuple2<String, Integer> tuple_1, Tuple2<String, Integer> tuple_2) {
	      return -tuple_1._2.compareTo(tuple_2._2);     // sorts RDD elements descending (use for Top-N)
          // return t1._2.compareTo(t2._2);   // sorts RDD elements ascending (use for Bottom-N)
	}

}
