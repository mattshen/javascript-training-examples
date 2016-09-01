package au.com.salmat.adb.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import au.com.salmat.adb.common.util.function.Function1;
import au.com.salmat.adb.common.util.function.Predicate;

/**
 * Helpful utilities for operating on lists
 * 
 * @author Matt Shen
 *
 */
public class Lists {
	
	/**
	 * Partition a big list into smaller lists based on the partitionSize.
	 * @param list
	 * @param partitionSize
	 * @return
	 */
	public static <T> List<List<T>> partition(List<T> list, int partitionSize) {
		
		List<List<T>> decisionsByN = new ArrayList<List<T>>();
	    
	    final int sizeOfList = list.size();
	    
	    for (int i = 0; i < sizeOfList; i += partitionSize) {
	        decisionsByN.add(new ArrayList<T> (
	            list.subList(i, Math.min(sizeOfList, i + partitionSize)))
	        );
	    }
		
		return decisionsByN;
	}
	
	/**
	 * Group a List of objects by the values returned from key getter to a new map with sub lists. 
	 *  
	 * @param list the input list
	 * @param a function to get the key from an object for the map
	 * @return
	 */
	public static <T, KT> Map<KT, List<T>> groupBy(List<T> list, Function1<KT, T> keyGetter) {
		Map<KT, List<T>> listGroupedByKeys = new HashMap<KT, List<T>>();
		return addListToMap(list, listGroupedByKeys, keyGetter);
	}

	
	/**
	 * Group a List of objects by the values returned from key getter to an existing map with sub lists. 
	 *  
	 * @param list the input list
	 * @param listGroupedByKeys the result
	 * @param a function to get the key from an object for the map
	 * @return
	 */
	public static <T, KT> Map<KT, List<T>> addListToMap(List<T> list, Map<KT, List<T>> listGroupedByKeys, Function1<KT, T> keyGetter) {
		for(T o : list){
		    List<T> temp = listGroupedByKeys.get(keyGetter.apply(o));
		    if(temp == null){
		        temp = new ArrayList<T>();
		        listGroupedByKeys.put((KT)keyGetter.apply(o), temp);
		    }
		    temp.add(o);
		}
		return listGroupedByKeys;
	}
	
	/**
	 * Traverse the original list and create a new list based on the predicate.
	 * @param originalList
	 * @param predicate
	 * @return
	 */
	public static <T> List<T> findAll(List<T> originalList, Predicate<T> predicate) {
		if (originalList == null) return Collections.emptyList();
		List<T> result = new ArrayList<>();
		for (T e : originalList) {
			if (predicate.evaluate(e)) {
				result.add(e);
			}
		}
		return result;
	}
	
	/**
	 * Traverse the original list and return the first matched element in the list based on the given predicate.
	 * @param originalList
	 * @param predicate
	 * @return
	 */
	public static <T> T find(List<T> originalList, Predicate<T> predicate) {
		if (originalList == null) return null;
		T result = null;
		for (T e : originalList) {
			if (predicate.evaluate(e)) {
				result = e;
				break;
			}
		}
		return result;
	}
	
	/**
	 * Transform the list of one type to a list of another type.
	 * @param originalList
	 * @param mapper
	 * @return
	 */
	public static <T,R> List<R> map(List<T> originalList, Function1<R, T> mapper) {
		if (originalList == null) return Collections.emptyList();
		List<R> result = new ArrayList<>();
		for(T e : originalList) {
			result.add(mapper.apply(e));
		}
		return result;
	}
	
	public static <T> List<T> getUniqueElements(List<T> list) {
		Set<T> set = new HashSet<T>();
		for(T obj: list) {
			set.add(obj);
		}
		return new ArrayList<T>(set);
	}

	/**
	 * Concurrently traverse the list and apply the function. 
	 * 
	 * Please be advised, the sequence of processing the elements in the list is not guaranteed.
	 * 
	 * @param originalList
	 * @param mapper
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 */
	public static <T, R> void concurrentForEach(List<T> originalList, final Function1<R, T> mapper) throws InterruptedException, ExecutionException {
		concurrentForEach(originalList, mapper, Runtime.getRuntime().availableProcessors());
	}
	
	public static <T, R> void concurrentForEach(List<T> originalList, final Function1<R, T> mapper, int numberOfWorkers) throws InterruptedException, ExecutionException {
		if (originalList == null) return;
		ExecutorService workers = Executors.newFixedThreadPool(numberOfWorkers);
		Collection<Callable<R>> tasks = new ArrayList<Callable<R>>();
		
		for(final T element : originalList) {
			tasks.add(new Callable<R>() {
				@Override
				public R call() throws Exception {
					return mapper.apply(element);
				}
			});
		}
		
		workers.invokeAll(tasks);
		workers.shutdown();
	}
	
}
